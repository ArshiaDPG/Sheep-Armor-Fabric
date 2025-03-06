package net.digitalpear.armored_wool.init;

import net.digitalpear.armored_wool.ArmoredWool;
import net.digitalpear.armored_wool.common.entity.AWRegistryKeys;
import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.minecraft.entity.VariantSelectorProvider;
import net.minecraft.entity.spawn.BiomeSpawnCondition;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SheepVariants {

    public static List<RegistryKey<SheepVariant>> variants = new ArrayList<>();

    public static final RegistryKey<SheepVariant> BARN = of("barn");
    public static final RegistryKey<SheepVariant> ROCKY = of("rocky");
    public static final RegistryKey<SheepVariant> REGAL = of("regal");
    public static final RegistryKey<SheepVariant> SOOT = of("soot");
    public static final RegistryKey<SheepVariant> GLOOMY = of("gloomy");


    public static void bootstrap(Registerable<SheepVariant> registry) {
        register(registry, BARN, Identifier.ofVanilla("sheep"), SheepColors.DEFAULT_SHEEP_COLORS, SpawnConditionSelectors.createFallback(0));
        register(registry, ROCKY, SheepColors.ROCKY, AWTags.AWBiomeTags.SPAWNS_ROCKY_SHEEP);
        register(registry, REGAL, SheepColors.REGAL, AWTags.AWBiomeTags.SPAWNS_REGAL_SHEEP);
        register(registry, SOOT, SheepColors.SOOT, AWTags.AWBiomeTags.SPAWNS_SOOT_SHEEP);
        register(registry, GLOOMY, SheepColors.GLOOMY, AWTags.AWBiomeTags.SPAWNS_GLOOMY_SHEEP);
    }


    public static Optional<RegistryEntry.Reference<SheepVariant>> select(Random random, DynamicRegistryManager registries, SpawnContext context) {
        return VariantSelectorProvider.select(registries.getOrThrow(AWRegistryKeys.SHEEP_VARIANT).streamEntries(), RegistryEntry::value, random, context);
    }

    private static RegistryKey<SheepVariant> of(String id) {
        RegistryKey<SheepVariant> variant = RegistryKey.of(AWRegistryKeys.SHEEP_VARIANT, ArmoredWool.id(id));
        variants.add(variant);
        return variant;
    }

    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, Pool<DyeColor> colors, TagKey<Biome> biomeTag) {
        register(registry, key, key.getValue(), colors, SpawnConditionSelectors.createSingle(new BiomeSpawnCondition(registry.getRegistryLookup(RegistryKeys.BIOME).getOrThrow(biomeTag)), 1));
    }
    static void register(Registerable<SheepVariant> registry, RegistryKey<SheepVariant> key, Identifier texturePath, Pool<DyeColor> colors, SpawnConditionSelectors spawnConditionSelectors) {
        registry.register(key, new SheepVariant(texturePath.withPrefixedPath(SheepVariant.SheepAssets.SHEEP_TEXTURE_PATH), colors, spawnConditionSelectors));
    }

    public static void init(){}

    public static class SheepColors {
        public static final Pool<DyeColor> DEFAULT_SHEEP_COLORS = new Pool.Builder<DyeColor>()
                .add(DyeColor.BLACK, 5)
                .add(DyeColor.GRAY, 5)
                .add(DyeColor.LIGHT_GRAY, 5)
                .add(DyeColor.BROWN, 3)
                .add(DyeColor.PINK, 1)
                .add(DyeColor.WHITE, 481).build();
        public static final Pool<DyeColor> REGAL = new Pool.Builder<DyeColor>()
                .add(DyeColor.BLACK, 5)
                .add(DyeColor.GRAY, 5)
                .add(DyeColor.LIGHT_GRAY, 5)
                .add(DyeColor.LIME, 3)
                .add(DyeColor.PINK, 1)
                .add(DyeColor.WHITE, 481).build();
        public static final Pool<DyeColor> ROCKY = new Pool.Builder<DyeColor>()
                .add(DyeColor.BROWN, 481)
                .add(DyeColor.GRAY, 8)
                .add(DyeColor.BLACK, 5)
                .add(DyeColor.WHITE, 5)
                .add(DyeColor.LIGHT_GRAY, 5)
                .add(DyeColor.LIGHT_BLUE, 1).build();
        public static final Pool<DyeColor> GLOOMY = new Pool.Builder<DyeColor>()
                .add(DyeColor.LIGHT_GRAY, 481)
                .add(DyeColor.BLUE, 8)
                .add(DyeColor.GRAY, 5)
                .add(DyeColor.LIGHT_BLUE, 5)
                .add(DyeColor.CYAN, 3)
                .add(DyeColor.YELLOW, 1).build();

        public static final Pool<DyeColor> SOOT = new Pool.Builder<DyeColor>()
                .add(DyeColor.GRAY, 481)
                .add(DyeColor.LIGHT_GRAY, 8)
                .add(DyeColor.WHITE, 5)
                .add(DyeColor.BLACK, 3)
                .add(DyeColor.GREEN, 1).build();

    }
}
