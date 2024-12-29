package net.digitalpear.sheep_armor.mixin;


import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.access.SheepRendererAccess;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import net.minecraft.client.render.entity.state.SheepEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SheepWoolFeatureRenderer.class)
public class SheepWoolFeatureRendererMixin {

    @Mutable
    @Shadow @Final private static Identifier SKIN;

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/client/render/entity/state/SheepEntityRenderState;FF)V", at = @At("HEAD"))
    private void newTexture(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, SheepEntityRenderState sheepEntityRenderState, float f, float g, CallbackInfo ci){
        if (SheepArmor.hasValidName(sheepEntityRenderState) != null){
            SKIN = SheepArmor.id(SheepVariant.SHEEP_TEXTURE_PATH).withSuffixedPath("special/" + SheepArmor.hasValidName(sheepEntityRenderState) + "_fur.png");
        }
        else{
            SheepVariant variant = ((SheepRendererAccess) sheepEntityRenderState).getVariant();
            if (variant != null){
                SKIN = variant.getWoolTexturePath().withSuffixedPath(".png");
            }
        }
    }
}
