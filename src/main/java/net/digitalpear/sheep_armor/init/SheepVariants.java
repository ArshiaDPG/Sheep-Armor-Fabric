package net.digitalpear.sheep_armor.init;

import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.entity.SARegistryKeys;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.common.entity.WoolColorEntry;
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
import java.util.Arrays;
import java.util.List;

public class SheepVariants {

    public static List<RegistryKey<SheepVariant>> variants = new ArrayList<>();

    public static final RegistryKey<SheepVariant> BARN = of("barn");
    public static final RegistryKey<SheepVariant> ROCKY = of("rocky");
    public static final RegistryKey<SheepVariant> REGAL = of("regal");
    public static final RegistryKey<SheepVariant> SOOT = of("soot");
    public static final RegistryKey<SheepVariant> GLOOMY = of("gloomy");


    public static void bootstrap(Registerable<SheepVariant> registry) {
        register(registry, BARN, WoolColorEntry.DEFAULT_SHEEP_COLORS, BiomeTags.IS_OVERWORLD);
        register(registry, ROCKY, SheepColors.ROCKY, SATags.SABiomeTags.SPAWNS_ROCKY_SHEEP);
        register(registry, REGAL, SheepColors.REGAL, SATags.SABiomeTags.SPAWNS_REGAL_SHEEP);
        register(registry, SOOT, SheepColors.SOOT, SATags.SABiomeTags.SPAWNS_SOOT_SHEEP);
        register(registry, GLOOMY, SheepColors.GLOOMY, SATags.SABiomeTags.SPAWNS_GLOOMY_SHEEP);
    }


    public static RegistryEntry<SheepVariant> fromBiome(DynamicRegistryManager dynamicRegistryManager, RegistryEntry<Biome> biome, Random random) {
        Registry<SheepVariant> registry = dynamicRegistryManager.getOrThrow(SARegistryKeys.SHEEP_VARIANT);
        List<RegistryEntry.Reference<SheepVariant>> entries = new ArrayList<>(registry.streamEntries().filter(entry -> entry.value() != registry.get(BARN)).filter(entry -> entry.value().getBiomes().contains(biome)).toList());
        if (entries.isEmpty()){
            return registry.getOrThrow(BARN);
        }
        if (SheepArmor.CommonConfig.universalBarn.getValue() && !entries.contains(registry.getOrThrow(BARN))){
            entries.add(registry.getOrThrow(BARN));
        }
        return entries.get(random.nextInt(entries.size()));
    }

    private static RegistryKey<SheepVariant> of(String id) {
        RegistryKey<SheepVariant> variant = RegistryKey.of(SARegistryKeys.SHEEP_VARIANT, SheepArmor.id(id));
        variants.add(variant);
        return variant;
    }

    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, List<WoolColorEntry> colors, TagKey<Biome> biomeTag) {
        register(registry, key, key.getValue(), colors, registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biomeTag));
    }
    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, Identifier texturePath, List<WoolColorEntry> colors, TagKey<Biome> biomeTag) {
        register(registry, key, texturePath, colors, registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biomeTag));
    }
    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, Identifier texturePath, List<WoolColorEntry> colors, RegistryEntryList<Biome> biomes) {
        registry.register(key, new SheepVariant(texturePath.withPrefixedPath(SheepVariant.SHEEP_TEXTURE_PATH), colors, biomes));
    }


    public static void init(){

    }

    public static class SheepColors {
        public static final List<WoolColorEntry> REGAL = woolColorList(
                new WoolColorEntry(DyeColor.BLACK, 5),
                new WoolColorEntry(DyeColor.GRAY, 5),
                new WoolColorEntry(DyeColor.LIGHT_GRAY, 5),
                new WoolColorEntry(DyeColor.LIME, 3),
                new WoolColorEntry(DyeColor.PINK, 1),
                new WoolColorEntry(DyeColor.WHITE, 481)
        );
        public static final List<WoolColorEntry> ROCKY = woolColorList(
                new WoolColorEntry(DyeColor.BROWN, 481),
                new WoolColorEntry(DyeColor.GRAY, 8),
                new WoolColorEntry(DyeColor.BLACK, 5),
                new WoolColorEntry(DyeColor.WHITE, 5),
                new WoolColorEntry(DyeColor.LIGHT_GRAY, 5),
                new WoolColorEntry(DyeColor.LIGHT_BLUE, 1)
        );
        public static final List<WoolColorEntry> GLOOMY = woolColorList(
                new WoolColorEntry(DyeColor.LIGHT_GRAY, 481),
                new WoolColorEntry(DyeColor.BLUE, 8),
                new WoolColorEntry(DyeColor.GRAY, 5),
                new WoolColorEntry(DyeColor.LIGHT_BLUE, 5),
                new WoolColorEntry(DyeColor.CYAN, 3),
                new WoolColorEntry(DyeColor.YELLOW, 1)
        );
        public static final List<WoolColorEntry> SOOT = woolColorList(
                new WoolColorEntry(DyeColor.GRAY, 481),
                new WoolColorEntry(DyeColor.LIGHT_GRAY, 8),
                new WoolColorEntry(DyeColor.WHITE, 5),
                new WoolColorEntry(DyeColor.BLACK, 3),
                new WoolColorEntry(DyeColor.GREEN, 1)
        );

        public static List<WoolColorEntry> woolColorList(WoolColorEntry... entries){
            return new ArrayList<>(Arrays.asList(entries));
        }
    }
}
