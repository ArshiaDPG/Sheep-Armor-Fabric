package net.digitalpear.sheep_armor.client;

import net.digitalpear.sheep_armor.client.layers.SAModelLayers;
import net.digitalpear.sheep_armor.common.access.SheepArmorAccess;
import net.digitalpear.sheep_armor.common.items.SheepArmorItem;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SheepArmorRenderer extends FeatureRenderer<SheepEntity, SheepEntityModel<SheepEntity>> {

    private final SheepEntityModel<SheepEntity> model;
    public SheepArmorRenderer(FeatureRendererContext<SheepEntity, SheepEntityModel<SheepEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new SheepEntityModel<>(loader.getModelPart(SAModelLayers.SHEEP_ARMOR));
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, SheepEntity sheepEntity, float f, float g, float h, float j, float k, float l) {
        if (((SheepArmorAccess) sheepEntity).hasArmor()) {
            ItemStack itemStack = sheepEntity.getBodyArmor();
            Item var13 = itemStack.getItem();
            if (var13 instanceof SheepArmorItem animalArmorItem) {
                this.getContextModel().copyStateTo(this.model);
                this.model.animateModel(sheepEntity, f, g, h);
                this.model.setAngles(sheepEntity, f, g, j, k, l);
                VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(animalArmorItem.getEntityTexture()));
                this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            }

        }
    }
}
