package net.digitalpear.sheep_armor.common.datagens;

import net.digitalpear.sheep_armor.common.entity.SARegistryKeys;
import net.digitalpear.sheep_armor.common.entity.SheepVariant;
import net.digitalpear.sheep_armor.init.SheepVariants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class SheepArmorSheepVariantProvider extends FabricDynamicRegistryProvider {
    public SheepArmorSheepVariantProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        SheepVariants.variants.forEach(sheepVariantRegistryKey -> {
            add(registries, entries, sheepVariantRegistryKey);
        });
    }

    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<SheepVariant> resourceKey) {
        RegistryWrapper.Impl<SheepVariant> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(SARegistryKeys.SHEEP_VARIANT);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "sheep_variant";
    }
}
