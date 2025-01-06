package net.digitalpear.armored_wool.common.datagens;

import net.digitalpear.armored_wool.init.AWItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;


public class AWModelProvider extends FabricModelProvider {
    public AWModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        AWItems.SHEEP_ARMOR_MAP.forEach((armor, item2) -> itemModelGenerator.register(armor, Models.GENERATED));
    }
}
