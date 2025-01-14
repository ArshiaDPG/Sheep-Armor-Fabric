package net.digitalpear.armored_wool.client;

import net.digitalpear.armored_wool.client.layers.AWModelLayers;
import net.digitalpear.armored_wool.common.access.SheepArmorAccess;
import net.digitalpear.armored_wool.common.items.SheepArmorItem;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SheepArmorWoolRenderer extends SheepWoolFeatureRenderer {

    private final EntityModel<SheepEntity> woolModel;

    public SheepArmorWoolRenderer(FeatureRendererContext<SheepEntity, SheepEntityModel<SheepEntity>> context, EntityModelLoader loader) {
        super(context, loader);
        this.woolModel = new SheepWoolEntityModel(loader.getModelPart(AWModelLayers.SHEEP_ARMOR_WOOL));

    }


    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int i, SheepEntity sheepEntity, float f, float g, float h, float j, float k, float l) {
        if (((SheepArmorAccess) sheepEntity).hasArmor()) {
            ItemStack itemStack = ((SheepArmorAccess) sheepEntity).getBodyArmor();
            Item sheepArmorItem = itemStack.getItem();
            if (sheepArmorItem instanceof SheepArmorItem animalArmorItem) {
                this.woolModel.setAngles(sheepEntity,f, g, h, j, k);
                VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(animalArmorItem.getWoolarmorTexture()), itemStack.hasGlint());
                this.woolModel.render(matrices, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
            }
        }
        super.render(matrices, vertexConsumers, i, sheepEntity, f, g, h, j, k, l);
    }
}
