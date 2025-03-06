package net.digitalpear.armored_wool.common.access;

import net.minecraft.registry.entry.RegistryEntry;

public interface VariantAccess<T> {

    RegistryEntry<T> getVariant();

    void setVariant(RegistryEntry<T> variant);
}
