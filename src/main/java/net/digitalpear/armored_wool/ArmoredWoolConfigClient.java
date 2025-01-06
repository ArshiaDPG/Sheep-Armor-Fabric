package net.digitalpear.armored_wool;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.Map;

@Config(name = ArmoredWool.MOD_ID + "_client")
public class ArmoredWoolConfigClient implements ConfigData {
    @ConfigEntry.Category("sheep_variant")
    @ConfigEntry.Gui.Tooltip()
    public boolean hasInnerColoring = true;

    @ConfigEntry.Category("sheep_variant")
    @ConfigEntry.Gui.Tooltip()
    public Map<String, String> easterEggVariants = Map.ofEntries(
            Map.entry("Gwen", "flecked")
    );
}
