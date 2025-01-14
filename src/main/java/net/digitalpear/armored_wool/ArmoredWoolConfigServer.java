package net.digitalpear.armored_wool;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.digitalpear.armored_wool.init.AWItems;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;

import java.util.Map;

@Config(name = ArmoredWool.MOD_ID + "_server")
public class ArmoredWoolConfigServer implements ConfigData {
    @ConfigEntry.Category("sheep_variant")
    @ConfigEntry.Gui.Tooltip()
    public boolean hasVariants = true;

    @ConfigEntry.Category("sheep_variant")
    @ConfigEntry.Gui.Tooltip()
    public boolean universalBarn = false;

    @ConfigEntry.Category("sheep_variant")
    @ConfigEntry.Gui.Tooltip()
    public boolean hasCustomColors = true;

    @ConfigEntry.Category("sheep_armor")
    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Gui.RequiresRestart()
    public boolean sheepArmorEnabled = true;

    @ConfigEntry.Category("sheep_armor")
    @ConfigEntry.Gui.Tooltip()
    public float thornyArmorDamage = 2.0f;

    @ConfigEntry.Category("sheep_armor")
    @ConfigEntry.Gui.Tooltip()
    @ConfigEntry.Gui.Excluded
    public Map<String, String> lootTableAdditions = Map.ofEntries(
            Map.entry(getId(LootTables.END_CITY_TREASURE_CHEST), getId(AWItems.SHULKER_SHEEP_ARMOR)),
            Map.entry(getId(LootTables.DESERT_PYRAMID_CHEST), getId(AWItems.CACTUS_SHEEP_ARMOR)),
            Map.entry(getId(LootTables.ANCIENT_CITY_CHEST), getId(AWItems.AMETHYST_SHEEP_ARMOR)),
            Map.entry(getId(LootTables.STRONGHOLD_CORRIDOR_CHEST), getId(AWItems.AMETHYST_SHEEP_ARMOR))
    );

    private static String getId(Item item){
        return Registries.ITEM.getId(item).toString();
    }
    private static String getId(RegistryKey<LootTable> lootTable){
        return lootTable.getValue().toString();
    }
}
