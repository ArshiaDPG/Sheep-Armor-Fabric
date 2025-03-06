package net.digitalpear.armored_wool.mixin;

import net.digitalpear.armored_wool.common.access.SheepVariantAccess;
import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.minecraft.client.render.entity.state.SheepEntityRenderState;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SheepEntityRenderState.class)
public class SheepRenderStateMixin implements SheepVariantAccess {

    @Unique
    RegistryEntry<SheepVariant> variant = null;

    @Override
    public RegistryEntry<SheepVariant> getVariant() {
        return variant;
    }

    @Override
    public void setVariant(RegistryEntry<SheepVariant> variant) {
        this.variant = variant;
    }


}
