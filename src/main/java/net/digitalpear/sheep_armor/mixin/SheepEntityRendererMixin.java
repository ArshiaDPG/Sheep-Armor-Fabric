package net.digitalpear.sheep_armor.mixin;


import net.digitalpear.sheep_armor.client.SheepArmorRenderer;
import net.digitalpear.sheep_armor.client.SheepArmorWoolRenderer;
import net.digitalpear.sheep_armor.client.SheepInnerWoolRenderer;
import net.digitalpear.sheep_armor.common.access.SheepArmorAccess;
import net.digitalpear.sheep_armor.common.access.SheepRendererAccess;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.minecraft.client.render.entity.AgeableMobEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.state.SheepEntityRenderState;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepEntityRenderer.class)
public abstract class SheepEntityRendererMixin extends AgeableMobEntityRenderer<SheepEntity, SheepEntityRenderState, SheepEntityModel> {

    public SheepEntityRendererMixin(EntityRendererFactory.Context context, SheepEntityModel model, SheepEntityModel babyModel, float shadowRadius) {
        super(context, model, babyModel, shadowRadius);
    }

    @Inject(at = @At("RETURN"), method = "updateRenderState(Lnet/minecraft/entity/passive/SheepEntity;Lnet/minecraft/client/render/entity/state/SheepEntityRenderState;F)V")
    private void renderState(SheepEntity sheepEntity, SheepEntityRenderState sheepEntityRenderState, float f, CallbackInfo ci){
        if (sheepEntityRenderState instanceof SheepRendererAccess){
            ((SheepRendererAccess) sheepEntityRenderState).setBodyArmor(sheepEntity.getBodyArmor());
            ((SheepRendererAccess) sheepEntityRenderState).setVariant(((SheepArmorAccess)sheepEntity).getVariant().value());
        }
    }


    @Inject(at = @At("RETURN"), method = "<init>")
    private void addSheepArmor(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature(new SheepArmorRenderer(this, context.getEntityModels()));
        this.addFeature(new SheepArmorWoolRenderer(this, context.getEntityModels()));
        this.addFeature(new SheepInnerWoolRenderer(this, context.getEntityModels()));
    }

    @Inject(at = @At("RETURN"), method = "getTexture(Lnet/minecraft/client/render/entity/state/SheepEntityRenderState;)Lnet/minecraft/util/Identifier;", cancellable = true)
    private void newTexture(SheepEntityRenderState sheepEntityRenderState, CallbackInfoReturnable<Identifier> cir){
        SheepVariant variant = ((SheepRendererAccess) sheepEntityRenderState).getVariant();
        if (variant != null){
            cir.setReturnValue(variant.getTexturePath().withSuffixedPath(".png"));
        }
    }
}