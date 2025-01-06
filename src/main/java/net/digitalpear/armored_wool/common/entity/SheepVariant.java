package net.digitalpear.armored_wool.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.digitalpear.armored_wool.ArmoredWool;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;

import java.util.List;
import java.util.Objects;

public class SheepVariant {

    public static final String SHEEP_TEXTURE_PATH = "textures/entity/sheep/";
    public static final Identifier VANILLA_SHEEP_TEXTURE = Identifier.ofVanilla("sheep").withPrefixedPath(SHEEP_TEXTURE_PATH);
    public static final Identifier VANILLA_SHEEP_INNER_WOOL = ArmoredWool.id("barn").withSuffixedPath("inner_fur").withPrefixedPath(SHEEP_TEXTURE_PATH);

    public static final Codec<SheepVariant> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Identifier.CODEC.fieldOf("texture").orElse(VANILLA_SHEEP_TEXTURE).forGetter((sheepVariant) -> sheepVariant.texturePath),
                    Identifier.CODEC.fieldOf("wool_texture").orElse(VANILLA_SHEEP_TEXTURE.withSuffixedPath("_fur")).forGetter((sheepVariant) -> sheepVariant.woolTexturePath),
                    Identifier.CODEC.fieldOf("inner_wool_texture").orElse(VANILLA_SHEEP_INNER_WOOL).forGetter((sheepVariant) -> sheepVariant.innerWoolTexturePath),
                    Codec.BOOL.fieldOf("has_inner_wool").orElse(true).forGetter(sheepVariant -> sheepVariant.hasInnerWool),
                    Codec.list(WoolColorEntry.CODEC).fieldOf("wool_colors").orElse(WoolColorEntry.DEFAULT_SHEEP_COLORS).forGetter(sheepVariant -> sheepVariant.woolColors),
                    RegistryCodecs.entryList(RegistryKeys.BIOME).fieldOf("biomes").orElse(RegistryEntryList.empty()).forGetter(sheepVariant -> sheepVariant.biomes)
            ).apply(instance, SheepVariant::new));

    private final Identifier texturePath;
    private final Identifier woolTexturePath;
    private final Identifier innerWoolTexturePath;
    private final boolean hasInnerWool;
    private final RegistryEntryList<Biome> biomes;
    private final List<WoolColorEntry> woolColors;

    public SheepVariant(Identifier texturePath, List<WoolColorEntry> woolColors, RegistryEntryList<Biome> biomes, boolean hasInnerWool) {
        this(texturePath, texturePath.withSuffixedPath("_fur"), texturePath.withSuffixedPath("_inner_fur"), hasInnerWool, woolColors, biomes);
    }
    public SheepVariant(Identifier texturePath, Identifier woolTexturePath, Identifier innerWoolTexturePath, boolean hasInnerWool, List<WoolColorEntry> woolColors, RegistryEntryList<Biome> biomes) {
        this.texturePath = texturePath;
        this.woolTexturePath = woolTexturePath;
        this.innerWoolTexturePath = innerWoolTexturePath;
        this.hasInnerWool = hasInnerWool;
        this.woolColors = woolColors;
        this.biomes = biomes;
    }
    public int hashCode() {
        int i = 1;
        i = 31 * i + this.texturePath.hashCode();
        i = 31 * i + this.innerWoolTexturePath.hashCode();
        i = 31 * i + this.woolTexturePath.hashCode();
        for (RegistryEntry<Biome> entry : this.biomes){
            i = 31 * i + entry.hashCode();
        }
        for (WoolColorEntry entry : this.woolColors){
            i = 31 * i + entry.hashCode();
        }
        return i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SheepVariant that = (SheepVariant) o;
        return Objects.equals(texturePath, that.texturePath) && Objects.equals(woolTexturePath, that.woolTexturePath) && Objects.equals(innerWoolTexturePath, that.innerWoolTexturePath) && Objects.equals(biomes, that.biomes) && Objects.equals(woolColors, that.woolColors);
    }

    public boolean hasInnerWool() {
        return hasInnerWool;
    }

    public Identifier getTexturePath(){
        return this.texturePath;
    }

    public Identifier getWoolTexturePath() {
        return woolTexturePath;
    }

    public Identifier getInnerWoolTexturePath() {
        return innerWoolTexturePath;
    }

    public RegistryEntryList<Biome> getBiomes() {
        return this.biomes;
    }

    public List<WoolColorEntry> getWoolColors() {
        return woolColors;
    }

    public DyeColor generateColor(Random random) {
        if (this.getWoolColors().isEmpty()) {
            return DyeColor.WHITE;
        }
        int totalWeight = this.getWoolColors().stream().mapToInt(WoolColorEntry::weight).sum();
        int randomValue = random.nextInt(totalWeight);

        for (WoolColorEntry wc : this.getWoolColors()) {
            if (randomValue < wc.weight()) {
                return wc.color();
            }
            randomValue -= wc.weight();
        }

        throw new IllegalStateException("Failed to generate a color");
    }
}
