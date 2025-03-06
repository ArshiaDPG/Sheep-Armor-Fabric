package net.digitalpear.armored_wool.init;

import net.digitalpear.armored_wool.ArmoredWool;
import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.function.UnaryOperator;

public class AWDataComponentTypes {
    public static final ComponentType<RegistryEntry<SheepVariant>> SHEEP_VARIANT = register("sheep/variant", (builder) ->
            builder.codec(SheepVariant.ENTRY_CODEC).packetCodec(SheepVariant.ENTRY_PACKET_CODEC));

    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, ArmoredWool.id(id), builderOperator.apply(ComponentType.builder()).build());
    }

    public static void init() {}
}
