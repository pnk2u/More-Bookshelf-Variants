package io.github.lieonlion.lolmbv.init;

import io.github.lieonlion.lolmbv.MoreBookshelfVariants;
import io.github.lieonlion.lolmbv.block.MoreBookshelfBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class MbvBlockInit {
    public static final MoreBookshelfBlock SPRUCE_BOOKSHELF = new MoreBookshelfBlock(MapColor.SPRUCE_BROWN);
    public static final MoreBookshelfBlock BIRCH_BOOKSHELF = new MoreBookshelfBlock(MapColor.PALE_YELLOW);
    public static final MoreBookshelfBlock JUNGLE_BOOKSHELF = new MoreBookshelfBlock(MapColor.DIRT_BROWN);
    public static final MoreBookshelfBlock ACACIA_BOOKSHELF = new MoreBookshelfBlock(MapColor.ORANGE);
    public static final MoreBookshelfBlock DARK_OAK_BOOKSHELF = new MoreBookshelfBlock(MapColor.BROWN);
    public static final MoreBookshelfBlock MANGROVE_BOOKSHELF = new MoreBookshelfBlock(MapColor.RED);
    public static final MoreBookshelfBlock CHERRY_BOOKSHELF = new MoreBookshelfBlock(MapColor.TERRACOTTA_WHITE, BlockSoundGroup.CHERRY_WOOD);
    public static final MoreBookshelfBlock BAMBOO_BOOKSHELF = new MoreBookshelfBlock(MapColor.YELLOW, BlockSoundGroup.BAMBOO_WOOD);
    public static final MoreBookshelfBlock CRIMSON_BOOKSHELF = new MoreBookshelfBlock(MapColor.DULL_PINK, BlockSoundGroup.NETHER_WOOD);
    public static final MoreBookshelfBlock WARPED_BOOKSHELF = new MoreBookshelfBlock(MapColor.DARK_AQUA, BlockSoundGroup.NETHER_WOOD);

    public static void registerBlocks() {
        registerBlock("spruce_bookshelf", SPRUCE_BOOKSHELF);
        registerBlock("birch_bookshelf", BIRCH_BOOKSHELF);
        registerBlock("jungle_bookshelf", JUNGLE_BOOKSHELF);
        registerBlock("acacia_bookshelf", ACACIA_BOOKSHELF);
        registerBlock("dark_oak_bookshelf", DARK_OAK_BOOKSHELF);
        registerBlock("mangrove_bookshelf", MANGROVE_BOOKSHELF);
        registerBlock("cherry_bookshelf", CHERRY_BOOKSHELF);
        registerBlock("bamboo_bookshelf", BAMBOO_BOOKSHELF);
        registerBlock("crimson_bookshelf", CRIMSON_BOOKSHELF);
        registerBlock("warped_bookshelf", WARPED_BOOKSHELF);
    }

    private static void registerBlock(String name, Block block) {
        Registry.register(Registries.BLOCK, new Identifier(MoreBookshelfVariants.MODID, name), block);
    }
}