package net.digitalpear.sheep_armor;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;


@Config(name = SheepArmor.MOD_ID)
public class SheepArmorConfig implements ConfigData {


    @ConfigEntry.Category("sheep_variant")
    @ConfigEntry.Gui.Tooltip()
    public boolean hasVariants = true;

    @ConfigEntry.Category("sheep_variant")
    @ConfigEntry.Gui.Tooltip()
    public boolean hasCustomColors = true;

    @ConfigEntry.Category("sheep_variant")
    @ConfigEntry.Gui.Tooltip()
    public boolean hasInnerColoring = true;

    @ConfigEntry.Category("sheep_variant")
    @ConfigEntry.Gui.Tooltip()
    public boolean universalBarn = false;

    @ConfigEntry.Category("sheep_armor")
    @ConfigEntry.Gui.Tooltip()
    public boolean sheepArmorEnabled = true;
}

