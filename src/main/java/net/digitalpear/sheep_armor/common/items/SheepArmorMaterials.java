package net.digitalpear.sheep_armor.common.items;

import net.digitalpear.sheep_armor.init.SATags;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;

public interface SheepArmorMaterials {

    ArmorMaterial CACTUS = register(5,
            Util.make(new EnumMap(EquipmentType.class), (map) -> {map.put(EquipmentType.BOOTS, 1);map.put(EquipmentType.LEGGINGS, 1);map.put(EquipmentType.CHESTPLATE, 1);map.put(EquipmentType.HELMET, 1);
                map.put(EquipmentType.BODY, 2);
            }),
            2,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            SATags.SAItemTags.REPAIRS_CACTUS_EQUIPMENT, EquipmentAssetKeys.LEATHER);
    ArmorMaterial COPPER = register(15,
            Util.make(new EnumMap(EquipmentType.class), (map) -> {map.put(EquipmentType.BOOTS, 1);map.put(EquipmentType.LEGGINGS, 4);map.put(EquipmentType.CHESTPLATE, 5);map.put(EquipmentType.HELMET, 2);
            map.put(EquipmentType.BODY, 7);
    }),
            4,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAssetKeys.LEATHER);
    ArmorMaterial AMETHYST = register(12,
            Util.make(new EnumMap(EquipmentType.class), (map) -> {map.put(EquipmentType.BOOTS, 2);map.put(EquipmentType.LEGGINGS, 5);map.put(EquipmentType.CHESTPLATE, 6);map.put(EquipmentType.HELMET, 2);
            map.put(EquipmentType.BODY, 5);
            }),
            30,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            0.0F,
            0.0F,
            ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAssetKeys.LEATHER);
    ArmorMaterial SHULKER = register(20,
            Util.make(new EnumMap(EquipmentType.class), (map) -> {map.put(EquipmentType.BOOTS, 8);map.put(EquipmentType.LEGGINGS, 9);map.put(EquipmentType.CHESTPLATE, 14);map.put(EquipmentType.HELMET, 10);
                map.put(EquipmentType.BODY, 15);
            }),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            ItemTags.REPAIRS_LEATHER_ARMOR, EquipmentAssetKeys.LEATHER);

    private static ArmorMaterial register(int durability, EnumMap<EquipmentType, Integer> defense, int enchantability, RegistryEntry<SoundEvent > equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient, RegistryKey<EquipmentAsset> layers) {
        EnumMap<EquipmentType, Integer> enumMap = new EnumMap(EquipmentType.class);
        EquipmentType[] var9 = EquipmentType.values();

        for (EquipmentType type : var9) {
            enumMap.put(type, defense.get(type));
        }

        return new ArmorMaterial(durability, enumMap, enchantability, equipSound, toughness, knockbackResistance, repairIngredient, layers);
    }
}
