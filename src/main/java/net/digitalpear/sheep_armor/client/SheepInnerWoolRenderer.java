package net.digitalpear.sheep_armor.client;

import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.access.SheepRendererAccess;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.render.entity.state.SheepEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class SheepInnerWoolRenderer extends FeatureRenderer<SheepEntityRenderState, SheepEntityModel> {
    private final EntityModel<SheepEntityRenderState> sheepModel;
    private final EntityModel<SheepEntityRenderState> babySheepModel;
    public SheepInnerWoolRenderer(FeatureRendererContext<SheepEntityRenderState, SheepEntityModel> context, LoadedEntityModels loader) {
        super(context);
        this.sheepModel = new SheepEntityModel(loader.getModelPart(EntityModelLayers.SHEEP));
        this.babySheepModel = new SheepEntityModel(loader.getModelPart(EntityModelLayers.SHEEP_BABY));
    }

    public Identifier getTexture(SheepEntityRenderState sheepEntityRenderState){
        return ((SheepRendererAccess) sheepEntityRenderState).getVariant().getInnerWoolTexturePath().withSuffixedPath(".png");
    }

    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, SheepEntityRenderState sheepEntityRenderState, float f, float g) {
        if (SheepArmor.ClientConfig.hasInnerColoring.getValue()){
            Identifier SKIN = getTexture(sheepEntityRenderState);
            EntityModel<SheepEntityRenderState> entityModel = sheepEntityRenderState.baby ? this.babySheepModel : this.sheepModel;
            if (sheepEntityRenderState.invisible) {
                if (sheepEntityRenderState.hasOutline) {
                    entityModel.setAngles(sheepEntityRenderState);
                    VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getOutline(SKIN));
                    entityModel.render(matrixStack, vertexConsumer, i, LivingEntityRenderer.getOverlay(sheepEntityRenderState, 0.0F), -16777216);
                }
            } else {
                int r;
                if (sheepEntityRenderState.customName != null && "jeb_".equals(sheepEntityRenderState.customName.getString())) {
                    int k = MathHelper.floor(sheepEntityRenderState.age);
                    int l = k / 25 + sheepEntityRenderState.id;
                    int m = DyeColor.values().length;
                    int n = l % m;
                    int o = (l + 1) % m;
                    float h = ((float)(k % 25) + MathHelper.fractionalPart(sheepEntityRenderState.age)) / 25.0F;
                    int p = SheepEntity.getRgbColor(DyeColor.byId(n));
                    int q = SheepEntity.getRgbColor(DyeColor.byId(o));
                    r = ColorHelper.lerp(h, p, q);
                } else {
                    r = SheepEntity.getRgbColor(sheepEntityRenderState.color);
                }

                render(entityModel, SKIN, matrixStack, vertexConsumerProvider, i, sheepEntityRenderState, r);
            }
        }
    }
}
