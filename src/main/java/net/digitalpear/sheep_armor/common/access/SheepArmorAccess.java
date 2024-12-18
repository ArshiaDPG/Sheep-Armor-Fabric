package net.digitalpear.sheep_armor.common.access;

import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.entry.RegistryEntry;

public interface SheepArmorAccess {

    boolean sheep_Armor_Fabric$hasArmor();

    boolean sheep_Armor_Fabric$shouldArmorAbsorbDamage(DamageSource source);

    RegistryEntry<SheepVariant> sheep_Armor_Fabric$getVariant();

    void sheep_Armor_Fabric$setVariant(RegistryEntry<SheepVariant> variant);
}
