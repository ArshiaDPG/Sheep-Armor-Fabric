package net.digitalpear.sheep_armor.client;

import net.digitalpear.sheep_armor.client.layers.SAModelLayers;
import net.digitalpear.sheep_armor.common.access.SheepRendererAccess;
import net.digitalpear.sheep_armor.common.items.SheepArmorItem;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.equipment.EquipmentRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.render.entity.state.SheepEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SheepArmorRenderer extends FeatureRenderer<SheepEntityRenderState, SheepEntityModel> {

    private final EntityModel<SheepEntityRenderState> sheepModel;
    private final EquipmentRenderer equipmentRenderer;
    public SheepArmorRenderer(FeatureRendererContext<SheepEntityRenderState, SheepEntityModel> context, LoadedEntityModels loader, EquipmentRenderer equipmentRenderer) {
        super(context);
        this.equipmentRenderer = equipmentRenderer;
        this.sheepModel = new SheepWoolEntityModel(loader.getModelPart(SAModelLayers.SHEEP_ARMOR));
    }



    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, SheepEntityRenderState state, float limbAngle, float limbDistance) {
        if (((SheepRendererAccess) state).hasArmor()) {
            ItemStack itemStack = ((SheepRendererAccess) state).getBodyArmor();
            Item sheepArmor = itemStack.getItem();
            if (sheepArmor instanceof SheepArmorItem animalArmorItem) {
                this.sheepModel.setAngles(state);
                VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(animalArmorItem.getEntityTexture()));
                this.sheepModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
//                this.equipmentRenderer.render(EquipmentModel.LayerType.HORSE_BODY, EquipmentAssetKeys.NETHERITE, this.sheepModel, itemStack, matrices, vertexConsumers, light);
            }
        }
    }

}
