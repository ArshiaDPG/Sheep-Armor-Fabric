package net.digitalpear.sheep_armor.common.datagens;

import net.digitalpear.sheep_armor.init.SAItems;
import net.minecraft.data.DataOutput;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class SheepArnorRecipeProvider extends RecipeProvider {
    public SheepArnorRecipeProvider(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        SAItems.SHEEP_ARMOR_MAP.forEach((item, item2) -> makeArmorRecipe(item, item2).offerTo(exporter));
    }

    public ShapedRecipeJsonBuilder makeArmorRecipe(Item armor, Item ingredient){
        return ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, armor)
                .pattern("CCC")
                .pattern("CWC")
                .input('C', ingredient)
                .input('W', Items.LEATHER)
                .criterion(hasItem(ingredient), conditionsFromItem(ingredient));
    }
}
