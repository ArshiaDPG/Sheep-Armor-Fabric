package net.digitalpear.armored_wool.common.items;

import me.shedaniel.autoconfig.AutoConfig;
import net.digitalpear.armored_wool.ArmoredWool;
import net.digitalpear.armored_wool.ArmoredWoolConfig;
import net.digitalpear.armored_wool.common.access.SheepArmorAccess;
import net.digitalpear.armored_wool.init.AWTags;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
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

    public SheepArmorItem(String materialName, RegistryEntry<ArmorMaterial> material, Item.Settings settings) {
        super(material, Type.BODY, settings);
        Identifier identifier = ArmoredWool.id(TEXTURE_PATH + materialName);
        this.armorTexture = identifier.withSuffixedPath(".png");
        this.woolarmorTexture = identifier.withSuffixedPath("_fur.png");
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        ArmoredWoolConfig config = AutoConfig.getConfigHolder(ArmoredWoolConfig.class).getConfig();
        return config.serverConfig.sheepArmorEnabled && super.isEnabled(enabledFeatures);
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
        if (entity instanceof SheepEntity sheep && entity.getType().isIn(AWTags.AWEntityTypeTags.ARMOR_COMPATIBLE_SHEEP)) {
            if (stack.isIn(AWTags.AWItemTags.SHEEP_ARMORS) && !sheep.isSheared() && !((SheepArmorAccess) sheep).hasArmor() && !sheep.isBaby()){
                if (!world.isClient()) {
                    sheep.equipBodyArmor(stack.copyWithCount(1));
                    stack.decrementUnlessCreative(1, user);
                }
                else{
                    return ActionResult.SUCCESS;
                }
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}