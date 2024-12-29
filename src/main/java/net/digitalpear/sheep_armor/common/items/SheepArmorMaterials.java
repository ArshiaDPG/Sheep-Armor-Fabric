package net.digitalpear.sheep_armor.common.items;

import net.digitalpear.sheep_armor.init.SATags;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;

public interface SheepArmorMaterials {

    ArmorMaterial CACTUS = register(
            5, //Durability
            2, //Defense Amount
            2, //Enchantability
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, //Equip Sound
            0.0F, //Toughness
            0.0F, //Knockback Resistance
            SATags.SAItemTags.REPAIRS_CACTUS_EQUIPMENT); //Repair Ingredient

    ArmorMaterial AMETHYST = register(
            9,
            5,
            30,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            0.0F,
            0.0F,
            SATags.SAItemTags.REPAIRS_AMETHYST_EQUIPMENT);

    ArmorMaterial COPPER = register(
            12,
            7,
            4,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            SATags.SAItemTags.REPAIRS_COPPER_EQUIPMENT);

    ArmorMaterial SHULKER = register(
            20,
            15,
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.5F,
            0.0F,
            SATags.SAItemTags.REPAIRS_SHULKER_EQUIPMENT);


    private static ArmorMaterial register(int durability, int defenseAmount, int enchantability, RegistryEntry<SoundEvent > equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient) {
        EnumMap<EquipmentType, Integer> enumMap = Util.make(new EnumMap(EquipmentType.class), (map) -> {map.put(EquipmentType.BOOTS, 8);map.put(EquipmentType.LEGGINGS, 9);map.put(EquipmentType.CHESTPLATE, 14);map.put(EquipmentType.HELMET, 10);
            map.put(EquipmentType.BODY, defenseAmount);
        });

        return new ArmorMaterial(durability, enumMap, enchantability, equipSound, toughness, knockbackResistance, repairIngredient, EquipmentAssetKeys.LEATHER);
    }

}
