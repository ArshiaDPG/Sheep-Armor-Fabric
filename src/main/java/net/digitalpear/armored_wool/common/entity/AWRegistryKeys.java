package net.digitalpear.armored_wool.common.entity;

import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public class AWRegistryKeys {

    public static final RegistryKey<Registry<SheepVariant>> SHEEP_VARIANT = of("sheep_variant");

    private static <T> RegistryKey<Registry<T>> of(String id) {
        return RegistryKey.ofRegistry(Identifier.ofVanilla(id));
    }

    public static void init() {
    }
}