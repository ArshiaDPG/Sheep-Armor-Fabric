package net.digitalpear.sheep_armor.common.datagens;

import net.digitalpear.sheep_armor.init.SAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class SheepArmorModelProvider extends FabricModelProvider {
    public SheepArmorModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        SAItems.SHEEP_ARMOR_MAP.forEach((item, item2) -> {
            itemModelGenerator.register(item, Models.GENERATED);
        });
//        itemModelGenerator.register(SAItems.COPPER_SHEEP_ARMOR, Models.GENERATED);
//        itemModelGenerator.register(SAItems.AMETHYST_SHEEP_ARMOR, Models.GENERATED);
//        itemModelGenerator.register(SAItems.SHULKER_SHEEP_ARMOR, Models.GENERATED);
    }
}
