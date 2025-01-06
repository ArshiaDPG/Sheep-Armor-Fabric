package net.digitalpear.armored_wool.common.access;

import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.minecraft.item.ItemStack;

public interface SheepRendererAccess {

    SheepVariant getVariant();

    boolean hasArmor();

    ItemStack getBodyArmor();

    void setBodyArmor(ItemStack stack);

    void setVariant(SheepVariant variant);
}
