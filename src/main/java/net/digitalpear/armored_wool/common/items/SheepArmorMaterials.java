package net.digitalpear.armored_wool.common.items;

import net.digitalpear.armored_wool.init.AWTags;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;

public interface SheepArmorMaterials {

    RegistryEntry<ArmorMaterial> CACTUS = register(
            "cactus",
            2, //Defense Amount
            2, //Enchantability
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, //Equip Sound
            0.0F, //Toughness
            0.0F, //Knockback Resistance
            AWTags.AWItemTags.REPAIRS_CACTUS_EQUIPMENT); //Repair Ingredient

    RegistryEntry<ArmorMaterial> AMETHYST = register(
            "amethyst",
            5,
            30,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            0.0F,
            0.0F,
            AWTags.AWItemTags.REPAIRS_AMETHYST_EQUIPMENT);

    RegistryEntry<ArmorMaterial> COPPER = register(
            "copper",
            7,
            4,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            AWTags.AWItemTags.REPAIRS_COPPER_EQUIPMENT);

    RegistryEntry<ArmorMaterial> SHULKER = register(
            "shulker",
            15,
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.5F,
            0.0F,
            AWTags.AWItemTags.REPAIRS_SHULKER_EQUIPMENT);



    private static RegistryEntry<ArmorMaterial> register(String id, int defenseAmount, int enchantability, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient) {
        EnumMap<ArmorItem.Type, Integer> enumMap = Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {map.put(ArmorItem.Type.BOOTS, 8);map.put(ArmorItem.Type.LEGGINGS, 9);map.put(ArmorItem.Type.CHESTPLATE, 14);map.put(ArmorItem.Type.HELMET, 10);
            map.put(ArmorItem.Type.BODY, defenseAmount);
        });
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(Identifier.ofVanilla(id)));


        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.ofVanilla(id), new ArmorMaterial(enumMap, enchantability, equipSound, () -> Ingredient.fromTag(repairIngredient), layers, toughness, knockbackResistance));
    }

}
