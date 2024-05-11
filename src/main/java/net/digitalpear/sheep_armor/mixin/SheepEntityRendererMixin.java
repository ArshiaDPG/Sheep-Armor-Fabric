package net.digitalpear.sheep_armor.mixin;


import net.digitalpear.sheep_armor.client.SheepArmorRenderer;
import net.digitalpear.sheep_armor.client.SheepArmorWoolRenderer;
import net.digitalpear.sheep_armor.common.access.SheepArmorAccess;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.init.SheepVariants;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepEntityRenderer.class)
public abstract class SheepEntityRendererMixin extends MobEntityRenderer<SheepEntity, SheepEntityModel<SheepEntity>> {


    public SheepEntityRendererMixin(EntityRendererFactory.Context context, SheepEntityModel<SheepEntity> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(at = @At("RETURN"), method = "<init>")
    private void addAxolotlArmor(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature(new SheepArmorRenderer(this, context.getModelLoader()));
        this.addFeature(new SheepArmorWoolRenderer(this, context.getModelLoader()));
    }

    @Inject(at = @At("RETURN"), method = "getTexture(Lnet/minecraft/entity/passive/SheepEntity;)Lnet/minecraft/util/Identifier;", cancellable = true)
    private void getTexture(SheepEntity sheepEntity, CallbackInfoReturnable<Identifier> cir){
        RegistryEntry<SheepVariant> variant = ((SheepArmorAccess) sheepEntity).getVariant();

        if (variant.getKey().get() != SheepVariants.PALE){
            cir.setReturnValue(variant.value().getTextureID().withSuffixedPath(".png"));
        }
    }
}