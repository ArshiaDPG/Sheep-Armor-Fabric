package net.digitalpear.sheep_armor;

import net.digitalpear.sheep_armor.common.entity.SARegistryKeys;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.init.SAData;
import net.digitalpear.sheep_armor.init.SAEnchantments;
import net.digitalpear.sheep_armor.init.SAItems;
import net.digitalpear.sheep_armor.init.SheepVariants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SheepArmor implements ModInitializer {

    /*
        Sheep armor code is taken and altered from https://github.com/ekulxam/axolotl-amour
        Sheep variant mixin code is taken and altered from https://github.com/Wolren/WolfPort/tree/main_1.20.1
        The Sheep Variant code itself was done by me.
     */


    public static final TrackedDataHandler<RegistryEntry<SheepVariant>> SHEEP_VARIANT = TrackedDataHandler.create(PacketCodecs.registryEntry(SARegistryKeys.SHEEP_VARIANT));
    public static final String MOD_ID = "sheep_armor";

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

    }
}
