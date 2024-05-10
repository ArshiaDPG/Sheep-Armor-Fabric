package net.digitalpear.sheep_armor.init;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;

public class SAData {

    public static void registerCompostables(){
        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;

        compostingChanceRegistry.add(SAItems.CACTUS_SHEEP_ARMOR, 0.8f);
    }

    public static void registerLootTables(){
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            if (key == LootTables.DESERT_PYRAMID_CHEST){
                tableBuilder.modifyPools(builder -> {
                    builder.with(ItemEntry.builder(SAItems.CACTUS_SHEEP_ARMOR).weight(5));
                });
            }
            else if (key == LootTables.END_CITY_TREASURE_CHEST){
                tableBuilder.modifyPools(builder -> {
                    builder.with(ItemEntry.builder(SAItems.SHULKER_SHEEP_ARMOR).weight(5));
                });
            }
            else if (key == LootTables.STRONGHOLD_CORRIDOR_CHEST || key == LootTables.ANCIENT_CITY_CHEST){
                tableBuilder.modifyPools(builder -> {
                    builder.with(ItemEntry.builder(SAItems.AMETHYST_SHEEP_ARMOR).weight(4));
                });
            }
        });
    }

    public static void init(){
        registerLootTables();
        registerCompostables();
    }
}
