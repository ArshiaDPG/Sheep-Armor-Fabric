package net.digitalpear.armored_wool.common.access;

import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;

public interface SheepArmorAccess {

    boolean hasArmor();

    boolean shouldArmorAbsorbDamage(DamageSource source);

    RegistryEntry<SheepVariant> getVariant();

    void setVariant(RegistryEntry<SheepVariant> variant);

    ItemStack getBodyArmor();
}
