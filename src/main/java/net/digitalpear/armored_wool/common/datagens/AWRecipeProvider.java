package net.digitalpear.armored_wool.common.datagens;

import net.digitalpear.armored_wool.init.AWItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class AWRecipeProvider extends FabricRecipeProvider {

    public AWRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        AWItems.SHEEP_ARMOR_MAP.forEach((item, item2) -> makeSheepArmorRecipe(recipeExporter, item, item));
    }

    public void makeSheepArmorRecipe(RecipeExporter exporter, Item armor, Item ingredient){
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, armor)
                .pattern("CCC")
                .pattern("CLC")
                .input('C', ingredient)
                .input('L', Items.LEATHER)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient)).offerTo(exporter);
    }

    @Override
    public String getName() {
        return "recipe";
    }
}
