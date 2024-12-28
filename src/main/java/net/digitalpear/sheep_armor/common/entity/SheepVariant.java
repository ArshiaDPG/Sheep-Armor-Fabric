package net.digitalpear.sheep_armor.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.List;

public class SheepVariant {

    public static final String SHEEP_TEXTURE_PATH = "textures/entity/sheep/";
    public static final Identifier VANILLA_SHEEP_TEXTURE = Identifier.ofVanilla("sheep");

    public static final Codec<SheepVariant> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Identifier.CODEC.fieldOf("texture").orElse(VANILLA_SHEEP_TEXTURE).forGetter((sheepVariant) -> sheepVariant.texturePath),
                    Identifier.CODEC.fieldOf("wool_texture").orElse(VANILLA_SHEEP_TEXTURE.withSuffixedPath("_fur")).forGetter((sheepVariant) -> sheepVariant.woolTexturePath),
                    Codec.list(WoolColorEntry.CODEC).fieldOf("wool_colors").orElse(WoolColorEntry.DEFAULT_SHEEP_COLORS).forGetter(sheepVariant -> sheepVariant.woolColors),
                    RegistryCodecs.entryList(RegistryKeys.BIOME).fieldOf("biomes").orElse(RegistryEntryList.empty()).forGetter(sheepVariant -> sheepVariant.biomes)
            ).apply(instance, SheepVariant::new));

    private final Identifier texturePath;
    private final Identifier woolTexturePath;
    private final RegistryEntryList<Biome> biomes;
    private final List<WoolColorEntry> woolColors;

    public SheepVariant(Identifier texturePath, List<WoolColorEntry> woolColors, RegistryEntryList<Biome> biomes) {
        this(texturePath, texturePath.withSuffixedPath("_fur"), woolColors, biomes);
    }
    public SheepVariant(Identifier texturePath, Identifier woolTexturePath, List<WoolColorEntry> woolColors, RegistryEntryList<Biome> biomes) {
        this.texturePath = texturePath;
        this.woolTexturePath = woolTexturePath;
        this.woolColors = woolColors;
        this.biomes = biomes;
    }
    public int hashCode() {
        int i = 1;
        i = 31 * i + this.texturePath.hashCode();
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

    public RegistryEntryList<Biome> getBiomes() {
        return this.biomes;
    }

    public List<WoolColorEntry> getWoolColors() {
        return woolColors;
    }
}
