package net.digitalpear.sheep_armor.common.entity;

import net.digitalpear.sheep_armor.SheepArmor;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public class SARegistryKeys {

    public static final RegistryKey<Registry<SheepVariant>> SHEEP_VARIANT = of("sheep_variant");

    private static <T> RegistryKey<Registry<T>> of(String id) {
        return RegistryKey.ofRegistry(new Identifier(SheepArmor.MOD_ID,id));
    }

    public static void init() {
    }
}