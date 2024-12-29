package net.digitalpear.sheep_armor.common.datagens;

import net.digitalpear.sheep_armor.SheepArmor;
import net.digitalpear.sheep_armor.init.SAEnchantments;
import net.digitalpear.sheep_armor.init.SAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class SheepArmorLanguageProvider extends FabricLanguageProvider {
    public SheepArmorLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(SAItems.CACTUS_SHEEP_ARMOR, "Cactus Sheep Armor");
        translationBuilder.add(SAItems.COPPER_SHEEP_ARMOR, "Copper Sheep Armor");
        translationBuilder.add(SAItems.AMETHYST_SHEEP_ARMOR, "Amethyst Sheep Armor");
        translationBuilder.add(SAItems.SHULKER_SHEEP_ARMOR, "Shulker Sheep Armor");

        translationBuilder.addEnchantment(SAEnchantments.TRIMMING, "Trimming");
        translationBuilder.addEnchantment(SAEnchantments.WOOLSPLOSION, "Woolsplosion");


        translationBuilder.add(SheepArmor.ClientConfig.hasInnerColoring.getTranslationKey(), "Has Inner Coloring");

        translationBuilder.add(SheepArmor.CommonConfig.hasVariants.getTranslationKey(), "Has Variants");
        translationBuilder.add(SheepArmor.CommonConfig.hasCustomColors.getTranslationKey(), "Has Custom Colors");
        translationBuilder.add(SheepArmor.CommonConfig.universalBarn.getTranslationKey(), "Universal Barn Spawns");
        translationBuilder.add(SheepArmor.CommonConfig.sheepArmorEnabled.getTranslationKey(), "Sheep Armor Enabled");
    }
}
