package net.digitalpear.sheep_armor.init;

import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.items.SheepArmorItem;
import net.digitalpear.sheep_armor.common.items.SheepArmorMaterials;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class SAItems {
    public static Map<Item, Item> SHEEP_ARMOR_MAP = new HashMap<>();

    private static Item createItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(SheepArmor.MOD_ID, name), item);
    }

    private static Item createSheepArmorItem(String materialName, RegistryEntry<ArmorMaterial> material, Item ingredient, int durability){
        Item item = createItem(materialName + "_sheep_armor", new SheepArmorItem(material, new Item.Settings().maxDamage(durability).maxCount(1)));
        SHEEP_ARMOR_MAP.put(item, ingredient);
        return item;
    }

    public static final Item CACTUS_SHEEP_ARMOR = createSheepArmorItem("cactus", SheepArmorMaterials.CACTUS, Items.CACTUS, 35);
    public static final Item AMETHYST_SHEEP_ARMOR = createSheepArmorItem("amethyst", SheepArmorMaterials.AMETHYST, Items.AMETHYST_SHARD, 75);
    public static final Item COPPER_SHEEP_ARMOR = createSheepArmorItem("copper", SheepArmorMaterials.COPPER, Items.COPPER_INGOT, 125);
    public static final Item SHULKER_SHEEP_ARMOR = createSheepArmorItem("shulker", SheepArmorMaterials.SHULKER, Items.SHULKER_SHELL, 365);


    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.WOLF_ARMOR, CACTUS_SHEEP_ARMOR, AMETHYST_SHEEP_ARMOR, COPPER_SHEEP_ARMOR, SHULKER_SHEEP_ARMOR);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            Set<TagKey<Item>> set = Set.of(SATags.SAItemTags.SHEEP_ARMORS);

            addMaxLevelEnchantedBooks(entries, set, ItemGroup.StackVisibility.PARENT_TAB_ONLY);
            addAllLevelEnchantedBooks(entries, set, ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
        });
    }
    private static void addMaxLevelEnchantedBooks(ItemGroup.Entries entries, Set<TagKey<Item>> tags, ItemGroup.StackVisibility visibility) {
        Registries.ENCHANTMENT.stream().filter((enchantment) ->
                tags.contains(enchantment.getApplicableItems())).map((enchantment) ->
                EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, enchantment.getMaxLevel()))).forEach((stack) ->
                entries.add(stack, visibility));
    }
    private static void addAllLevelEnchantedBooks(ItemGroup.Entries entries, Set<TagKey<Item>> tags, ItemGroup.StackVisibility visibility) {
        Registries.ENCHANTMENT.stream().filter((enchantment) ->
                tags.contains(enchantment.getApplicableItems())).flatMap((enchantment) ->
                IntStream.rangeClosed(enchantment.getMinLevel(), enchantment.getMaxLevel()).mapToObj((level) ->
                        EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, level)))).forEach((stack) ->
                entries.add(stack, visibility));
    }
}
