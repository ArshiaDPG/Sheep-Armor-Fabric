package net.digitalpear.sheep_armor.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;

public class SheepVariant {

    public static final String SHEEP_TEXTURE_PATH = "textures/entity/sheep/";
    public static final Identifier VANILLA_SHEEP_TEXTURE = Identifier.ofVanilla("sheep");

    public static final SheepColor DEFAULT_SHEEP_COLORS = new SheepColor(DyeColor.WHITE);

    public static final Codec<SheepVariant> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(
                    Identifier.CODEC.fieldOf("texture").orElse(VANILLA_SHEEP_TEXTURE).forGetter((sheepVariant) -> sheepVariant.texturePath),
                    Identifier.CODEC.fieldOf("wool_texture").orElse(VANILLA_SHEEP_TEXTURE.withSuffixedPath("_fur")).forGetter((sheepVariant) -> sheepVariant.woolTexturePath),
                    SheepColor.CODEC.fieldOf("wool_colors").orElse(DEFAULT_SHEEP_COLORS).forGetter((sheepVariant) -> sheepVariant.woolColors),
                    RegistryCodecs.entryList(RegistryKeys.BIOME).fieldOf("biomes").orElse(RegistryEntryList.empty()).forGetter(sheepVariant -> sheepVariant.biomes)
            ).apply(instance, SheepVariant::new));

    private final Identifier texturePath;
    private final Identifier woolTexturePath;
    private final RegistryEntryList<Biome> biomes;
    private final SheepColor woolColors;

    public SheepVariant(Identifier texturePath, SheepColor woolColors, RegistryEntryList<Biome> biomes) {
        this(texturePath, texturePath.withSuffixedPath("_fur"), woolColors, biomes);
    }
    public SheepVariant(Identifier texturePath, Identifier woolTexturePath, SheepColor woolColors, RegistryEntryList<Biome> biomes) {
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

    public SheepColor getWoolColors() {
        return woolColors;
    }


    @SuppressWarnings("unused")
    public static class SheepColor {

//        public static class SheepColorEntry{
//
//            private final DyeColor color;
//            private final int weight;
//            public SheepColorEntry(DyeColor color, int weight){
//                this.color = color;
//                this.weight = weight;
//            }
//        }
        private final DyeColor baseColor;
        private final DyeColor commonColor;
        private final DyeColor uncommonColor;
        private final DyeColor rareColor;
        private final DyeColor superRareColor;
        private final DyeColor legendaryColor;

        public static final Codec<SheepColor> CODEC = RecordCodecBuilder.create((instance) ->
                instance.group(
                        DyeColor.CODEC.fieldOf("base_color").orElse(DyeColor.WHITE).forGetter((color) -> color.baseColor),
                        DyeColor.CODEC.fieldOf("common_color").orElse(DyeColor.BLACK).forGetter((color) -> color.commonColor),
                        DyeColor.CODEC.fieldOf("uncommon_color").orElse(DyeColor.GRAY).forGetter((color) -> color.uncommonColor),
                        DyeColor.CODEC.fieldOf("rare_color").orElse(DyeColor.LIGHT_GRAY).forGetter((color) -> color.rareColor),
                        DyeColor.CODEC.fieldOf("super_rare_color").orElse(DyeColor.BROWN).forGetter((color) -> color.superRareColor),
                        DyeColor.CODEC.fieldOf("legendary_color").orElse(DyeColor.PINK).forGetter((color) -> color.legendaryColor)
                ).apply(instance, SheepColor::new));
        public SheepColor(DyeColor baseColor, DyeColor commonColor, DyeColor uncommonColor, DyeColor rareColor, DyeColor superRareColor, DyeColor legendaryColor){
            this.baseColor = baseColor;
            this.commonColor = commonColor;
            this.uncommonColor = uncommonColor;
            this.rareColor = rareColor;
            this.superRareColor = superRareColor;
            this.legendaryColor = legendaryColor;
        }
        public SheepColor(DyeColor baseColor){
            this(baseColor, DyeColor.BLACK, DyeColor.GRAY, DyeColor.LIGHT_GRAY, DyeColor.BROWN, DyeColor.PINK);
        }

        public DyeColor getBaseColor() {
            return baseColor;
        }

        public DyeColor getCommonColor() {
            return commonColor;
        }

        public DyeColor getUncommonColor() {
            return uncommonColor;
        }

        public DyeColor getRareColor() {
            return rareColor;
        }

        public DyeColor getSuperRareColor() {
            return superRareColor;
        }

        public DyeColor getLegendaryColor() {
            return legendaryColor;
        }

        @Override
        public int hashCode() {
            int i = 1;
            i = 31 * i + this.baseColor.hashCode();
            i = 31 * i + this.commonColor.hashCode();
            i = 31 * i + this.uncommonColor.hashCode();
            i = 31 * i + this.rareColor.hashCode();
            i = 31 * i + this.superRareColor.hashCode();
            i = 31 * i + this.legendaryColor.hashCode();
            return i;
        }

        public DyeColor generateColor(Random random) {
            int i = random.nextInt(100);
            if (i < 5) {
                return commonColor;
            } else if (i < 10) {
                return uncommonColor;
            } else if (i < 15) {
                return rareColor;
            } else if (i < 18) {
                return superRareColor;
            } else {
                return random.nextInt(500) == 0 ? legendaryColor : baseColor;
            }
        }
    }
}
