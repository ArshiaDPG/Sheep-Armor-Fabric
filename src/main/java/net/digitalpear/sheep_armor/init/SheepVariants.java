package net.digitalpear.sheep_armor.init;

import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.common.entity.SheepVariantRegistry;
import net.fabricmc.loader.api.FabricLoader;

public class SheepVariants {

    public static final SheepVariant PALE = SheepVariantRegistry.register("pale");
    public static final SheepVariant LONG_NOSED = SheepVariantRegistry.registerBiomeSpecific("long_nosed", SABiomeTags.LONG_NOSED_BIOMES);
    public static final SheepVariant FUZZY = SheepVariantRegistry.registerBiomeSpecific("fuzzy", SABiomeTags.FUZZY_BIOMES);
    public static final SheepVariant PATCHED = SheepVariantRegistry.registerBiomeSpecific("patched", SABiomeTags.PATCHED_BIOMES);
    public static final SheepVariant ROCKY = SheepVariantRegistry.registerBiomeSpecific("rocky", SABiomeTags.ROCKY_BIOMES);
    public static final SheepVariant INKY = SheepVariantRegistry.registerBiomeSpecific("inky", SABiomeTags.INKY_BIOMES);
    public static final SheepVariant FLECKED = SheepVariantRegistry.registerBiomeSpecific("flecked", SABiomeTags.FLECKED_BIOMES);



    public static void init(){
        if (FabricLoader.getInstance().isDevelopmentEnvironment()){
            SheepVariantRegistry.SHEEP_VARIANT.stream().iterator().forEachRemaining(variant -> {
                SheepArmor.LOGGER.info("Registered sheep variant: [" + variant.getName().toString() + "].");
            });
        }
    }
}
