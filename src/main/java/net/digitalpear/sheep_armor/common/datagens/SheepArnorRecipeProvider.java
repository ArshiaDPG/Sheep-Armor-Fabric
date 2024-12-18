package net.digitalpear.sheep_armor.common.datagens;

import net.digitalpear.sheep_armor.init.SAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class SheepArnorRecipeProvider extends FabricRecipeProvider {

    public SheepArnorRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                SAItems.SHEEP_ARMOR_MAP.forEach((armor, ingredient) -> makeArmorRecipe(armor, ingredient).offerTo(exporter));
            }

            public ShapedRecipeJsonBuilder makeArmorRecipe(Item armor, Item ingredient){
                return ShapedRecipeJsonBuilder.create(registries.getOrThrow(RegistryKeys.ITEM), RecipeCategory.COMBAT, armor)
                        .pattern("CCC")
                        .pattern("CWC")
                        .input('C', ingredient)
                        .input('W', Items.LEATHER)
                        .criterion(hasItem(ingredient), conditionsFromItem(ingredient));
            }
        };
    }

    @Override
    public String getName() {
        return "recipe";
    }
}
