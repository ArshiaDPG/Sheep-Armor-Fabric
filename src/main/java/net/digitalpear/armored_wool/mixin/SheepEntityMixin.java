package net.digitalpear.armored_wool.mixin;


import net.digitalpear.armored_wool.ArmoredWool;
import net.digitalpear.armored_wool.common.access.SheepVariantAccess;
import net.digitalpear.armored_wool.common.entity.AWRegistryKeys;
import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.digitalpear.armored_wool.init.AWDataComponentTypes;
import net.digitalpear.armored_wool.init.SheepVariants;
import net.minecraft.component.ComponentType;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.Variants;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity implements SheepVariantAccess {

    @Unique
    private static final TrackedData<RegistryEntry<SheepVariant>> VARIANT = DataTracker.registerData(SheepEntityMixin.class, ArmoredWool.SHEEP_VARIANT);

    @Inject(at = @At("HEAD"), method = "writeCustomDataToNbt")
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        Variants.writeVariantToNbt(nbt, this.getVariant());
    }

    @Inject(at = @At("HEAD"), method = "readCustomDataFromNbt")
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        Variants.readVariantFromNbt(nbt, this.getRegistryManager(), AWRegistryKeys.SHEEP_VARIANT).ifPresent(this::setVariant);
    }
    @Inject(at = @At("HEAD"), method = "initDataTracker")
    private void addData(DataTracker.Builder builder, CallbackInfo ci){
        builder.add(VARIANT, Variants.getOrDefaultOrThrow(this.getRegistryManager(), SheepVariants.BARN));
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
        Optional<RegistryEntry.Reference<SheepVariant>> compatibleVariant = SheepVariants.select(this.random, this.getRegistryManager(), SpawnContext.of(world, this.getBlockPos()));
        if (compatibleVariant.isPresent()){
            this.setVariant(compatibleVariant.get());
            this.setColor(compatibleVariant.get().value().generateColor(world.getRandom()));
        }
    }

    @Shadow public abstract DyeColor getColor();

    @Shadow public abstract void setColor(DyeColor color);

    protected SheepEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
    @Nullable
    public <T> T get(ComponentType<? extends T> type) {
        return type == AWDataComponentTypes.SHEEP_VARIANT ? castComponentValue(type, this.getVariant()) : super.get(type);
    }
    @Inject(at = @At("HEAD"), method = "copyComponentsFrom")
    private void copyVariant(ComponentsAccess from, CallbackInfo ci){
        this.copyComponentFrom(from, AWDataComponentTypes.SHEEP_VARIANT);
    }

    @Inject(at = @At("RETURN"), method = "setApplicableComponent")
    private <T> void setApplicableVariant(ComponentType<T> type, T value, CallbackInfoReturnable<Boolean> cir){
        if (type == AWDataComponentTypes.SHEEP_VARIANT) {
            this.setVariant(castComponentValue(AWDataComponentTypes.SHEEP_VARIANT, value));
            cir.setReturnValue(true);
        }
    }
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.SHEEP_FOOD);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        SheepEntity sheepEntity = EntityType.SHEEP.create(world, SpawnReason.BREEDING);
        if (sheepEntity != null) {
            DyeColor dyeColor = this.getColor();
            DyeColor dyeColor2 = ((SheepEntity)entity).getColor();
            sheepEntity.setColor(DyeColor.mixColors(world, dyeColor, dyeColor2));

            if (random.nextFloat() > 0.5f){
                ((SheepVariantAccess)sheepEntity).setVariant(this.getVariant());
            }
            else if (entity instanceof SheepEntity && random.nextFloat() > 0.5f){
                ((SheepVariantAccess)entity).setVariant(((SheepVariantAccess) entity).getVariant());
            }
            else{
                Optional<RegistryEntry.Reference<SheepVariant>> variantRegistryEntry = SheepVariants.select(this.random, this.getRegistryManager(), SpawnContext.of(world, this.getBlockPos()));
                variantRegistryEntry.ifPresent(sheepVariantReference -> ((SheepVariantAccess) sheepEntity).setVariant(sheepVariantReference));
            }
        }
        return sheepEntity;
    }

}
