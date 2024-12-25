package net.digitalpear.sheep_armor.common.datagens.tags;

import net.digitalpear.sheep_armor.init.SAEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class SheepArmorEnchantmentTagProvider extends FabricTagProvider<Enchantment> {
    public SheepArmorEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENCHANTMENT, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(EnchantmentTags.IN_ENCHANTING_TABLE).add(SAEnchantments.TRIMMING);
        getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE).add(SAEnchantments.TRIMMING);

        getOrCreateTagBuilder(EnchantmentTags.TREASURE).add(SAEnchantments.WOOLSPLOSION);
        getOrCreateTagBuilder(EnchantmentTags.TAIGA_SPECIAL_TRADE).add(SAEnchantments.WOOLSPLOSION);

        getOrCreateTagBuilder(EnchantmentTags.ON_RANDOM_LOOT).add(SAEnchantments.WOOLSPLOSION).add(SAEnchantments.TRIMMING);
        getOrCreateTagBuilder(EnchantmentTags.TRADEABLE).add(SAEnchantments.WOOLSPLOSION).add(SAEnchantments.TRIMMING);
    }
}
