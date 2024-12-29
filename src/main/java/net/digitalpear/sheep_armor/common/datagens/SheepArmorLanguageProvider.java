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

        translationBuilder.add("text.autoconfig." + SheepArmor.MOD_ID + ".category.sheep_variant", "Sheep Variant Settings");
        translationBuilder.add("text.autoconfig." + SheepArmor.MOD_ID + ".category.sheep_armor", "Sheep Armor Settings");

        translationBuilder.add("text.autoconfig." + SheepArmor.MOD_ID + ".title", "Sheep Armor Config");
        addConfigTranslation(translationBuilder, "hasVariants", "Has Variants", "Whether biome specific sheep variants spawn.");
        addConfigTranslation(translationBuilder, "hasCustomColors", "Has Custom Colors", "Whether sheep use their variant's assigned colors when spawning.");
        addConfigTranslation(translationBuilder, "universalBarn", "Universal Barn Sheep Spawns", "Whether vanilla sheep have a chance of spawning alongside other valid sheep variants no matter what.");
        addConfigTranslation(translationBuilder, "sheepArmorEnabled", "Sheep Armor Enabled", "Whether sheep armor can be crafted/found in the world.");
        addConfigTranslation(translationBuilder, "hasInnerColoring", "Has Inner Coloring", "Whether sheep body hair is colored like their wool.");
    }


    public void addConfigTranslation(TranslationBuilder translationBuilder, String configID, String name, String desc){
        translationBuilder.add("text.autoconfig." + SheepArmor.MOD_ID + ".option."+ configID, name);
        translationBuilder.add("text.autoconfig." + SheepArmor.MOD_ID + ".option."+ configID +".@Tooltip", desc);
    }
}
