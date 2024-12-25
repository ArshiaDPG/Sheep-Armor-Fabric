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
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class SheepVariants {

    public static List<RegistryKey<SheepVariant>> variants = new ArrayList<>();

    public static final RegistryKey<SheepVariant> BARN = of("barn");
    public static final RegistryKey<SheepVariant> MOUNTAIN = of("mountain");
    public static final RegistryKey<SheepVariant> REGAL = of("regal");
    public static final RegistryKey<SheepVariant> SOOT = of("soot");


    public static void bootstrap(Registerable<SheepVariant> registry) {
        register(registry, BARN);
        register(registry, MOUNTAIN, SATags.SABiomeTags.MOUNTAIN_SHEEP_BIOMES);
        register(registry, REGAL, SATags.SABiomeTags.REGAL_SHEEP_BIOMES);
        register(registry, SOOT, SATags.SABiomeTags.SOOT_SHEEP_BIOMES);
    }


    public static RegistryEntry<SheepVariant> fromBiome(DynamicRegistryManager dynamicRegistryManager, RegistryEntry<Biome> biome, Random random) {
        Registry<SheepVariant> registry = dynamicRegistryManager.getOrThrow(SARegistryKeys.SHEEP_VARIANT);
        List<RegistryEntry.Reference<SheepVariant>> entries = registry.streamEntries().filter((entry) -> entry.value().getBiomes().contains(biome)).toList();
        if (entries.isEmpty()){
            return registry.getOrThrow(BARN);
        }
        return entries.get(random.nextInt(entries.size()));
    }

    private static RegistryKey<SheepVariant> of(String id) {
        RegistryKey<SheepVariant> variant = RegistryKey.of(SARegistryKeys.SHEEP_VARIANT, SheepArmor.id(id));
        variants.add(variant);
        return variant;
    }


    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key) {
        register(registry, key, key.getValue(), registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(BiomeTags.IS_OVERWORLD));
    }
    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, TagKey<Biome> biomeTag) {
        register(registry, key, key.getValue(), registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biomeTag));
    }
    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, Identifier texturePath, RegistryEntryList<Biome> biomes) {
        registry.register(key, new SheepVariant(texturePath.withPrefixedPath(SheepVariant.SHEEP_TEXTURE_PATH), biomes));
    }


    public static void init(){

    }
}
