package net.digitalpear.sheep_armor.init;

import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.common.entity.SARegistryKeys;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class SheepVariants {

    public static List<RegistryKey<SheepVariant>> variants = new ArrayList<>();

    public static final RegistryKey<SheepVariant> BARN = of("barn");
    public static final RegistryKey<SheepVariant> ROCKY = of("rocky");
    public static final RegistryKey<SheepVariant> REGAL = of("regal");
    public static final RegistryKey<SheepVariant> SOOT = of("soot");
    public static final RegistryKey<SheepVariant> GLOOMY = of("gloomy");


    public static void bootstrap(Registerable<SheepVariant> registry) {
        register(registry, BARN, SheepVariant.VANILLA_SHEEP_TEXTURE, DyeColor.WHITE, BiomeTags.IS_OVERWORLD);
        register(registry, ROCKY, DyeColor.LIGHT_GRAY, SATags.SABiomeTags.SPAWNS_ROCKY_SHEEP);
        register(registry, REGAL, DyeColor.WHITE, SATags.SABiomeTags.SPAWNS_REGAL_SHEEP);
        register(registry, SOOT, DyeColor.GRAY, SATags.SABiomeTags.SPAWNS_SOOT_SHEEP);
        register(registry, GLOOMY, DyeColor.CYAN, SATags.SABiomeTags.SPAWNS_GLOOMY_SHEEP);
    }


    public static RegistryEntry<SheepVariant> fromBiome(DynamicRegistryManager dynamicRegistryManager, RegistryEntry<Biome> biome, Random random) {
        Registry<SheepVariant> registry = dynamicRegistryManager.getOrThrow(SARegistryKeys.SHEEP_VARIANT);
        List<RegistryEntry.Reference<SheepVariant>> entries = registry.streamEntries().filter(entry -> entry.value() != registry.get(BARN)).filter(entry -> entry.value().getBiomes().contains(biome)).toList();
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

    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, DyeColor defaultColor, TagKey<Biome> biomeTag) {
        register(registry, key, key.getValue(), defaultColor, registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biomeTag));
    }
    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, Identifier texturePath, DyeColor defaultColor, TagKey<Biome> biomeTag) {
        register(registry, key, texturePath, defaultColor, registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biomeTag));
    }
    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, Identifier texturePath, DyeColor defaultColor, RegistryEntryList<Biome> biomes) {
        registry.register(key, new SheepVariant(texturePath.withPrefixedPath(SheepVariant.SHEEP_TEXTURE_PATH), new SheepVariant.SheepColor(defaultColor), biomes));
    }


    public static void init(){

    }
}
