package net.digitalpear.armored_wool.mixin;


import net.digitalpear.armored_wool.ArmoredWool;
import net.digitalpear.armored_wool.client.SheepArmorRenderer;
import net.digitalpear.armored_wool.client.SheepArmorWoolRenderer;
import net.digitalpear.armored_wool.client.SheepInnerWoolRenderer;
import net.digitalpear.armored_wool.common.access.SheepArmorAccess;
import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.SheepEntityRenderer;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.entity.passive.SheepEntity;
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
    private void addSheepArmor(EntityRendererFactory.Context context, CallbackInfo ci) {
        this.addFeature(new SheepArmorRenderer(this, context.getModelLoader()));
        this.addFeature(new SheepArmorWoolRenderer(this, context.getModelLoader()));
        this.addFeature(new SheepInnerWoolRenderer(this, context.getModelLoader()));
    }

    @Inject(at = @At("RETURN"), method = "getTexture(Lnet/minecraft/entity/passive/SheepEntity;)Lnet/minecraft/util/Identifier;", cancellable = true)
    private void newTexture(SheepEntity sheepEntity, CallbackInfoReturnable<Identifier> cir) {
        if (ArmoredWool.hasValidName(sheepEntity) != null) {
            cir.setReturnValue(ArmoredWool.id(SheepVariant.SHEEP_TEXTURE_PATH).withSuffixedPath("special/" + ArmoredWool.hasValidName(sheepEntity) + ".png"));
        } else {
            SheepVariant variant = ((SheepArmorAccess) sheepEntity).getVariant().value();
            if (variant != null) {
                cir.setReturnValue(variant.getTexturePath().withSuffixedPath(".png"));
            }
        }
    }
}