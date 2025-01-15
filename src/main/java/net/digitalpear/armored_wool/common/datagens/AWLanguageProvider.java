package net.digitalpear.armored_wool.common.datagens;

import net.digitalpear.armored_wool.ArmoredWool;
import net.digitalpear.armored_wool.init.AWEnchantments;
import net.digitalpear.armored_wool.init.AWItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AWLanguageProvider extends FabricLanguageProvider {
    public AWLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(AWItems.CACTUS_SHEEP_ARMOR, "Cactus Sheep Armor");
        translationBuilder.add(AWItems.COPPER_SHEEP_ARMOR, "Copper Sheep Armor");
        translationBuilder.add(AWItems.AMETHYST_SHEEP_ARMOR, "Amethyst Sheep Armor");
        translationBuilder.add(AWItems.SHULKER_SHEEP_ARMOR, "Shulker Sheep Armor");

        translationBuilder.addEnchantment(AWEnchantments.TRIMMING, "Trimming");
        translationBuilder.addEnchantment(AWEnchantments.WOOLSPLOSION, "Woolsplosion");
        translationBuilder.addEnchantment(AWEnchantments.LIGHTNESS, "Lightness");

        translationBuilder.add("text.autoconfig." + ArmoredWool.MOD_ID + ".title", ArmoredWool.MOD_NAME + " Config");

        registerTitlesAndCategories(translationBuilder, "Server");
        registerTitlesAndCategories(translationBuilder, "Client");

        addServerConfigTranslation(translationBuilder, "hasVariants", "Has Variants", "Whether biome specific sheep variants spawn.");
        addServerConfigTranslation(translationBuilder, "hasCustomColors", "Has Custom Colors", "Whether sheep use their variant's assigned colors when spawning.");
        addServerConfigTranslation(translationBuilder, "universalBarn", "Universal Barn Sheep Spawns", "Whether vanilla sheep have a chance of spawning alongside other valid sheep variants no matter what.");
        addServerConfigTranslation(translationBuilder, "sheepArmorEnabled", "Sheep Armor Enabled", "Whether sheep armor can be crafted/found in the world.");
        addServerConfigTranslation(translationBuilder, "thornyArmorDamage", "Thorny Armor Damage", "The amount of damage sheep armor with built-in thorns does.");
        addServerConfigTranslation(translationBuilder, "lootTableAdditions", "Loot Table Additions", "List of loot tables and the sheep armor that is added to them.");

        addClientConfigTranslation(translationBuilder, "easterEggVariants", "Easter Egg Sheep Variants", "A list of custom sheep variants that can be accessed by naming any sheep. [Nametag, Texture Name]");
        addClientConfigTranslation(translationBuilder, "hasInnerColoring", "Has Inner Coloring", "Whether sheep body hair is colored like their wool.");
    }

    public void registerTitlesAndCategories(TranslationBuilder translationBuilder, String name){
        translationBuilder.add("text.autoconfig." + ArmoredWool.MOD_ID + ".category." + ArmoredWool.MOD_ID + "_" + name.toLowerCase(), name + " Config");
    }

    public void addServerConfigTranslation(TranslationBuilder translationBuilder, String configID, String name, String desc){
        addConfigTranslation(translationBuilder, configID, name, "server", desc);
    }
    public void addClientConfigTranslation(TranslationBuilder translationBuilder, String configID, String name, String desc){
        addConfigTranslation(translationBuilder, configID, name, "client", desc);
    }
    public void addConfigTranslation(TranslationBuilder translationBuilder, String configID, String name, String type, String desc){
        translationBuilder.add("text.autoconfig." + ArmoredWool.MOD_ID + ".option." + type + "Config." + configID, name);
        translationBuilder.add("text.autoconfig." + ArmoredWool.MOD_ID + ".option." + type + "Config." + configID +".@Tooltip", desc);
    }
}
