package io.github.lieonlion.lolmbv.init;

import io.github.lieonlion.lolmbv.MoreBookshelfVariants;
import io.github.lieonlion.lolmbv.block.MoreBookshelfBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class MbvItemInit {
    public static BlockItem SPRUCE_BOOKSHELF_I = itemFromBlock(MbvBlockInit.SPRUCE_BOOKSHELF);
    public static BlockItem BIRCH_BOOKSHELF_I = itemFromBlock(MbvBlockInit.BIRCH_BOOKSHELF);
    public static BlockItem JUNGLE_BOOKSHELF_I = itemFromBlock(MbvBlockInit.JUNGLE_BOOKSHELF);
    public static BlockItem ACACIA_BOOKSHELF_I = itemFromBlock(MbvBlockInit.ACACIA_BOOKSHELF);
    public static BlockItem DARK_OAK_BOOKSHELF_I = itemFromBlock(MbvBlockInit.DARK_OAK_BOOKSHELF);
    public static BlockItem MANGROVE_BOOKSHELF_I = itemFromBlock(MbvBlockInit.MANGROVE_BOOKSHELF);
    public static BlockItem CHERRY_BOOKSHELF_I = itemFromBlock(MbvBlockInit.CHERRY_BOOKSHELF);
    public static BlockItem BAMBOO_BOOKSHELF_I = itemFromBlock(MbvBlockInit.BAMBOO_BOOKSHELF);
    public static BlockItem CRIMSON_BOOKSHELF_I = itemFromBlock(MbvBlockInit.CRIMSON_BOOKSHELF);
    public static BlockItem WARPED_BOOKSHELF_I = itemFromBlock(MbvBlockInit.WARPED_BOOKSHELF);

    public static BlockItem itemFromBlock(MoreBookshelfBlock moreBookshelfBlock) {
        return new BlockItem(moreBookshelfBlock, setProperties(moreBookshelfBlock));
    }

    public static Item.Settings setProperties(MoreBookshelfBlock moreBookshelfBlock) {
        return new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM,Registries.BLOCK.getId(moreBookshelfBlock))).useBlockPrefixedTranslationKey();
    }

    public static void registerItems() {
        registerItem(SPRUCE_BOOKSHELF_I, Items.BOOKSHELF);
        registerItem(BIRCH_BOOKSHELF_I, SPRUCE_BOOKSHELF_I);
        registerItem(JUNGLE_BOOKSHELF_I, BIRCH_BOOKSHELF_I);
        registerItem(ACACIA_BOOKSHELF_I, JUNGLE_BOOKSHELF_I);
        registerItem(DARK_OAK_BOOKSHELF_I, ACACIA_BOOKSHELF_I);
        registerItem(MANGROVE_BOOKSHELF_I, DARK_OAK_BOOKSHELF_I);
        registerItem(CHERRY_BOOKSHELF_I, MANGROVE_BOOKSHELF_I);
        registerItem(BAMBOO_BOOKSHELF_I, CHERRY_BOOKSHELF_I);
        registerItem(CRIMSON_BOOKSHELF_I, BAMBOO_BOOKSHELF_I);
        registerItem(WARPED_BOOKSHELF_I, CRIMSON_BOOKSHELF_I);
    }

    public static void registerItem(Item item, Item after) {
        String name = ((MoreBookshelfBlock)((BlockItem) item).getBlock()).bookshelfWoodType + "_bookshelf";
        Registry.register(Registries.ITEM, Identifier.of(MoreBookshelfVariants.MODID, name), item);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(after, item));
    }
}