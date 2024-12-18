package net.digitalpear.sheep_armor.common.items;

import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.common.access.SheepArmorAccess;
import net.digitalpear.sheep_armor.init.SATags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SheepArmorItem extends ArmorItem {
    private final Identifier entityTexture;
    private final Identifier woolTexture;
    @Nullable
    private final Identifier overlayTexture;

    private String path = "textures/entity/sheep/armor/";

    public SheepArmorItem(String materialName, ArmorMaterial material, Item.Settings settings) {
        super(material, EquipmentType.BODY, settings);
        Identifier identifier = SheepArmor.id(path + materialName);
        this.entityTexture = identifier.withSuffixedPath(".png");
        this.woolTexture = identifier.withSuffixedPath("_fur.png");
        this.overlayTexture = null;
//        this.overlayTexture = hasOverlay ? identifier.withSuffixedPath("_overlay.png") : null;
    }

    public Identifier getEntityTexture() {
        return this.entityTexture;
    }
    public Identifier getWoolTexture() {
        return this.woolTexture;
    }

    @Nullable
    public Identifier getOverlayTexture() {
        return this.overlayTexture;
    }

    @Override
    public SoundEvent getBreakSound() {
        return SoundEvents.ITEM_SHIELD_BREAK;
    }

//    @Override
//    public boolean isEnchantable(ItemStack stack) {
//        return super.isEnchantable(stack);
//    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = user.getWorld();
        if (entity instanceof SheepEntity sheep) {
            if (stack.isIn(SATags.SAItemTags.SHEEP_ARMORS) && !sheep.isSheared() && !((SheepArmorAccess) sheep).sheep_Armor_Fabric$hasArmor() && !sheep.isBaby()){
                if (!world.isClient()) {
                    sheep.equipBodyArmor(stack.copyWithCount(1));
                    stack.decrementUnlessCreative(1, user);
                }
                else{
                    return ActionResult.SUCCESS.withNewHandStack(stack);
                }
            }

        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}