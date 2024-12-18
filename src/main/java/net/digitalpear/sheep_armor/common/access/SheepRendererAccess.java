package net.digitalpear.sheep_armor.common.access;

import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.minecraft.item.ItemStack;

public interface SheepRendererAccess {

    SheepVariant getVariant();

    boolean hasArmor();

    ItemStack getBodyArmor();

    void setBodyArmor(ItemStack stack);

    void setVariant(SheepVariant variant);
}
