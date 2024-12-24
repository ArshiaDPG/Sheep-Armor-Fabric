package net.digitalpear.sheep_armor.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class SheepVariant {

    public static final String SHEEP_TEXTURE_PATH = "textures/entity/sheep/";
    public static final Identifier VANILLA_SHEEP_TEXTURE = Identifier.ofVanilla("textures/entity/sheep/sheep");

    public static final Codec<SheepVariant> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Identifier.CODEC.fieldOf("texture").orElse(VANILLA_SHEEP_TEXTURE).forGetter((sheepVariant) -> sheepVariant.texturePath),
                    Identifier.CODEC.fieldOf("wool_texture").orElse(VANILLA_SHEEP_TEXTURE.withSuffixedPath("_fur")).forGetter((sheepVariant) -> sheepVariant.woolTexturePath),
                    RegistryCodecs.entryList(RegistryKeys.BIOME).fieldOf("biomes").orElse(RegistryEntryList.empty()).forGetter(sheepVariant -> sheepVariant.biomes)
                    ).apply(instance, SheepVariant::new));

    private final Identifier texturePath;
    private final Identifier woolTexturePath;
    private final RegistryEntryList<Biome> biomes;

    public SheepVariant(Identifier texturePath, RegistryEntryList<Biome> biomes) {
        this(texturePath, texturePath.withSuffixedPath("_fur"), biomes);
    }
    public SheepVariant(Identifier texturePath, Identifier woolTexturePath, RegistryEntryList<Biome> biomes) {
        this.texturePath = texturePath;
        this.woolTexturePath = woolTexturePath;
        this.biomes = biomes;
    }
    public int hashCode() {
        int i = 1;
        i = 31 * i + this.texturePath.hashCode();
        i = 31 * i + this.biomes.hashCode();
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
}
