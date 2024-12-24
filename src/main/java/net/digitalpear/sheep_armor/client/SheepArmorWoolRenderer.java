package net.digitalpear.sheep_armor.client;

import net.digitalpear.sheep_armor.client.layers.SAModelLayers;
import net.digitalpear.sheep_armor.common.access.SheepRendererAccess;
import net.digitalpear.sheep_armor.common.items.SheepArmorItem;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SheepWoolEntityModel;
import net.minecraft.client.render.entity.state.SheepEntityRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SheepArmorWoolRenderer extends SheepWoolFeatureRenderer {

    private final EntityModel<SheepEntityRenderState> woolModel;

    public SheepArmorWoolRenderer(FeatureRendererContext<SheepEntityRenderState, SheepEntityModel> context, LoadedEntityModels loader) {
        super(context, loader);
        this.woolModel = new SheepWoolEntityModel(loader.getModelPart(SAModelLayers.SHEEP_ARMOR_WOOL));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, SheepEntityRenderState state, float limbAngle, float limbDistance) {
        if (((SheepRendererAccess) state).hasArmor()) {
            ItemStack itemStack = ((SheepRendererAccess) state).getBodyArmor();
            Item sheepArmorItem = itemStack.getItem();
            if (sheepArmorItem instanceof SheepArmorItem animalArmorItem) {
                this.woolModel.setAngles(state);
                VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(animalArmorItem.getWoolarmorTexture()), itemStack.hasGlint());
                this.woolModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
            }
        }
    }
}
