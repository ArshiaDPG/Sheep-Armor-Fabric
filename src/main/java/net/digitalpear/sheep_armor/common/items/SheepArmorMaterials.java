package net.digitalpear.sheep_armor.common.items;

import net.digitalpear.sheep_armor.init.SATags;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;

public interface SheepArmorMaterials {

    ArmorMaterial CACTUS = register(5,
            2,
            2,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            SATags.SAItemTags.REPAIRS_CACTUS_EQUIPMENT);

    ArmorMaterial AMETHYST = register(9,
            5,
            30,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            0.0F,
            0.0F,
            ItemTags.REPAIRS_LEATHER_ARMOR);

    ArmorMaterial COPPER = register(12,
            7,
            4,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON,
            0.0F,
            0.0F,
            ItemTags.REPAIRS_LEATHER_ARMOR);

    ArmorMaterial SHULKER = register(20,
            15,
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            0.0F,
            0.0F,
            ItemTags.REPAIRS_LEATHER_ARMOR);


    private static ArmorMaterial register(int durability, int defenseAmount, int enchantability, RegistryEntry<SoundEvent > equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient) {
        EnumMap<EquipmentType, Integer> enumMap = Util.make(new EnumMap(EquipmentType.class), (map) -> {map.put(EquipmentType.BOOTS, 8);map.put(EquipmentType.LEGGINGS, 9);map.put(EquipmentType.CHESTPLATE, 14);map.put(EquipmentType.HELMET, 10);
            map.put(EquipmentType.BODY, defenseAmount);
        });

        return new ArmorMaterial(durability, enumMap, enchantability, equipSound, toughness, knockbackResistance, repairIngredient, EquipmentAssetKeys.LEATHER);
    }

}
