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
import net.minecraft.registry.tag.TagKey;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
//                .component(DataComponentTypes.EQUIPPABLE, new EquippableComponent(EquipmentSlot.BODY, SoundEvents.ENTITY_HORSE_ARMOR, Optional.empty(), Optional.empty(), Optional.of(RegistryEntryList.of(EntityType::getRegistryEntry, EntityType.SHEEP)), true, false, false));
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

//            addMaxLevelEnchantedBooks(entries, set, ItemGroup.StackVisibility.PARENT_TAB_ONLY);
//            addAllLevelEnchantedBooks(entries, set, ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
        });
    }
//    private static void addMaxLevelEnchantedBooks(ItemGroup.Entries entries, Set<TagKey<Item>> tags, ItemGroup.StackVisibility visibility) {
//        Registries.ENCHANTMENT.stream().filter((enchantment) ->
//                tags.contains(enchantment.getApplicableItems())).map((enchantment) ->
//                EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, enchantment.getMaxLevel()))).forEach((stack) ->
//                entries.add(stack, visibility));
//    }
//    private static void addAllLevelEnchantedBooks(ItemGroup.Entries entries, Set<TagKey<Item>> tags, ItemGroup.StackVisibility visibility) {
//        Registries.ENCHANTMENT.stream().filter((enchantment) ->
//                tags.contains(enchantment.getApplicableItems())).flatMap((enchantment) ->
//                IntStream.rangeClosed(enchantment.getMinLevel(), enchantment.getMaxLevel()).mapToObj((level) ->
//                        EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(enchantment, level)))).forEach((stack) ->
//                entries.add(stack, visibility));
//    }
}
