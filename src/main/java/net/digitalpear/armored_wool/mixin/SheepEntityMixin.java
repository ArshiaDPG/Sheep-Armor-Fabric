package net.digitalpear.armored_wool.mixin;


import me.shedaniel.autoconfig.AutoConfig;
import net.digitalpear.armored_wool.ArmoredWool;
import net.digitalpear.armored_wool.ArmoredWoolConfig;
import net.digitalpear.armored_wool.common.access.SheepArmorAccess;
import net.digitalpear.armored_wool.common.entity.AWRegistryKeys;
import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.digitalpear.armored_wool.init.AWEnchantments;
import net.digitalpear.armored_wool.init.AWTags;
import net.digitalpear.armored_wool.init.SheepVariants;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity implements SheepArmorAccess {

    @Unique
    private static final TrackedData<RegistryEntry<SheepVariant>> VARIANT = DataTracker.registerData(SheepEntityMixin.class, ArmoredWool.SHEEP_VARIANT);

    @Inject(at = @At("HEAD"), method = "writeCustomDataToNbt")
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putString("variant", this.getVariant().getKey().get().getValue().toString());
    }

    @Inject(at = @At("HEAD"), method = "readCustomDataFromNbt")
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        RegistryEntry<SheepVariant> sheepVariant = this.getRegistryManager().get(AWRegistryKeys.SHEEP_VARIANT).getEntry(Identifier.tryParse(nbt.getString("variant"))).get();
        this.setVariant(sheepVariant);
    }
    @Inject(at = @At("HEAD"), method = "initDataTracker")
    private void addData(DataTracker.Builder builder, CallbackInfo ci){
        builder.add(VARIANT, this.getRegistryManager().get(AWRegistryKeys.SHEEP_VARIANT).getEntry(SheepVariants.BARN.getValue()).get());
    }
    @Unique
    public RegistryEntry<SheepVariant> getVariant(){
        return this.dataTracker.get(VARIANT);
    }
    @Unique
    public void setVariant(RegistryEntry<SheepVariant> value){
        this.dataTracker.set(VARIANT, value);
    }
    @Inject(at = @At("RETURN"), method = "initialize")
    private void addVariantStuff(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CallbackInfoReturnable<EntityData> cir){
        RegistryEntry<Biome> biomeEntry = world.getBiome(this.getBlockPos());
        RegistryEntry<SheepVariant> compatibleVariant = SheepVariants.fromBiome(this.getRegistryManager(), biomeEntry, world.getRandom());
        ArmoredWoolConfig config = AutoConfig.getConfigHolder(ArmoredWoolConfig.class).getConfig();
        if (config.serverConfig.hasVariants){
            this.setVariant(compatibleVariant);
        }
        if (config.serverConfig.hasCustomColors){
            this.setColor(compatibleVariant.value().generateColor(world.getRandom()));
        }

    }


    @Shadow public abstract void setColor(DyeColor color);

    @Shadow protected abstract DyeColor getChildColor(AnimalEntity firstParent, AnimalEntity secondParent);

    @Shadow public abstract RegistryKey<LootTable> getLootTableId();

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
            if (getWorld() instanceof ServerWorld){
                dropStack(getBodyArmor());
            }
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
            ItemStack armorStack = this.getBodyArmor();

            //Cactus armor thorns effect
            if (armorStack.isIn(AWTags.AWItemTags.THORNY_SHEEP_ARMORS) && source.getAttacker() != null){
                ArmoredWoolConfig config = AutoConfig.getConfigHolder(ArmoredWoolConfig.class).getConfig();
                source.getAttacker().damage(this.getDamageSources().cactus(), config.serverConfig.thornyArmorDamage);
            }

            //Damage item
            armorStack.damage(MathHelper.ceil(amount), this, EquipmentSlot.BODY);

            //Explode if armor breaks and should explode
            if (armorStack.isEmpty()){
                int level = getBodyArmor().getEnchantments().getLevel(getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(AWEnchantments.WOOLSPLOSION).get());
                if (level > 0){
                    this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 2 * level, World.ExplosionSourceType.NONE);
                }
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
            sheepEntity.setColor(getChildColor(this, (AnimalEntity) entity));

            if (random.nextFloat() > 0.5f){
                ((SheepArmorAccess)sheepEntity).setVariant(this.getVariant());
            }
            else if (entity instanceof SheepEntity && random.nextFloat() > 0.5f){
                ((SheepArmorAccess)sheepEntity).setVariant(((SheepArmorAccess) entity).getVariant());
            }
            else{
                RegistryEntry<Biome> biomeEntry = world.getBiome(this.getBlockPos());
                ((SheepArmorAccess)sheepEntity).setVariant(SheepVariants.fromBiome(this.getRegistryManager(), biomeEntry, world.getRandom()));
            }
        }
        return sheepEntity;
    }

    /*
        Drop wool if eats grass with armor enchanted with Trimming equipped.
     */
    @Inject(method = "onEatingGrass", at = @At("HEAD"))
    private void applyTrimmingFunctionality(CallbackInfo ci){
        int level = getBodyArmor().getEnchantments().getLevel(getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(AWEnchantments.TRIMMING).get());
        if (level > 0){
            this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1.0F, 0.5F);
            if (getWorld() instanceof ServerWorld){
                for (int i = 0; i < random.nextBetween(1, level); i++){
                    List<ItemStack> stacks = generateLoot((ServerWorld) getWorld(), getLootTableId(), getBlockPos());
                    for (ItemStack stack : stacks){
                        ItemEntity itemEntity = this.dropStack(stack, 1.0F);
                        if (itemEntity != null) {
                            itemEntity.setVelocity(itemEntity.getVelocity().add((this.random.nextFloat() - this.random.nextFloat()) * 0.1F, this.random.nextFloat() * 0.05F, (this.random.nextFloat() - this.random.nextFloat()) * 0.1F));
                        }
                    }
                }
            }
        }

    }
    private static List<ItemStack> generateLoot(ServerWorld world, RegistryKey<LootTable> tableRegistryKey, BlockPos pos) {
        LootTable lootTable = world.getServer().getReloadableRegistries().getLootTable(tableRegistryKey);
        LootContextParameterSet lootContextParameterSet = (new LootContextParameterSet.Builder(world)).add(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos)).build(LootContextTypes.ENCHANTED_ENTITY);
        return lootTable.generateLoot(lootContextParameterSet);
    }
}
