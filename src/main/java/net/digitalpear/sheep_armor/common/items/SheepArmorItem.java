package net.digitalpear.sheep_armor.common.items;

import me.shedaniel.autoconfig.AutoConfig;
import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.SheepArmorConfig;
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
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class SheepArmorItem extends ArmorItem {
    private final Identifier armorTexture;
    private final Identifier woolarmorTexture;

    private static final String TEXTURE_PATH = "textures/entity/sheep/armor/";

    public SheepArmorItem(String materialName, ArmorMaterial material, Item.Settings settings) {
        super(material, EquipmentType.BODY, settings);
        Identifier identifier = SheepArmor.id(TEXTURE_PATH + materialName);
        this.armorTexture = identifier.withSuffixedPath(".png");
        this.woolarmorTexture = identifier.withSuffixedPath("_fur.png");
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        SheepArmorConfig config = AutoConfig.getConfigHolder(SheepArmorConfig.class).getConfig();
        return config.sheepArmorEnabled && super.isEnabled(enabledFeatures);
    }

    public Identifier getArmorTexture() {
        return this.armorTexture;
    }

    public Identifier getWoolarmorTexture() {
        return this.woolarmorTexture;
    }

    @Override
    public SoundEvent getBreakSound() {
        return SoundEvents.ITEM_SHIELD_BREAK;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = user.getWorld();
        if (entity instanceof SheepEntity sheep) {
            if (stack.isIn(SATags.SAItemTags.SHEEP_ARMORS) && !sheep.isSheared() && !((SheepArmorAccess) sheep).hasArmor() && !sheep.isBaby()){
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