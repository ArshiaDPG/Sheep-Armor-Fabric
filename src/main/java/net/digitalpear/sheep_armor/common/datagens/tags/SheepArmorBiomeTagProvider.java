package net.digitalpear.sheep_armor.common.datagens.tags;

import net.digitalpear.sheep_armor.init.SATags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class SheepArmorBiomeTagProvider extends FabricTagProvider<Biome> {
    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public SheepArmorBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(SATags.SABiomeTags.SPAWNS_ROCKY_SHEEP)
                .add(BiomeKeys.STONY_PEAKS)
                .add(BiomeKeys.GROVE)
                .add(BiomeKeys.FROZEN_PEAKS)
                .add(BiomeKeys.JAGGED_PEAKS)
                .add(BiomeKeys.STONY_SHORE);

        getOrCreateTagBuilder(SATags.SABiomeTags.SPAWNS_REGAL_SHEEP)
                .add(BiomeKeys.FLOWER_FOREST)
                .add(BiomeKeys.CHERRY_GROVE)
                .add(BiomeKeys.SUNFLOWER_PLAINS)
                .add(BiomeKeys.MEADOW);

        getOrCreateTagBuilder(SATags.SABiomeTags.SPAWNS_SOOT_SHEEP)
                .forceAddTag(BiomeTags.IS_NETHER)
                .addOptionalTag(Identifier.of("clifftree", "inferno"));

        getOrCreateTagBuilder(SATags.SABiomeTags.SPAWNS_GLOOMY_SHEEP)
                .add(BiomeKeys.PALE_GARDEN).add(BiomeKeys.DEEP_DARK);
    }
}
