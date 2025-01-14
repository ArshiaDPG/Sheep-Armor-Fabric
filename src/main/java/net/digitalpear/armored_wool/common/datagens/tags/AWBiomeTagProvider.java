package net.digitalpear.armored_wool.common.datagens.tags;

import net.digitalpear.armored_wool.init.AWTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.concurrent.CompletableFuture;

public class AWBiomeTagProvider extends FabricTagProvider<Biome> {
    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public AWBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(AWTags.AWBiomeTags.SPAWNS_ROCKY_SHEEP)
                .add(BiomeKeys.STONY_PEAKS)
                .add(BiomeKeys.GROVE)
                .add(BiomeKeys.FROZEN_PEAKS)
                .add(BiomeKeys.JAGGED_PEAKS)
                .add(BiomeKeys.STONY_SHORE)
                .addOptional(Identifier.of("biomesoplenty", "crag"))
                .addOptional(Identifier.of("biomesoplenty", "rocky_rainforest"))
                .addOptional(Identifier.of("biomesoplenty", "rocky_shrubland"))
                .addOptional(Identifier.of("blooming_biosphere", "tidepools"))
        ;

        getOrCreateTagBuilder(AWTags.AWBiomeTags.SPAWNS_REGAL_SHEEP)
                .add(BiomeKeys.FLOWER_FOREST)
                .add(BiomeKeys.CHERRY_GROVE)
                .add(BiomeKeys.SUNFLOWER_PLAINS)
                .add(BiomeKeys.MEADOW)
                .addOptional(Identifier.of("biomesoplenty", "orchard"))
                .addOptional(Identifier.of("biomesoplenty", "lavender_field"))
                .addOptional(Identifier.of("wilderwild", "flower_field"))
                .addOptional(Identifier.of("blooming_biosphere", "snowy_cherry_grove"))
        ;

        getOrCreateTagBuilder(AWTags.AWBiomeTags.SPAWNS_SOOT_SHEEP)
                .forceAddTag(BiomeTags.IS_NETHER)
                .addOptional(Identifier.of("clifftree", "inferno"))
                .addOptional(Identifier.of("biomesoplenty", "volcano"))
                .addOptional(Identifier.of("biomesoplenty", "volcanic_plains"))
                .addOptional(Identifier.of("profundis", "molten_caves"))
                .addOptional(Identifier.of("cavernous", "volcanic_caves"))
                .addOptional(Identifier.of("wilderwild", "magmatic_caves"))
        ;

        getOrCreateTagBuilder(AWTags.AWBiomeTags.SPAWNS_GLOOMY_SHEEP)
                .add(BiomeKeys.DEEP_DARK)
                .addOptional(Identifier.of("limbo", "agony"))
                .addOptional(Identifier.of("limbo", "blossom"))
                .addOptional(Identifier.of("limbo", "change"))
                .addOptional(Identifier.of("limbo", "desolation"))
                .addOptional(Identifier.of("limbo", "despair"))
                .addOptional(Identifier.of("limbo", "dread"))
                .addOptional(Identifier.of("limbo", "hope"))
                .addOptional(Identifier.of("limbo", "isolation"))
        ;
    }
}
