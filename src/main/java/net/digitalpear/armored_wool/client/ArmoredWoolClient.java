package net.digitalpear.armored_wool.client;

import net.digitalpear.armored_wool.client.layers.AWModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

public class ArmoredWoolClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(AWModelLayers.SHEEP_ARMOR, AWModelLayers::getArmorModelData);
        EntityModelLayerRegistry.registerModelLayer(AWModelLayers.SHEEP_ARMOR_WOOL, AWModelLayers::getArmorWoolModelData);
    }
}
