package net.digitalpear.armored_wool.common.access;

import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.minecraft.registry.entry.RegistryEntry;

public interface SheepVariantAccess extends VariantAccess<SheepVariant> {

    RegistryEntry<SheepVariant> getVariant();

    void setVariant(RegistryEntry<SheepVariant> variant);
}
