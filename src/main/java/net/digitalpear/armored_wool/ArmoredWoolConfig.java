package net.digitalpear.armored_wool;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;


@Config(name = ArmoredWool.MOD_ID)
@Config.Gui.Background("minecraft:textures/block/white_wool.png")
public class ArmoredWoolConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category(ArmoredWool.MOD_ID + "_client")
    @ConfigEntry.Gui.TransitiveObject
    public ArmoredWoolConfigClient clientConfig = new ArmoredWoolConfigClient();

    @ConfigEntry.Category(ArmoredWool.MOD_ID + "_server")
    @ConfigEntry.Gui.TransitiveObject
    public ArmoredWoolConfigServer serverConfig = new ArmoredWoolConfigServer();
}

