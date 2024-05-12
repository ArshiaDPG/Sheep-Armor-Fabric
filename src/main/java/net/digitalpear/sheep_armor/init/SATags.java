package net.digitalpear.sheep_armor.init;

import net.digitalpear.sheep_armor.SheepArmor;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class SATags {

    public class SAItemTags{
        public static final TagKey<Item> SHEEP_ARMORS = of("sheep_armors");

        private static TagKey<Item> of(String id) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(SheepArmor.MOD_ID, id));
        }
        public static void init(){

        }
    }
    public class SABiomeTags {

        public static final TagKey<Biome> LONG_NOSED_BIOMES = of("long_nosed_biomes");
        public static final TagKey<Biome> FUZZY_BIOMES = of("fuzzy_biomes");
        public static final TagKey<Biome> PATCHED_BIOMES = of("patched_biomes");
        public static final TagKey<Biome> ROCKY_BIOMES = of("rocky_biomes");
        public static final TagKey<Biome> INKY_BIOMES = of("inky_biomes");
        public static final TagKey<Biome> FLECKED_BIOMES = of("flecked_biomes");

        private static TagKey<Biome> of(String id) {
            return TagKey.of(RegistryKeys.BIOME, new Identifier(SheepArmor.MOD_ID, id));
        }
        public static void init(){

        }
    }

    public static void init(){
        SAItemTags.init();
        SABiomeTags.init();
    }

}
