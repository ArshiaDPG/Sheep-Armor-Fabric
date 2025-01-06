package net.digitalpear.armored_wool.mixin;

import net.digitalpear.armored_wool.common.access.SheepRendererAccess;
import net.digitalpear.armored_wool.common.entity.SheepVariant;
import net.digitalpear.armored_wool.init.AWTags;
import net.minecraft.client.render.entity.state.SheepEntityRenderState;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SheepEntityRenderState.class)
public class SheepRenderStateMixin implements SheepRendererAccess {

    @Unique
    SheepVariant variant = null;
    @Unique
    ItemStack armorStack = ItemStack.EMPTY;

    @Override
    public SheepVariant getVariant() {
        return variant;
    }

    @Override
    public boolean hasArmor() {
        return !armorStack.isEmpty();
    }

    @Override
    public ItemStack getBodyArmor() {
        return armorStack;
    }

    @Override
    public void setBodyArmor(ItemStack stack) {
        if (stack.isIn(AWTags.AWItemTags.SHEEP_ARMORS)){
            armorStack = stack;
        }
        else{
            armorStack = ItemStack.EMPTY;
        }
    }

    @Override
    public void setVariant(SheepVariant variant) {
        this.variant = variant;
    }
}
