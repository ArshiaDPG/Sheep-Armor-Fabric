package net.digitalpear.armored_wool.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.digitalpear.armored_wool.init.SheepVariants;
import net.minecraft.entity.VariantSelectorProvider;
import net.minecraft.entity.spawn.SpawnCondition;
import net.minecraft.entity.spawn.SpawnConditionSelectors;
import net.minecraft.entity.spawn.SpawnContext;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryFixedCodec;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.random.Random;

import java.util.List;
import java.util.Objects;

public class SheepVariant implements VariantSelectorProvider<SpawnContext, SpawnCondition> {



    public static final Codec<SheepVariant> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    SheepAssets.CODEC.fieldOf("assets").forGetter(sheepVariant -> sheepVariant.assets),
                    Pool.createCodec(DyeColor.CODEC).fieldOf("wool_colors").orElse(SheepVariants.SheepColors.DEFAULT_SHEEP_COLORS).forGetter(sheepVariant -> sheepVariant.woolColors),
                    SpawnConditionSelectors.CODEC.fieldOf("spawn_conditions").orElse(SpawnConditionSelectors.EMPTY).forGetter(sheepVariant -> sheepVariant.spawnConditions)
            ).apply(instance, SheepVariant::new));

    public static final Codec<SheepVariant> NETWORK_CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    SheepAssets.CODEC.fieldOf("assets").forGetter(sheepVariant -> sheepVariant.assets),
                    Pool.createCodec(DyeColor.CODEC).fieldOf("wool_colors").orElse(SheepVariants.SheepColors.DEFAULT_SHEEP_COLORS).forGetter(sheepVariant -> sheepVariant.woolColors)
            ).apply(instance, SheepVariant::new));

    public static final Codec<RegistryEntry<SheepVariant>> ENTRY_CODEC = RegistryFixedCodec.of(AWRegistryKeys.SHEEP_VARIANT);
    public static final PacketCodec<RegistryByteBuf, RegistryEntry<SheepVariant>> ENTRY_PACKET_CODEC =  PacketCodecs.registryEntry(AWRegistryKeys.SHEEP_VARIANT);

    private final SheepAssets assets;
    private final SpawnConditionSelectors spawnConditions;
    private final Pool<DyeColor> woolColors;


    public SheepVariant(Identifier texturePath, Pool<DyeColor> woolColors, SpawnConditionSelectors spawnConditions) {
        this(new SheepAssets(texturePath.withSuffixedPath(".png"), texturePath.withSuffixedPath("_wool.png"), texturePath.withSuffixedPath("_wool_undercoat.png")), woolColors, spawnConditions);
    }
    public SheepVariant(SheepAssets sheepAssets, Pool<DyeColor> woolColors, SpawnConditionSelectors spawnConditions) {
        this.assets = sheepAssets;
        this.woolColors = woolColors;
        this.spawnConditions = spawnConditions;
    }
    public SheepVariant(SheepAssets assets, Pool<DyeColor> woolColors) {
        this.assets = assets;
        this.woolColors = woolColors;
        this.spawnConditions = SpawnConditionSelectors.EMPTY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assets, spawnConditions, woolColors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SheepVariant that = (SheepVariant) o;
        return Objects.equals(assets, that.assets) && Objects.equals(spawnConditions, that.spawnConditions) && Objects.equals(woolColors, that.woolColors);
    }

    public SheepAssets getAssets(){
        return this.assets;
    }

    public SpawnConditionSelectors spawnConditions() {
        return this.spawnConditions;
    }

    public Pool<DyeColor> getWoolColors() {
        return woolColors;
    }

    public DyeColor generateColor(Random random) {
        return this.getWoolColors().get(random);
    }

    @Override
    public List<Selector<SpawnContext, SpawnCondition>> getSelectors() {
        return spawnConditions.selectors();
    }


    public static class SheepAssets {
        public static final String SHEEP_TEXTURE_PATH = "textures/entity/sheep/";
        public static final Identifier VANILLA_SHEEP_TEXTURE = Identifier.ofVanilla("sheep").withPrefixedPath(SHEEP_TEXTURE_PATH);

        public static final Codec<SheepAssets> CODEC = RecordCodecBuilder.create((instance) ->
                instance.group(
                        Identifier.CODEC.fieldOf("texture").orElse(VANILLA_SHEEP_TEXTURE).forGetter((sheepVariant) -> sheepVariant.texture),
                        Identifier.CODEC.fieldOf("wool_texture").orElse(VANILLA_SHEEP_TEXTURE.withSuffixedPath("_wool")).forGetter((sheepVariant) -> sheepVariant.woolTexture),
                        Identifier.CODEC.fieldOf("wool_undercoat_texture").orElse(VANILLA_SHEEP_TEXTURE.withSuffixedPath("_wool_undercoat")).forGetter((sheepVariant) -> sheepVariant.undercoatTexture)
                ).apply(instance, SheepAssets::new));

        private final Identifier texture;
        private final Identifier woolTexture;
        private final Identifier undercoatTexture;

        public SheepAssets(Identifier texture, Identifier woolTexture, Identifier undercoatTexture){
            this.texture = texture;
            this.woolTexture = woolTexture;
            this.undercoatTexture = undercoatTexture;
        }
        public SheepAssets(Identifier texture){
            this(texture.withSuffixedPath(".png"), texture.withSuffixedPath("_wool.png"), texture.withSuffixedPath("_wool_undercoat.png"));
        }

        public Identifier getTexture() {
            return texture;
        }

        public Identifier getWoolTexture() {
            return woolTexture;
        }

        public Identifier getUndercoatTexture() {
            return undercoatTexture;
        }
    }
}
