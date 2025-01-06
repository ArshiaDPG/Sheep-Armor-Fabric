package net.digitalpear.armored_wool.common.datagens.tags;

import net.digitalpear.armored_wool.init.AWTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AWEntityTagProvider extends FabricTagProvider<EntityType<?>> {
    public AWEntityTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ENTITY_TYPE, registriesFuture);
    }
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(AWTags.AWEntityTypeTags.ARMOR_COMPATIBLE_SHEEP)
                .add(EntityType.SHEEP);
    }
}
