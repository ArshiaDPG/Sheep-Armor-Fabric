package net.digitalpear.sheep_armor.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.digitalpear.sheep_armor.SheepArmor;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.List;

public class SheepVariant {

    public static final String SHEEP_TEXTURE_PATH = "textures/entity/sheep/";
    public static final Identifier VANILLA_SHEEP_TEXTURE = Identifier.ofVanilla("sheep");
    public static final Identifier VANILLA_SHEEP_INNER_WOOL = SheepArmor.id("barn").withSuffixedPath("inner_fur").withPrefixedPath(SHEEP_TEXTURE_PATH);

    public static final Codec<SheepVariant> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Identifier.CODEC.fieldOf("texture").orElse(VANILLA_SHEEP_TEXTURE).forGetter((sheepVariant) -> sheepVariant.texturePath),
                    Identifier.CODEC.fieldOf("wool_texture").orElse(VANILLA_SHEEP_TEXTURE.withSuffixedPath("_fur")).forGetter((sheepVariant) -> sheepVariant.woolTexturePath),
                    Identifier.CODEC.fieldOf("inner_wool_texture").orElse(VANILLA_SHEEP_INNER_WOOL).forGetter((sheepVariant) -> sheepVariant.innerWoolTexturePath),
                    Codec.list(WoolColorEntry.CODEC).fieldOf("wool_colors").orElse(WoolColorEntry.DEFAULT_SHEEP_COLORS).forGetter(sheepVariant -> sheepVariant.woolColors),
                    RegistryCodecs.entryList(RegistryKeys.BIOME).fieldOf("biomes").orElse(RegistryEntryList.empty()).forGetter(sheepVariant -> sheepVariant.biomes)
            ).apply(instance, SheepVariant::new));

    private final Identifier texturePath;
    private final Identifier woolTexturePath;
    private final Identifier innerWoolTexturePath;
    private final RegistryEntryList<Biome> biomes;
    private final List<WoolColorEntry> woolColors;

    public SheepVariant(Identifier texturePath, List<WoolColorEntry> woolColors, RegistryEntryList<Biome> biomes) {
        this(texturePath, texturePath.withSuffixedPath("_fur"), texturePath.withSuffixedPath("_inner_fur"), woolColors, biomes);
    }
    public SheepVariant(Identifier texturePath, Identifier woolTexturePath, Identifier innerWoolTexturePath, List<WoolColorEntry> woolColors, RegistryEntryList<Biome> biomes) {
        this.texturePath = texturePath;
        this.woolTexturePath = woolTexturePath;
        this.innerWoolTexturePath = innerWoolTexturePath;
        this.woolColors = woolColors;
        this.biomes = biomes;
    }
    public int hashCode() {
        int i = 1;
        i = 31 * i + this.texturePath.hashCode();
        i = 31 * i + this.innerWoolTexturePath.hashCode();
        i = 31 * i + this.woolTexturePath.hashCode();
        i = 31 * i + this.biomes.hashCode();
        i = 31 * i + this.woolColors.hashCode();
        return i;
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
}
