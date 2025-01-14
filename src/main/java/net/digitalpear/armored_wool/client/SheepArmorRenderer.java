package net.digitalpear.armored_wool.client;

import net.digitalpear.armored_wool.client.layers.AWModelLayers;
import net.digitalpear.armored_wool.common.access.SheepArmorAccess;
import net.digitalpear.armored_wool.common.items.SheepArmorItem;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SheepArmorRenderer<T extends SheepEntity> extends FeatureRenderer<T, SheepEntityModel<T>> {


    private final EntityModel<SheepEntity> sheepModel;

    public SheepArmorRenderer(FeatureRendererContext<T, SheepEntityModel<T>> context, EntityModelLoader loader) {
        super(context);
        this.sheepModel = new SheepEntityModel(loader.getModelPart(AWModelLayers.SHEEP_ARMOR));
    }



    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (((SheepArmorAccess) entity).hasArmor()) {
            ItemStack itemStack = ((SheepArmorAccess) entity).getBodyArmor();
            Item sheepArmor = itemStack.getItem();
            if (sheepArmor instanceof SheepArmorItem animalArmorItem) {
                this.sheepModel.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
                VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(animalArmorItem.getArmorTexture()), itemStack.hasGlint());
//                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(animalArmorItem.getArmorTexture()));
                this.sheepModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
            }
        }
    }
}
