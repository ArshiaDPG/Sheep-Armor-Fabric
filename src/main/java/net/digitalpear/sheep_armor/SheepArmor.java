package net.digitalpear.sheep_armor;

import com.google.common.collect.ImmutableBiMap;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.digitalpear.sheep_armor.common.entity.SARegistryKeys;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.init.SAData;
import net.digitalpear.sheep_armor.init.SAEnchantments;
import net.digitalpear.sheep_armor.init.SAItems;
import net.digitalpear.sheep_armor.init.SheepVariants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SheepArmor implements ModInitializer {

    /*
        Sheep armor code is taken and altered from https://github.com/ekulxam/axolotl-amour
        Sheep variant mixin code is taken and altered from https://github.com/Wolren/WolfPort/tree/main_1.20.1
        The Sheep Variant code itself was done by me.
     */


    public static final ImmutableBiMap<String, String> SPECIAL_NAMES = new ImmutableBiMap.Builder<String, String>()
            .put("Gwen", "flecked")
            .build();

    public static final TrackedDataHandler<RegistryEntry<SheepVariant>> SHEEP_VARIANT = TrackedDataHandler.create(PacketCodecs.registryEntry(SARegistryKeys.SHEEP_VARIANT));
    public static final String MOD_ID = "sheep_armor";

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
            -Added easter egg when naming sheep "Gwen".
            -Gloomy sheep now has textures thanks to KattZZi.
        SNAPSHOT 7 CHANGELOG:
            -Reworked config to use Auto Config API.
     */


    @Override
    public void onInitialize() {
        TrackedDataHandlerRegistry.register(SHEEP_VARIANT);

        DynamicRegistries.registerSynced(SARegistryKeys.SHEEP_VARIANT, SheepVariant.CODEC);

        SARegistryKeys.init();
        SAItems.init();
        SAData.init();
        SAEnchantments.init();
        SheepVariants.init();

        AutoConfig.register(SheepArmorConfig.class, GsonConfigSerializer::new);
    }


    public static String hasValidName(LivingEntityRenderState state){
        if (state.customName != null && SPECIAL_NAMES.containsKey(state.customName.getString())){
            return SPECIAL_NAMES.get(state.customName.getString());
        }
        return null;
    }
}
