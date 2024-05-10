package net.digitalpear.sheep_armor.init;

import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.enchantments.SheepEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SAEnchantments {

    public static final Enchantment TRIMMING = register("trimming", new SheepEnchantment(7, 3, Enchantment.leveledCost(1, 11), Enchantment.leveledCost(12, 11), 4));
    public static final Enchantment WOOLSPLOSION = register("woolsplosion", new SheepEnchantment(3, 1, Enchantment.leveledCost(1, 11), Enchantment.leveledCost(18, 11), 6));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(SheepArmor.MOD_ID, name), enchantment);
    }

    public static void init(){}

}
