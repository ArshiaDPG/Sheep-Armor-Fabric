package net.digitalpear.sheep_armor.client;

import net.digitalpear.sheep_armor.client.layers.SAModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

public class SheepArmorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(SAModelLayers.SHEEP_ARMOR, SAModelLayers::getArmorModelData);
        EntityModelLayerRegistry.registerModelLayer(SAModelLayers.SHEEP_ARMOR_WOOL, SAModelLayers::getArmorWoolModelData);
    }
}
