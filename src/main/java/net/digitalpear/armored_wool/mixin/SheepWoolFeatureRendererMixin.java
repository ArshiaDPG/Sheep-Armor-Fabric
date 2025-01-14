package net.digitalpear.armored_wool.mixin;


import net.digitalpear.armored_wool.ArmoredWool;
import net.digitalpear.armored_wool.common.access.SheepArmorAccess;
import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
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

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/passive/SheepEntity;FFFFFF)V", at = @At("HEAD"), cancellable = true)
    private void newTexture(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, SheepEntity sheepEntity, float f, float g, float h, float j, float k, float l, CallbackInfo ci){
        if (ArmoredWool.hasValidName(sheepEntity) != null){
            SKIN = ArmoredWool.id(SheepVariant.SHEEP_TEXTURE_PATH).withSuffixedPath("special/" + ArmoredWool.hasValidName(sheepEntity) + "_fur.png");
        }
        else{
            SheepVariant variant = ((SheepArmorAccess) sheepEntity).getVariant().value();
            if (variant != null){
                SKIN = variant.getWoolTexturePath().withSuffixedPath(".png");
            }
        }
    }
}
