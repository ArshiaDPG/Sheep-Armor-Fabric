package net.digitalpear.sheep_armor.common.datagens;

import net.digitalpear.sheep_armor.init.SAEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class SheepEnchantmentProvider extends FabricDynamicRegistryProvider {
    public SheepEnchantmentProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        SAEnchantments.enchantments.forEach(enchantmentRegistryKey -> add(wrapperLookup, entries, enchantmentRegistryKey));
    }
    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<Enchantment> resourceKey) {
        RegistryWrapper.Impl<Enchantment> configuredFeatureRegistryLookup = registries.getOrThrow(RegistryKeys.ENCHANTMENT);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "enchantment";
    }
}
