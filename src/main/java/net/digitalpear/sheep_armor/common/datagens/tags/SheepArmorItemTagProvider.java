package net.digitalpear.sheep_armor.common.datagens.tags;

import net.digitalpear.sheep_armor.init.SATags;
import net.digitalpear.sheep_armor.init.SAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
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

        getOrCreateTagBuilder(SATags.SAItemTags.THORNY_SHEEP_ARMORS).add(SAItems.CACTUS_SHEEP_ARMOR);

        SAItems.SHEEP_ARMOR_MAP.keySet().forEach(item -> {
            if (!item.getDefaultStack().isIn(SATags.SAItemTags.THORNY_SHEEP_ARMORS)){
                getOrCreateTagBuilder(SATags.SAItemTags.SHEEP_ARMORS).add(item);
            }
        });

        getOrCreateTagBuilder(SATags.SAItemTags.REPAIRS_CACTUS_EQUIPMENT).add(Items.CACTUS);
        getOrCreateTagBuilder(SATags.SAItemTags.REPAIRS_COPPER_EQUIPMENT).add(Items.COPPER_INGOT);
        getOrCreateTagBuilder(SATags.SAItemTags.REPAIRS_AMETHYST_EQUIPMENT).add(Items.AMETHYST_SHARD);
        getOrCreateTagBuilder(SATags.SAItemTags.REPAIRS_SHULKER_EQUIPMENT).add(Items.SHULKER_SHELL);

        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE).forceAddTag(SATags.SAItemTags.SHEEP_ARMORS);
        getOrCreateTagBuilder(ItemTags.ARMOR_ENCHANTABLE).forceAddTag(SATags.SAItemTags.SHEEP_ARMORS);
        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE).forceAddTag(SATags.SAItemTags.SHEEP_ARMORS);
    }
}
