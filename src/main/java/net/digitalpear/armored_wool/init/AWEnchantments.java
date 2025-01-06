package net.digitalpear.armored_wool.init;

import net.digitalpear.armored_wool.ArmoredWool;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.ArrayList;
import java.util.List;

public class AWEnchantments {

    public static List<RegistryKey<Enchantment>> enchantments = new ArrayList<>();

    public static final RegistryKey<Enchantment> TRIMMING = of("trimming");
    public static final RegistryKey<Enchantment> WOOLSPLOSION = of("woolsplosion");
//    public static final RegistryKey<Enchantment> LIGHTNESS = of("lightness");

    private static RegistryKey<Enchantment> of(String id) {
        RegistryKey<Enchantment> enchantment = RegistryKey.of(RegistryKeys.ENCHANTMENT, ArmoredWool.id(id));
        enchantments.add(enchantment);
        return enchantment;
    }

    public static void bootstrap(Registerable<Enchantment> registry) {
        RegistryEntryLookup<Item> itemLookup = registry.getRegistryLookup(RegistryKeys.ITEM);
        register(registry, TRIMMING, sheepEnchantmentProperties(itemLookup, 2, 3, Enchantment.leveledCost(1, 11), Enchantment.leveledCost(12, 11), 4));
        register(registry, WOOLSPLOSION, sheepEnchantmentProperties(itemLookup, 3, 4, Enchantment.leveledCost(1, 11), Enchantment.leveledCost(18, 11), 6));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }

    private static Enchantment.Builder sheepEnchantmentProperties(RegistryEntryLookup<Item> itemLookup, int weight, int maxLevel, Enchantment.Cost minCost, Enchantment.Cost maxCost, int anvilCost, AttributeModifierSlot... slots){
        return Enchantment.builder(Enchantment.definition(itemLookup.getOrThrow(AWTags.AWItemTags.SHEEP_ARMORS), weight, maxLevel, minCost, maxCost, anvilCost, slots));
    }
    private static Enchantment.Builder sheepEnchantmentProperties(RegistryEntryLookup<Item> itemLookup, int weight, int maxLevel, Enchantment.Cost minCost, Enchantment.Cost maxCost, int anvilCost){
        return sheepEnchantmentProperties(itemLookup, weight, maxLevel, minCost, maxCost, anvilCost, AttributeModifierSlot.BODY);
    }

    public static void init(){}

}
