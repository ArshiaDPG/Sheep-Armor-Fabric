package net.digitalpear.sheep_armor.client.layers;

import net.digitalpear.sheep_armor.SheepArmor;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class SAModelLayers {
    public static final EntityModelLayer SHEEP_ARMOR = new EntityModelLayer(new Identifier(SheepArmor.MOD_ID, "sheep_armor_layer"), "main");
    public static final EntityModelLayer SHEEP_ARMOR_WOOL = new EntityModelLayer(new Identifier(SheepArmor.MOD_ID, "sheep_armor_wool_layer"), "main");

}
