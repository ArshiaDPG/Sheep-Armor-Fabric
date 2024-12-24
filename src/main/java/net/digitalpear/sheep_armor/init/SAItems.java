package net.digitalpear.sheep_armor.init;

import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.items.SheepArmorItem;
import net.digitalpear.sheep_armor.common.items.SheepArmorMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class SAItems {
    public static Map<Item, Item> SHEEP_ARMOR_MAP = new HashMap<>();
    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, SheepArmor.id(id));
    }
    private static Item createItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings){
        return Items.register(keyOf(name), factory, settings);
    }

    private static Item createSheepArmorItem(String materialName, ArmorMaterial material, Item ingredient, int durability){
        Item item = createItem(materialName + "_sheep_armor",settings ->
                new SheepArmorItem(materialName, material, settings),
                sheepArmorSettings());
        SHEEP_ARMOR_MAP.put(item, ingredient);
        return item;
    }

    private static Item.Settings sheepArmorSettings(){
        return new Item.Settings()
                .maxCount(1);
    }


    public static final Item CACTUS_SHEEP_ARMOR = createSheepArmorItem("cactus", SheepArmorMaterials.CACTUS, Items.CACTUS, 35);
    public static final Item AMETHYST_SHEEP_ARMOR = createSheepArmorItem("amethyst", SheepArmorMaterials.AMETHYST, Items.AMETHYST_SHARD, 75);
    public static final Item COPPER_SHEEP_ARMOR = createSheepArmorItem("copper", SheepArmorMaterials.COPPER, Items.COPPER_INGOT, 125);
    public static final Item SHULKER_SHEEP_ARMOR = createSheepArmorItem("shulker", SheepArmorMaterials.SHULKER, Items.SHULKER_SHELL, 365);


    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.WOLF_ARMOR, CACTUS_SHEEP_ARMOR, AMETHYST_SHEEP_ARMOR, COPPER_SHEEP_ARMOR, SHULKER_SHEEP_ARMOR);
        });
    }
}
