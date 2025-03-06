package net.digitalpear.armored_wool;

import net.digitalpear.armored_wool.common.datagens.*;
import net.digitalpear.armored_wool.common.datagens.tags.AWBiomeTagProvider;
import net.digitalpear.armored_wool.common.entity.AWRegistryKeys;
import net.digitalpear.armored_wool.init.SheepVariants;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;

public class ArmoredWoolDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(AWBiomeTagProvider::new);
        pack.addProvider(AWSheepVariantProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(AWRegistryKeys.SHEEP_VARIANT, SheepVariants::bootstrap);
    }
}
