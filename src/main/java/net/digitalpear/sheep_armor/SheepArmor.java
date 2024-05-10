package net.digitalpear.sheep_armor;

import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.common.entity.SheepVariantRegistry;
import net.digitalpear.sheep_armor.init.SAData;
import net.digitalpear.sheep_armor.init.SAEnchantments;
import net.digitalpear.sheep_armor.init.SAItems;
import net.digitalpear.sheep_armor.init.SheepVariants;
import net.fabricmc.api.ModInitializer;
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


    public static final TrackedDataHandler<RegistryEntry<SheepVariant>> SHEEP_VARIANT = TrackedDataHandler.create(PacketCodecs.registryEntry(SheepVariantRegistry.SHEEP_VARIANT.getKey()));
    public static final String MOD_ID = "sheep_armor";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /*
        SNAPSHOT 2 CHANGELOG:
            -Pale sheep no longer have a chance of always spawning when some other variant can spawn instead.
            -Added proper textures for all armor models and items.
            -Trimming and Woolsplosion now appear in the creative inventory.
     */

    @Override
    public void onInitialize() {
        TrackedDataHandlerRegistry.register(SHEEP_VARIANT);

        SAItems.init();
        SAData.init();
        SAEnchantments.init();
        SheepVariants.init();
    }
}
