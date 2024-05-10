package net.digitalpear.sheep_armor.common.access;

import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.entry.RegistryEntry;

public interface SheepArmorAccess {

    boolean hasArmor();
    boolean shouldArmorAbsorbDamage(DamageSource source);

    RegistryEntry<SheepVariant> getVariant();

    void setVariant(RegistryEntry<SheepVariant> variant);
}
