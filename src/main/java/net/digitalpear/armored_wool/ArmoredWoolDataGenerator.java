package net.digitalpear.armored_wool;

import net.digitalpear.armored_wool.common.datagens.*;
import net.digitalpear.armored_wool.common.datagens.tags.AWBiomeTagProvider;
import net.digitalpear.armored_wool.common.datagens.tags.AWEnchantmentTagProvider;
import net.digitalpear.armored_wool.common.datagens.tags.AWItemTagProvider;
import net.digitalpear.armored_wool.common.entity.AWRegistryKeys;
import net.digitalpear.armored_wool.init.AWEnchantments;
import net.digitalpear.armored_wool.init.SheepVariants;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ArmoredWoolDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(AWModelProvider::new);
        pack.addProvider(AWLanguageProvider::new);
        pack.addProvider(AWRecipeProvider::new);

        pack.addProvider(AWItemTagProvider::new);
        pack.addProvider(AWEnchantmentTagProvider::new);
        pack.addProvider(AWBiomeTagProvider::new);

        pack.addProvider(AWSheepVariantProvider::new);

        pack.addProvider(AWEnchantmentProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(AWRegistryKeys.SHEEP_VARIANT, SheepVariants::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, AWEnchantments::bootstrap);
    }
}
