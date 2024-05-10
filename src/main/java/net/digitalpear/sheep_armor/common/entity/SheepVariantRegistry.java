package net.digitalpear.sheep_armor.common.entity;

import net.digitalpear.sheep_armor.SheepArmor;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntryInfo;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class SheepVariantRegistry {

    public static final SimpleRegistry<SheepVariant> SHEEP_VARIANT = FabricRegistryBuilder.createSimple(SheepVariant.class,
                    new Identifier(SheepArmor.MOD_ID, "sheep_variant"))
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    private static <V, T extends V> T register(Registry<V> registry, Identifier id, T entry) {
        return register(registry, RegistryKey.of(registry.getKey(), id), entry);
    }
    public static <V, T extends V> T register(Registry<V> registry, RegistryKey<V> key, T entry) {
        ((MutableRegistry)registry).add(key, entry, RegistryEntryInfo.DEFAULT);
        return entry;
    }


    public static SheepVariant register(Identifier id, List<Predicate<SheepVariant.Context>> spawnConditions) {
        return Registry.register(SHEEP_VARIANT, id, new SheepVariant(id, spawnConditions));
    }
    public static SheepVariant register(String id, List<Predicate<SheepVariant.Context>> spawnConditions) {
        if(id.contains(":")){
            List<String> newID = Arrays.stream(id.split(":")).toList();
            Identifier skinName = new Identifier(newID.get(0), newID.get(1));
            return register(SHEEP_VARIANT, skinName, new SheepVariant(skinName, spawnConditions));
        }
        return Registry.register(SHEEP_VARIANT, id, new SheepVariant(new Identifier(SheepArmor.MOD_ID, id), spawnConditions));
    }

    public static SheepVariant register(Identifier id, Predicate<SheepVariant.Context>... spawnConditions) {
        return register(id, Arrays.stream(spawnConditions).toList());
    }
    public static SheepVariant register(String id, Predicate<SheepVariant.Context>... spawnConditions) {
        return register(id, Arrays.stream(spawnConditions).toList());
    }
    public static SheepVariant registerBiomeSpecific(Identifier id, TagKey<Biome> biomeTag) {
        return register(id, context -> context.getWorld().getBiome(context.getPos()).isIn(biomeTag));
    }
    public static SheepVariant registerBiomeSpecific(String id, TagKey<Biome> biomeTag) {
        return register(id, context -> context.getWorld().getBiome(context.getPos()).isIn(biomeTag));
    }
    public static SheepVariant register(String id) {
        return register(id, context -> true);
    }
    public static SheepVariant register(Identifier id) {
        return register(id, context -> true);
    }
}