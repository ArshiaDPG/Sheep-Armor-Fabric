package net.digitalpear.sheep_armor;

import net.digitalpear.sheep_armor.common.datagens.*;
import net.digitalpear.sheep_armor.common.datagens.tags.SheepArmorBiomeTagProvider;
import net.digitalpear.sheep_armor.common.datagens.tags.SheepArmorItemTagProvider;
import net.digitalpear.sheep_armor.common.entity.SARegistryKeys;
import net.digitalpear.sheep_armor.init.SheepVariants;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

public class SheepArmorDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(SheepArmorModelProvider::new);
        pack.addProvider(SheepArmorLanguageProvider::new);
        pack.addProvider(SheepArnorRecipeProvider::new);

        pack.addProvider(SheepArmorItemTagProvider::new);
        pack.addProvider(SheepArmorBiomeTagProvider::new);

        pack.addProvider(SheepArmorSheepVariantProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(SARegistryKeys.SHEEP_VARIANT, SheepVariants::bootstrap);
    }
}
