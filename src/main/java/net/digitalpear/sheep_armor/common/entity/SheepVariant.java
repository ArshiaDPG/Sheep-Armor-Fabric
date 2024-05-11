package net.digitalpear.sheep_armor.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class SheepVariant {

    public static final Codec<SheepVariant> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Identifier.CODEC.fieldOf("texture").forGetter((sheepVariant) -> sheepVariant.texture),
                    Codec.BOOL.fieldOf("has_custom_wool").orElse(false).forGetter(sheepVariant -> sheepVariant.hasCustomWool),
                    RegistryCodecs.entryList(RegistryKeys.BIOME).fieldOf("biomes").forGetter(SheepVariant::getBiomes)

                    ).apply(instance, SheepVariant::new));

    private final Identifier texture;
    private final boolean hasCustomWool;
    private final RegistryEntryList<Biome> biomes;
    public static final Codec<RegistryEntry<SheepVariant>> ENTRY_CODEC = RegistryElementCodec.of(SARegistryKeys.SHEEP_VARIANT, CODEC);


    public SheepVariant(Identifier texture, boolean hasCustomWool, RegistryEntryList<Biome> biomes) {
        this.texture = texture;
        this.hasCustomWool = hasCustomWool;
        this.biomes = biomes;
    }
    public int hashCode() {
        int i = 1;
        i = 31 * i + this.texture.hashCode();
        i = 31 * i + this.biomes.hashCode();
        return i;
    }

    public boolean hasCustomWool() {
        return hasCustomWool;
    }

    public Identifier getName() {
        return texture;
    }
    public Identifier getTextureID(){
        return getName().withPrefixedPath("textures/entity/sheep/");
    }

    public RegistryEntryList<Biome> getBiomes() {
        return this.biomes;
    }



}
