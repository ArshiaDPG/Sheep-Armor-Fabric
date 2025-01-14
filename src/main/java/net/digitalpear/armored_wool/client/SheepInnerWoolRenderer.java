package net.digitalpear.armored_wool.client;

import me.shedaniel.autoconfig.AutoConfig;
import net.digitalpear.armored_wool.ArmoredWool;
import net.digitalpear.armored_wool.ArmoredWoolConfig;
import net.digitalpear.armored_wool.common.access.SheepArmorAccess;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

@Environment(EnvType.CLIENT)
public class SheepInnerWoolRenderer extends FeatureRenderer<SheepEntity, SheepEntityModel<SheepEntity>> {
    private final EntityModel<SheepEntity> model;

    public SheepInnerWoolRenderer(FeatureRendererContext<SheepEntity, SheepEntityModel<SheepEntity>> context, EntityModelLoader loader) {
        super(context);
        this.model = new SheepEntityModel(loader.getModelPart(EntityModelLayers.SHEEP));

    }


//    public SheepInnerWoolRenderer(FeatureRendererContext<SheepEntityRenderState, SheepEntityModel> context, EntityModelLoader loader) {
//        super(context);
//    }


    @Override
    protected Identifier getTexture(SheepEntity entity) {
        return ((SheepArmorAccess) entity).getVariant().value().getWoolTexturePath().withSuffixedPath(".png");
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, SheepEntity sheepEntity, float f, float g, float h, float j, float k, float l) {
        ArmoredWoolConfig config = AutoConfig.getConfigHolder(ArmoredWoolConfig.class).getConfig();
        if (!config.clientConfig.hasInnerColoring ||!((SheepArmorAccess) sheepEntity).getVariant().value().hasInnerWool() || ArmoredWool.hasValidName(sheepEntity) != null){
            return;
        }
        boolean m;
        Identifier SKIN = ((SheepArmorAccess) sheepEntity).getVariant().value().getInnerWoolTexturePath().withSuffixedPath(".png");
        if (sheepEntity.isInvisible()) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            m = minecraftClient.hasOutline(sheepEntity);
            if (m) {
                this.getContextModel().copyStateTo(this.model);
                this.model.animateModel(sheepEntity, f, g, h);
                this.model.setAngles(sheepEntity, f, g, j, k, l);
                VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getOutline(SKIN));
                this.model.render(matrixStack, vertexConsumer, i, LivingEntityRenderer.getOverlay(sheepEntity, 0.0F), -16777216);
            }

        } else {
            int u;
            if (sheepEntity.hasCustomName() && "jeb_".equals(sheepEntity.getName().getString())) {
                m = true;
                int n = sheepEntity.age / 25 + sheepEntity.getId();
                int o = DyeColor.values().length;
                int p = n % o;
                int q = (n + 1) % o;
                float r = ((float)(sheepEntity.age % 25) + h) / 25.0F;
                int s = SheepEntity.getRgbColor(DyeColor.byId(p));
                int t = SheepEntity.getRgbColor(DyeColor.byId(q));
                u = ColorHelper.Argb.lerp(r, s, t);
            } else {
                u = SheepEntity.getRgbColor(sheepEntity.getColor());
            }

            render(this.getContextModel(), this.model, SKIN, matrixStack, vertexConsumerProvider, i, sheepEntity, f, g, j, k, l, h, u);
        }
    }
}
