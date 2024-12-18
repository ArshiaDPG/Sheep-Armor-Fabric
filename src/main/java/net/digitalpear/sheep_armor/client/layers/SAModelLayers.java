package net.digitalpear.sheep_armor.client.layers;

import net.digitalpear.sheep_armor.SheepArmor;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;

public class SAModelLayers {
    public static final EntityModelLayer SHEEP_ARMOR = new EntityModelLayer(SheepArmor.id("sheep_armor_layer"), "main");
    public static final EntityModelLayer SHEEP_ARMOR_WOOL = new EntityModelLayer(SheepArmor.id("sheep_armor_wool_layer"), "main");
    public static final float MODEL_SCALE = 1f;

    public static TexturedModelData getArmorWoolModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.6F)), ModelTransform.pivot(0.0F, 6.0F, -8.0F).scaled(MODEL_SCALE));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(28, 8).cuboid(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, new Dilation(1.75F)), ModelTransform.of(0.0F, 5.0F, 2.0F, 1.5707964F, 0.0F, 0.0F).scaled(MODEL_SCALE));
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new Dilation(0.5F));
        modelPartData.addChild("right_hind_leg", modelPartBuilder, ModelTransform.pivot(-3.0F, 12.0F, 7.0F).scaled(MODEL_SCALE));
        modelPartData.addChild("left_hind_leg", modelPartBuilder, ModelTransform.pivot(3.0F, 12.0F, 7.0F).scaled(MODEL_SCALE));
        modelPartData.addChild("right_front_leg", modelPartBuilder, ModelTransform.pivot(-3.0F, 12.0F, -5.0F).scaled(MODEL_SCALE));
        modelPartData.addChild("left_front_leg", modelPartBuilder, ModelTransform.pivot(3.0F, 12.0F, -5.0F).scaled(MODEL_SCALE));
        return TexturedModelData.of(modelData, 64, 32);
    }

    public static TexturedModelData getArmorModelData() {
        ModelData modelData = QuadrupedEntityModel.getModelData(12, Dilation.NONE);
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.0F, -6.0F, 6.0F, 6.0F, 8.0F), ModelTransform.pivot(0.0F, 6F, -8F).scaled(MODEL_SCALE));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(28, 8).cuboid(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F), ModelTransform.of(0.0F, 5F, 2F, 1.5707964F, 0.0F, 0.0F).scaled(MODEL_SCALE));
        return TexturedModelData.of(modelData, 64, 32);
    }
}
