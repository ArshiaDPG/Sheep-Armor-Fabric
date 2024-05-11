package net.digitalpear.sheep_armor.mixin;


import net.digitalpear.sheep_armor.common.access.SheepArmorAccess;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.init.SheepVariants;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.registry.entry.RegistryEntry;
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

    @Inject(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/Entity;FFFFFF)V", cancellable = true, at = @At("HEAD"))
    private void newTexture(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, CallbackInfo ci){
        RegistryEntry<SheepVariant> variant = ((SheepArmorAccess) entity).getVariant();
        if (variant.getKey().get() != SheepVariants.PALE && variant.value().hasCustomWool()){
            SKIN = variant.value().getTextureID().withPath("_fur.png");
        }
    }
}
