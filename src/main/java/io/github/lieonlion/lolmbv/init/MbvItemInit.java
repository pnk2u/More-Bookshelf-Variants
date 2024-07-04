package io.github.lieonlion.lolmbv.init;

import io.github.lieonlion.lolmbv.MoreBookshelfVariants;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MbvItemInit {
    public static BlockItem SPRUCE_BOOKSHELF_I = new BlockItem(MbvBlockInit.SPRUCE_BOOKSHELF, (new Item.Settings()));
    public static BlockItem BIRCH_BOOKSHELF_I = new BlockItem(MbvBlockInit.BIRCH_BOOKSHELF, (new Item.Settings()));
    public static BlockItem JUNGLE_BOOKSHELF_I = new BlockItem(MbvBlockInit.JUNGLE_BOOKSHELF, (new Item.Settings()));
    public static BlockItem ACACIA_BOOKSHELF_I = new BlockItem(MbvBlockInit.ACACIA_BOOKSHELF, (new Item.Settings()));
    public static BlockItem DARK_OAK_BOOKSHELF_I = new BlockItem(MbvBlockInit.DARK_OAK_BOOKSHELF, (new Item.Settings()));
    public static BlockItem MANGROVE_BOOKSHELF_I = new BlockItem(MbvBlockInit.MANGROVE_BOOKSHELF, (new Item.Settings()));
    public static BlockItem CHERRY_BOOKSHELF_I = new BlockItem(MbvBlockInit.CHERRY_BOOKSHELF, (new Item.Settings()));
    public static BlockItem BAMBOO_BOOKSHELF_I = new BlockItem(MbvBlockInit.BAMBOO_BOOKSHELF, (new Item.Settings()));
    public static BlockItem CRIMSON_BOOKSHELF_I = new BlockItem(MbvBlockInit.CRIMSON_BOOKSHELF, (new Item.Settings()));
    public static BlockItem WARPED_BOOKSHELF_I = new BlockItem(MbvBlockInit.WARPED_BOOKSHELF, (new Item.Settings()));

    public static void registerItems() {
        registerItem("spruce_bookshelf", SPRUCE_BOOKSHELF_I, Items.BOOKSHELF);
        registerItem("birch_bookshelf", BIRCH_BOOKSHELF_I, SPRUCE_BOOKSHELF_I);
        registerItem("jungle_bookshelf", JUNGLE_BOOKSHELF_I, BIRCH_BOOKSHELF_I);
        registerItem("acacia_bookshelf", ACACIA_BOOKSHELF_I, JUNGLE_BOOKSHELF_I);
        registerItem("dark_oak_bookshelf", DARK_OAK_BOOKSHELF_I, ACACIA_BOOKSHELF_I);
        registerItem("mangrove_bookshelf", MANGROVE_BOOKSHELF_I, DARK_OAK_BOOKSHELF_I);
        registerItem("cherry_bookshelf", CHERRY_BOOKSHELF_I, MANGROVE_BOOKSHELF_I);
        registerItem("bamboo_bookshelf", BAMBOO_BOOKSHELF_I, CHERRY_BOOKSHELF_I);
        registerItem("crimson_bookshelf", CRIMSON_BOOKSHELF_I, BAMBOO_BOOKSHELF_I);
        registerItem("warped_bookshelf", WARPED_BOOKSHELF_I, CRIMSON_BOOKSHELF_I);
    }

    public static void registerItem(String name, Item item, Item after) {
        Registry.register(Registries.ITEM, Identifier.of(MoreBookshelfVariants.MODID, name), item);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.addAfter(after, item));
    }
}