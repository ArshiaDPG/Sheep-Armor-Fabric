package net.digitalpear.armored_wool.client.layers;

import net.digitalpear.armored_wool.ArmoredWool;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class AWModelLayers {
    public static final EntityModelLayer SHEEP_ARMOR = new EntityModelLayer(ArmoredWool.id("sheep_armor_layer"), "main");
    public static final EntityModelLayer SHEEP_ARMOR_WOOL = new EntityModelLayer(ArmoredWool.id("sheep_armor_wool_layer"), "main");

    public static final float MODEL_SCALE = 0.2f;

    public static TexturedModelData getArmorWoolModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.6F + MODEL_SCALE)), ModelTransform.pivot(0.0F, 6.0F, -8.0F));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(28, 8).cuboid(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, new Dilation(1.75F + MODEL_SCALE)), ModelTransform.of(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.5F + MODEL_SCALE));
        modelPartData.addChild("right_hind_leg", modelPartBuilder, ModelTransform.pivot(-3.0F, 12.0F, 7.0F));
        modelPartData.addChild("left_hind_leg", modelPartBuilder, ModelTransform.pivot(3.0F, 12.0F, 7.0F));
        modelPartData.addChild("right_front_leg", modelPartBuilder, ModelTransform.pivot(-3.0F, 12.0F, -5.0F));
        modelPartData.addChild("left_front_leg", modelPartBuilder, ModelTransform.pivot(3.0F, 12.0F, -5.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }

    public static TexturedModelData getArmorModelData() {
        ModelData modelData = QuadrupedEntityModel.getModelData(12, new Dilation(MODEL_SCALE));
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F, new Dilation(MODEL_SCALE)), ModelTransform.pivot(0.0F, 6.0F, -8.0F));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(28, 8).cuboid(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, new Dilation(MODEL_SCALE)), ModelTransform.of(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 32);
    }
}
