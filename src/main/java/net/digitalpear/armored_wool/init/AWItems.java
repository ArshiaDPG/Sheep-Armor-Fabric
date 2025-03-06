package net.digitalpear.armored_wool.init;

import net.digitalpear.armored_wool.ArmoredWool;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class AWItems {
    public static Map<Item, Item> SHEEP_ARMOR_MAP = new HashMap<>();
    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, ArmoredWool.id(id));
    }
    private static Item createItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings){
        return Items.register(keyOf(name), factory, settings);
    }


    public static void init(){

    }
}
