package net.digitalpear.armored_wool.common.datagens.tags;

import net.digitalpear.armored_wool.init.AWItems;
import net.digitalpear.armored_wool.init.AWTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class AWItemTagProvider extends FabricTagProvider<Item> {
    /**
     * Constructs a new {@link FabricTagProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output           the {@link FabricDataOutput} instance
     * @param registriesFuture the backing registry for the tag type
     */
    public AWItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }


    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(AWTags.AWItemTags.THORNY_SHEEP_ARMORS).add(AWItems.CACTUS_SHEEP_ARMOR);

        AWItems.SHEEP_ARMOR_MAP.keySet().forEach(item -> {
            if (item != AWItems.CACTUS_SHEEP_ARMOR){
                getOrCreateTagBuilder(AWTags.AWItemTags.SHEEP_ARMORS).add(item);
            }
        });
        getOrCreateTagBuilder(AWTags.AWItemTags.SHEEP_ARMORS).forceAddTag(AWTags.AWItemTags.THORNY_SHEEP_ARMORS);

        getOrCreateTagBuilder(AWTags.AWItemTags.REPAIRS_CACTUS_EQUIPMENT).add(Items.CACTUS);
        getOrCreateTagBuilder(AWTags.AWItemTags.REPAIRS_COPPER_EQUIPMENT).add(Items.COPPER_INGOT);
        getOrCreateTagBuilder(AWTags.AWItemTags.REPAIRS_AMETHYST_EQUIPMENT).add(Items.AMETHYST_SHARD);
        getOrCreateTagBuilder(AWTags.AWItemTags.REPAIRS_SHULKER_EQUIPMENT).add(Items.SHULKER_SHELL);

        getOrCreateTagBuilder(ItemTags.VANISHING_ENCHANTABLE).forceAddTag(AWTags.AWItemTags.SHEEP_ARMORS);
        getOrCreateTagBuilder(ItemTags.ARMOR_ENCHANTABLE).forceAddTag(AWTags.AWItemTags.SHEEP_ARMORS);
        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE).forceAddTag(AWTags.AWItemTags.SHEEP_ARMORS);
    }
}
