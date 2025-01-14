package net.digitalpear.armored_wool.init;

import net.digitalpear.armored_wool.ArmoredWool;
import net.digitalpear.armored_wool.common.items.SheepArmorItem;
import net.digitalpear.armored_wool.common.items.SheepArmorMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.HashMap;
import java.util.Map;

public class AWItems {
    public static Map<Item, Item> SHEEP_ARMOR_MAP = new HashMap<>();
    private static Item createItem(String name, Item item){
        return Registry.register(Registries.ITEM, ArmoredWool.id(name), item);
    }

    private static Item createSheepArmorItem(RegistryEntry<ArmorMaterial> material, int durability, Item ingredient){
        String materialName = material.getKey().map(RegistryKey::getValue).get().getPath();
        Item item = createItem(materialName + "_sheep_armor", new SheepArmorItem(materialName, material, new Item.Settings().maxCount(1).maxDamage(durability)));
        SHEEP_ARMOR_MAP.put(item, ingredient);
        return item;
    }

    public static final Item CACTUS_SHEEP_ARMOR = createSheepArmorItem(SheepArmorMaterials.CACTUS, 80, Items.CACTUS);
    public static final Item AMETHYST_SHEEP_ARMOR = createSheepArmorItem(SheepArmorMaterials.AMETHYST, 144, Items.AMETHYST_SHARD);
    public static final Item COPPER_SHEEP_ARMOR = createSheepArmorItem(SheepArmorMaterials.COPPER, 192, Items.COPPER_INGOT);
    public static final Item SHULKER_SHEEP_ARMOR = createSheepArmorItem(SheepArmorMaterials.SHULKER, 320, Items.SHULKER_SHELL);

    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.WOLF_ARMOR, CACTUS_SHEEP_ARMOR, AMETHYST_SHEEP_ARMOR, COPPER_SHEEP_ARMOR, SHULKER_SHEEP_ARMOR);
        });
    }
}
