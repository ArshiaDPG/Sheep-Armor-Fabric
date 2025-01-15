package net.digitalpear.armored_wool.common.datagens.tags;

import net.digitalpear.armored_wool.init.AWEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class AWEnchantmentTagProvider extends FabricTagProvider<Enchantment> {
    public AWEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENCHANTMENT, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(EnchantmentTags.IN_ENCHANTING_TABLE).add(AWEnchantments.TRIMMING, AWEnchantments.LIGHTNESS);
        getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE).add(AWEnchantments.TRIMMING, AWEnchantments.LIGHTNESS);

        getOrCreateTagBuilder(EnchantmentTags.TREASURE).add(AWEnchantments.WOOLSPLOSION);
        getOrCreateTagBuilder(EnchantmentTags.TAIGA_SPECIAL_TRADE).add(AWEnchantments.WOOLSPLOSION);
        getOrCreateTagBuilder(EnchantmentTags.SNOW_COMMON_TRADE).add(AWEnchantments.LIGHTNESS);

        getOrCreateTagBuilder(EnchantmentTags.ON_RANDOM_LOOT).add(AWEnchantments.WOOLSPLOSION, AWEnchantments.TRIMMING, AWEnchantments.LIGHTNESS);
        getOrCreateTagBuilder(EnchantmentTags.TRADEABLE).add(AWEnchantments.TRIMMING, AWEnchantments.LIGHTNESS);
    }
}
