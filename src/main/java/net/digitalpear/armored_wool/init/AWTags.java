package net.digitalpear.armored_wool.init;

import net.digitalpear.armored_wool.ArmoredWool;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class AWTags {
    private static <T> TagKey<T> of(String id, RegistryKey<? extends Registry<T>> key) {
        return TagKey.of(key, ArmoredWool.id(id));
    }

    public static class AWItemTags {
        private static TagKey<Item> of(String id) {
            return AWTags.of(id, RegistryKeys.ITEM);
        }
    }
    public static class AWBiomeTags {

        public static final TagKey<Biome> SPAWNS_ROCKY_SHEEP = of("spawns_rocky_sheep");
        public static final TagKey<Biome> SPAWNS_REGAL_SHEEP = of("spawns_regal_sheep");
        public static final TagKey<Biome> SPAWNS_SOOT_SHEEP = of("spawns_soot_sheep");
        public static final TagKey<Biome> SPAWNS_GLOOMY_SHEEP = of("spawns_gloomy_sheep");
        private static TagKey<Biome> of(String id) {
            return AWTags.of(id, RegistryKeys.BIOME);
        }
    }
}
