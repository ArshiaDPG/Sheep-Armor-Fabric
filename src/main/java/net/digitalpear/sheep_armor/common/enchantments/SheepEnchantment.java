package net.digitalpear.sheep_armor.common.enchantments;

import net.digitalpear.sheep_armor.init.SATags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;

public class SheepEnchantment extends Enchantment {
    public SheepEnchantment(int weight, int maxLevel, Cost minCost, Cost maxCost, int anvilCost) {
        super(Enchantment.properties(SATags.SAItemTags.SHEEP_ARMORS, weight, maxLevel, minCost, maxCost, anvilCost, EquipmentSlot.BODY));
    }
}
