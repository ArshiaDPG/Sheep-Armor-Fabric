package net.digitalpear.sheep_armor.init;

import me.shedaniel.autoconfig.AutoConfig;
import net.digitalpear.sheep_armor.SheepArmorConfig;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.Map;

public class SAData {

    public static void registerCompostables(){
        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;

        compostingChanceRegistry.add(SAItems.CACTUS_SHEEP_ARMOR, 0.8f);
    }

    public static void registerLootTables(){
        SheepArmorConfig config = AutoConfig.getConfigHolder(SheepArmorConfig.class).getConfig();
        if (!config.lootTableAdditions.isEmpty()){
            LootTableEvents.MODIFY.register((registryKey, tableBuilder, lootTableSource, wrapperLookup) -> {
                for (Map.Entry<String, String> entry : config.lootTableAdditions.entrySet()) {
                    RegistryKey<LootTable> lootTableRegistryKey = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(entry.getKey()));
                    Item item = Registries.ITEM.get(Identifier.of(entry.getValue()));
                    if (registryKey == lootTableRegistryKey) {
                        tableBuilder.modifyPools(builder -> {
                            builder.with(ItemEntry.builder(item).weight(5).apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(0.8F, 1.0F))).apply(EnchantRandomlyLootFunction.builder(wrapperLookup)));
                        });
                    }
                }
            });
        }
    }


    public static void init(){
        registerLootTables();
        registerCompostables();
    }
}
