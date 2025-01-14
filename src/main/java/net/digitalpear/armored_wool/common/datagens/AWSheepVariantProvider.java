package net.digitalpear.armored_wool.common.datagens;

import net.digitalpear.armored_wool.common.entity.AWRegistryKeys;
import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.digitalpear.armored_wool.init.SheepVariants;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AWSheepVariantProvider extends FabricDynamicRegistryProvider {
    public AWSheepVariantProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        SheepVariants.variants.forEach(sheepVariantRegistryKey -> add(registries, entries, sheepVariantRegistryKey));
    }

    private void add(RegistryWrapper.WrapperLookup registries, Entries entries, RegistryKey<SheepVariant> resourceKey) {
        RegistryWrapper.Impl<SheepVariant> configuredFeatureRegistryLookup = registries.getWrapperOrThrow(AWRegistryKeys.SHEEP_VARIANT);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }
    @Override
    public String getName() {
        return "sheep_variant";
    }
}
