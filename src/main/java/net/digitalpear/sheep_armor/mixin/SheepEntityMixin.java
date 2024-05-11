package net.digitalpear.sheep_armor.mixin;


import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.access.SheepArmorAccess;
import net.digitalpear.sheep_armor.common.entity.SARegistryKeys;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.init.SAEnchantments;
import net.digitalpear.sheep_armor.init.SAItems;
import net.digitalpear.sheep_armor.init.SheepVariants;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity implements SheepArmorAccess {

    @Unique
    private static final TrackedData<RegistryEntry<SheepVariant>> VARIANT = DataTracker.registerData(SheepEntity.class, SheepArmor.SHEEP_VARIANT);

    @Inject(at = @At("HEAD"), method = "writeCustomDataToNbt")
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putString("variant", this.getVariant().value().getName().toString());
    }

    @Inject(at = @At("HEAD"), method = "readCustomDataFromNbt")
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        RegistryEntry<SheepVariant> sheepVariant = this.getRegistryManager().get(SARegistryKeys.SHEEP_VARIANT).getEntry(Identifier.tryParse(nbt.getString("variant"))).get();
        this.setVariant(sheepVariant);
    }
    @Inject(at = @At("HEAD"), method = "initDataTracker")
    private void addData(DataTracker.Builder builder, CallbackInfo ci){
        builder.add(VARIANT, this.getRegistryManager().get(SARegistryKeys.SHEEP_VARIANT).entryOf(SheepVariants.PALE));
    }
    @Unique
    public RegistryEntry<SheepVariant> getVariant(){
        return this.dataTracker.get(VARIANT);
    }
    @Unique
    public void setVariant(RegistryEntry<SheepVariant> value){
        this.dataTracker.set(VARIANT, value);
    }
    @Inject(at = @At("HEAD"), method = "initialize")
    private void addVariantStuff(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CallbackInfoReturnable<EntityData> cir){
        RegistryEntry<Biome> biomeEntry = world.getBiome(this.getBlockPos());
        RegistryEntry compatibleBiome = SheepVariants.fromBiome(this.getRegistryManager(), biomeEntry);
        this.setVariant(compatibleBiome);
    }

    @Shadow protected abstract DyeColor getChildColor(AnimalEntity firstParent, AnimalEntity secondParent);

    @Shadow @Final private static Map<DyeColor, ItemConvertible> DROPS;

    @Shadow public abstract DyeColor getColor();

    protected SheepEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.SHEEP_FOOD);
    }


    public boolean hasArmor() {
        return !this.getBodyArmor().isEmpty();
    }


    public boolean shouldArmorAbsorbDamage(DamageSource source) {
        return this.hasArmor() && !source.isIn(DamageTypeTags.BYPASSES_WOLF_ARMOR);
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void armorInteractions(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir){
        ItemStack stack = player.getStackInHand(hand);
        if (hasArmor() && stack.isOf(Items.SHEARS)) {
            dropStack(getBodyArmor());
            this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 1.0F);
            equipBodyArmor(ItemStack.EMPTY);
            stack.damage(1, player, getSlotForHand(hand));
            cir.setReturnValue(ActionResult.SUCCESS);
            cir.cancel();
        }
    }
    @Inject(method = "sheared", at = @At("HEAD"))
    private void removeArmorOnSheard(SoundCategory shearedSoundCategory, CallbackInfo ci) {
        if (hasArmor()){
            dropStack(getBodyArmor());
            equipBodyArmor(ItemStack.EMPTY);
        }
    }


    @Override
    protected void applyDamage(DamageSource source, float amount) {
        if (this.shouldArmorAbsorbDamage(source)){
            ItemStack itemStack = this.getBodyArmor();

            //Cactus armor thorns effect
            if (itemStack.isOf(SAItems.CACTUS_SHEEP_ARMOR) && source.getAttacker() != null){
                source.getAttacker().damage(this.getDamageSources().cactus(), 2);
            }

            //Sould explode
            boolean explode = getBodyArmor().getEnchantments().getEnchantments().stream().anyMatch(enchantmentRegistryEntry -> enchantmentRegistryEntry.value() == SAEnchantments.WOOLSPLOSION);

            //Damage item
            itemStack.damage(MathHelper.ceil(amount), this, EquipmentSlot.BODY);

            //Explode if armor breaks and should explode
            if (itemStack.isEmpty() && explode){
                this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 5, World.ExplosionSourceType.NONE);
            }
        }
        else{
            super.applyDamage(source, amount);
        }
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        SheepEntity sheepEntity = EntityType.SHEEP.create(world);
        if (sheepEntity != null) {
            sheepEntity.setColor(getChildColor(this, (SheepEntity)entity));
            if (random.nextFloat() > 0.5f){
                ((SheepArmorAccess)sheepEntity).setVariant(this.getVariant());
            }
            else if (entity instanceof SheepEntity && random.nextFloat() > 0.5f){
                ((SheepArmorAccess)sheepEntity).setVariant(((SheepArmorAccess) entity).getVariant());
            }
            else{
                RegistryEntry<Biome> biomeEntry = world.getBiome(this.getBlockPos());
                ((SheepArmorAccess)sheepEntity).setVariant(SheepVariants.fromBiome(this.getRegistryManager(), biomeEntry));
            }

        }

        return sheepEntity;
    }

    /*
        Drop wool if eats grass with armor enchanted with Trimming equipped.
     */
    @Inject(method = "onEatingGrass", at = @At("HEAD"))
    private void applyTrimmingFunctionality(CallbackInfo ci){
        if (getBodyArmor().getEnchantments().getEnchantments().stream().anyMatch(enchantmentRegistryEntry -> enchantmentRegistryEntry.value() == SAEnchantments.TRIMMING)){
            int level = getBodyArmor().getEnchantments().getLevel(SAEnchantments.TRIMMING);
            this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 0.5F);
            for (int i = 0; i < random.nextBetween(1, level); i++){
                ItemEntity itemEntity = this.dropItem(DROPS.get(this.getColor()), 1);
                if (itemEntity != null) {
                    this.getBodyArmor().damage(1, this, EquipmentSlot.BODY);
                    itemEntity.setVelocity(itemEntity.getVelocity().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1F, this.random.nextFloat() * 0.05F, (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
                }
            }
        }
    }
}
