package net.digitalpear.armored_wool.init;

import net.digitalpear.armored_wool.ArmoredWool;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class AWTags {

    public static class AWItemTags {
        public static final TagKey<Item> THORNY_SHEEP_ARMORS = of("throny_sheep_armors");
        public static final TagKey<Item> SHEEP_ARMORS = of("sheep_armors");
        public static final TagKey<Item> REPAIRS_CACTUS_EQUIPMENT = of("repairs_cactus_equipment");
        public static final TagKey<Item> REPAIRS_COPPER_EQUIPMENT = of("repairs_copper_equipment");
        public static final TagKey<Item> REPAIRS_AMETHYST_EQUIPMENT = of("repairs_amethyst_equipment");
        public static final TagKey<Item> REPAIRS_SHULKER_EQUIPMENT = of("repairs_shulker_equipment");

        private static TagKey<Item> of(String id) {
            return TagKey.of(RegistryKeys.ITEM, ArmoredWool.id(id));
        }
    }
    public static class AWBiomeTags {

        public static final TagKey<Biome> SPAWNS_ROCKY_SHEEP = of("spawns_rocky_sheep");
        public static final TagKey<Biome> SPAWNS_REGAL_SHEEP = of("spawns_regal_sheep");
        public static final TagKey<Biome> SPAWNS_SOOT_SHEEP = of("spawns_soot_sheep");
        public static final TagKey<Biome> SPAWNS_GLOOMY_SHEEP = of("spawns_gloomy_sheep");


        private static TagKey<Biome> of(String id) {
            return TagKey.of(RegistryKeys.BIOME, ArmoredWool.id(id));
        }
    }
    public static class AWEntityTypeTags{

        public static final TagKey<EntityType<?>> ARMOR_COMPATIBLE_SHEEP = of("armor_compatible_sheep");

        private static TagKey<EntityType<?>> of(String id) {
            return TagKey.of(RegistryKeys.ENTITY_TYPE, ArmoredWool.id(id));
        }

    }


}
