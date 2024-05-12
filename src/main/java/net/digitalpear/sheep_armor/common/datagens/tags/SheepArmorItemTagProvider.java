package net.digitalpear.sheep_armor.common.datagens.tags;

import net.digitalpear.sheep_armor.init.SATags;
import net.digitalpear.sheep_armor.init.SAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class SheepArmorItemTagProvider extends FabricTagProvider<Item> {
    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public SheepArmorItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        SAItems.SHEEP_ARMOR_MAP.forEach((item, item2) -> {
            getOrCreateTagBuilder(SATags.SAItemTags.SHEEP_ARMORS).add(item);
        });


        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE).addOptionalTag(SATags.SAItemTags.SHEEP_ARMORS);
        getOrCreateTagBuilder(ItemTags.ARMOR_ENCHANTABLE).addOptionalTag(SATags.SAItemTags.SHEEP_ARMORS);
    }
}
