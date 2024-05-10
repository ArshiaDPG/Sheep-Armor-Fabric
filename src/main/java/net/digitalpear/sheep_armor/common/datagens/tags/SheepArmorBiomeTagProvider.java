package net.digitalpear.sheep_armor.common.datagens.tags;

import net.digitalpear.sheep_armor.init.SABiomeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
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

        getOrCreateTagBuilder(SABiomeTags.LONG_NOSED_BIOMES)
                .addOptionalTag(BiomeTags.IS_FOREST);

        getOrCreateTagBuilder(SABiomeTags.FUZZY_BIOMES)
                .addOptionalTag(BiomeTags.IS_TAIGA)
                .add(BiomeKeys.WINDSWEPT_HILLS)
                .add(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS);

        getOrCreateTagBuilder(SABiomeTags.PATCHED_BIOMES)
                .addOptionalTag(BiomeTags.SPAWNS_SNOW_FOXES);

        getOrCreateTagBuilder(SABiomeTags.ROCKY_BIOMES)
                .addOptionalTag(BiomeTags.IS_MOUNTAIN)
                .add(BiomeKeys.STONY_SHORE);

        getOrCreateTagBuilder(SABiomeTags.INKY_BIOMES)
                .addOptionalTag(BiomeTags.IS_JUNGLE);

        getOrCreateTagBuilder(SABiomeTags.FLECKED_BIOMES)
                .add(BiomeKeys.PLAINS).add(BiomeKeys.SUNFLOWER_PLAINS);


    }
}
