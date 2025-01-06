package net.digitalpear.armored_wool;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.digitalpear.armored_wool.common.entity.AWRegistryKeys;
import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.digitalpear.armored_wool.init.AWData;
import net.digitalpear.armored_wool.init.AWEnchantments;
import net.digitalpear.armored_wool.init.AWItems;
import net.digitalpear.armored_wool.init.SheepVariants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ArmoredWool implements ModInitializer {

    /*
        Sheep armor code is taken and altered from https://github.com/ekulxam/axolotl-amour
        Sheep variant mixin code is taken and altered from https://github.com/Wolren/WolfPort/tree/main_1.20.1
        The Sheep Variant code itself was done by me.
     */
    public static final TrackedDataHandler<RegistryEntry<SheepVariant>> SHEEP_VARIANT = TrackedDataHandler.create(PacketCodecs.registryEntry(AWRegistryKeys.SHEEP_VARIANT));
    public static final String MOD_ID = "armored_wool";
    public static final String MOD_NAME = "Armored Wool";

    public static Identifier id(String name){
        return Identifier.of(MOD_ID, name);
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /*
        SNAPSHOT 2 CHANGELOG:
            -Pale sheep no longer have a chance of always spawning when some other variant can spawn instead.
            -Added proper textures for all armor models and items.
            -Trimming and Woolsplosion now appear in the creative inventory.

        SNAPSHOT 3 CHANGELOG:
            -New item textures by Tirdul
            -It is now possible to add new sheep variants using datapacks.
            -As a side effect, the sheep variant predicate system has been replaced by specifying which biomes you want, like how it is done with wolves.
            -It is now possible to give sheep variants custom wool textures as well.

        SNAPSHOT 4 CHANGELOG:
            -Updated to 1.21.4.
            -Textures are now set by assigning the paths to them. Leaving them empty will default to the vanilla texture.
            -Amethyst armor now has less durability than copper armor. Copper also has less durability than before.
            -Sheep armor model is now slightly inflated like other armors.
            -Scrapped MCE sheep variants in favor of variants that better visually fit the vanilla game.
            -Sheep armor can now have an enchant glint.
            -Sheep armors can now be enchanted with unbreaking and mending.

        SNAPSHOT 5 CHANGELOG:
            -Barn Sheep now once again use the vanilla sheep texture.
            -Added gloomy sheep variant that spawns in horror themed biomes.
            -Renamed Mountain sheep to Rocky sheep.
            -Sheep variants now include a weighted list which allows customization of wool colors.
            -Sheep's inner wool layer is now colored like the wool.
            -Regal, Soot and Rocky sheep now have textures thanks to KattZZi.

        SNAPSHOT 6 CHANGELOG:
            -Weights for wool colors no longer has a limit.
            -Added some config options using Config API (https://modrinth.com/mod/config-api).
            -Added clifftree compat.
            -Added Easter egg when naming sheep "Gwen".
            -Gloomy sheep now has textures thanks to KattZZi.

        SNAPSHOT 7 CHANGELOG:
            -Reworked config to use Auto Config API.
            -Added Modmenu integration.
            -Added compat for more mods.
            -Amount of thorns damage from cactus armor can now be set in the config.
            -Loot tables that have horse armor can now be set in the config.
            -Horse armor can now be enchanted and slightly damanged when found in loot tables.
            -Config will now specify whether a setting is for the client or server.
            -Renamed mod to "Armored Wool".
            -Fixed cactus armor item texture being 17x17 instead of 16x16.
            -Sheep armor compatibility is now determined using an entity tag.
     */

    /*
        Colors associated with each enchantment.
        Guide for compat with mods like Colorful Books.
     */
    private static final Map<RegistryKey<Enchantment>, Item> enchantDyes = Map.ofEntries(
            Map.entry(AWEnchantments.TRIMMING, Items.GREEN_DYE),
            Map.entry(AWEnchantments.WOOLSPLOSION, Items.ORANGE_DYE)
    );

    @Override
    public void onInitialize() {
        AutoConfig.register(ArmoredWoolConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new));

        TrackedDataHandlerRegistry.register(SHEEP_VARIANT);

        DynamicRegistries.registerSynced(AWRegistryKeys.SHEEP_VARIANT, SheepVariant.CODEC);

        AWRegistryKeys.init();
        AWItems.init();
        AWData.init();
        AWEnchantments.init();
        SheepVariants.init();
    }


    public static String hasValidName(LivingEntityRenderState state){
        ArmoredWoolConfig config = AutoConfig.getConfigHolder(ArmoredWoolConfig.class).getConfig();
        if (state.customName != null && config.clientConfig.easterEggVariants.containsKey(state.customName.getString())){
            return config.clientConfig.easterEggVariants.get(state.customName.getString());
        }
        return null;
    }
}
