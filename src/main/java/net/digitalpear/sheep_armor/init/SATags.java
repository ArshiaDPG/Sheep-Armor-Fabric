package net.digitalpear.sheep_armor.init;

import net.digitalpear.sheep_armor.SheepArmor;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class SATags {

    public static class SAItemTags{
        public static final TagKey<Item> SHEEP_ARMORS = of("sheep_armors");
        public static final TagKey<Item> REPAIRS_CACTUS_EQUIPMENT = of("repairs_cactus_equipment");
        public static final TagKey<Item> REPAIRS_COPPER_EQUIPMENT = of("repairs_copper_equipment");
        public static final TagKey<Item> REPAIRS_AMETHYST_EQUIPMENT = of("repairs_amethyst_equipment");
        public static final TagKey<Item> REPAIRS_SHULKER_EQUIPMENT = of("repairs_shulker_equipment");

        private static TagKey<Item> of(String id) {
            return TagKey.of(RegistryKeys.ITEM, SheepArmor.id(id));
        }
    }
    public static class SABiomeTags {

        public static final TagKey<Biome> SPAWNS_ROCKY_SHEEP = of("spawns_rocky_sheep");
        public static final TagKey<Biome> SPAWNS_REGAL_SHEEP = of("spawns_regal_sheep");
        public static final TagKey<Biome> SPAWNS_SOOT_SHEEP = of("spawns_soot_sheep");
        public static final TagKey<Biome> SPAWNS_GLOOMY_SHEEP = of("spawns_gloomy_sheep");


        private static TagKey<Biome> of(String id) {
            return TagKey.of(RegistryKeys.BIOME, SheepArmor.id(id));
        }
    }


}
