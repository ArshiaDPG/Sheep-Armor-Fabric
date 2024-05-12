package net.digitalpear.sheep_armor.init;

import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.common.entity.SARegistryKeys;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class SheepVariants {

    public static List<RegistryKey<SheepVariant>> variants = new ArrayList<>();

    public static final RegistryKey<SheepVariant> PALE = of("pale");
    public static final RegistryKey<SheepVariant> LONG_NOSED = of("long_nosed");
    public static final RegistryKey<SheepVariant> FUZZY = of("fuzzy");
    public static final RegistryKey<SheepVariant> PATCHED = of("patched");
    public static final RegistryKey<SheepVariant> ROCKY = of("rocky");
    public static final RegistryKey<SheepVariant> INKY = of("inky");
    public static final RegistryKey<SheepVariant> FLECKED = of("flecked");

    public static void bootstrap(Registerable<SheepVariant> registry) {
        register(registry, PALE, BiomeTags.IS_OVERWORLD);
        register(registry, LONG_NOSED, SATags.SABiomeTags.LONG_NOSED_BIOMES);
        register(registry, FUZZY, SATags.SABiomeTags.FUZZY_BIOMES);
        register(registry, PATCHED, SATags.SABiomeTags.PATCHED_BIOMES);
        register(registry, ROCKY, SATags.SABiomeTags.ROCKY_BIOMES);
        register(registry, INKY, SATags.SABiomeTags.INKY_BIOMES);
        register(registry, FLECKED, SATags.SABiomeTags.FLECKED_BIOMES);
    }
    public static RegistryEntry<SheepVariant> fromBiome(DynamicRegistryManager dynamicRegistryManager, RegistryEntry<Biome> biome) {
        Registry<SheepVariant> registry = dynamicRegistryManager.get(SARegistryKeys.SHEEP_VARIANT);
        return registry.streamEntries().filter((entry) -> entry.value().getBiomes().contains(biome)).findFirst().orElse(registry.entryOf(PALE));
    }
    private static RegistryKey<SheepVariant> of(String id) {
        RegistryKey<SheepVariant> variant = RegistryKey.of(SARegistryKeys.SHEEP_VARIANT, new Identifier(SheepArmor.MOD_ID, id));
        variants.add(variant);
        return variant;
    }

    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, RegistryKey<Biome> biome) {
        register(registry, key, key.getValue(), RegistryEntryList.of(registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biome)));
    }

    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, TagKey<Biome> biomeTag) {
        register(registry, key, key.getValue(), registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biomeTag));
    }
    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, Identifier variantName, RegistryEntryList<Biome> biomes) {
        registry.register(key, new SheepVariant(variantName, false, biomes));
    }


    public static void init(){

    }
}
