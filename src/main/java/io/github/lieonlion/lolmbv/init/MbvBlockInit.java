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
    public static final MoreBookshelfBlock SPRUCE_BOOKSHELF = new MoreBookshelfBlock("spruce", MapColor.SPRUCE_BROWN);
    public static final MoreBookshelfBlock BIRCH_BOOKSHELF = new MoreBookshelfBlock("birch", MapColor.PALE_YELLOW);
    public static final MoreBookshelfBlock JUNGLE_BOOKSHELF = new MoreBookshelfBlock("jungle", MapColor.DIRT_BROWN);
    public static final MoreBookshelfBlock ACACIA_BOOKSHELF = new MoreBookshelfBlock("acacia", MapColor.ORANGE);
    public static final MoreBookshelfBlock DARK_OAK_BOOKSHELF = new MoreBookshelfBlock("dark_oak", MapColor.BROWN);
    public static final MoreBookshelfBlock MANGROVE_BOOKSHELF = new MoreBookshelfBlock("mangrove", MapColor.RED);
    public static final MoreBookshelfBlock CHERRY_BOOKSHELF = new MoreBookshelfBlock("cherry", MapColor.TERRACOTTA_WHITE, BlockSoundGroup.CHERRY_WOOD);
    public static final MoreBookshelfBlock BAMBOO_BOOKSHELF = new MoreBookshelfBlock("bamboo", MapColor.YELLOW, BlockSoundGroup.BAMBOO_WOOD);
    public static final MoreBookshelfBlock CRIMSON_BOOKSHELF = new MoreBookshelfBlock("crimson", MapColor.DULL_PINK, BlockSoundGroup.NETHER_WOOD);
    public static final MoreBookshelfBlock WARPED_BOOKSHELF = new MoreBookshelfBlock("warped", MapColor.DARK_AQUA, BlockSoundGroup.NETHER_WOOD);

    public static void registerBlocks() {
        registerBlock(SPRUCE_BOOKSHELF);
        registerBlock(BIRCH_BOOKSHELF);
        registerBlock(JUNGLE_BOOKSHELF);
        registerBlock(ACACIA_BOOKSHELF);
        registerBlock(DARK_OAK_BOOKSHELF);
        registerBlock(MANGROVE_BOOKSHELF);
        registerBlock(CHERRY_BOOKSHELF);
        registerBlock(BAMBOO_BOOKSHELF);
        registerBlock(CRIMSON_BOOKSHELF);
        registerBlock(WARPED_BOOKSHELF);
    }

    private static void registerBlock(Block block) {
        String name = ((MoreBookshelfBlock) block).bookshelfWoodType + "_bookshelf";
        Registry.register(Registries.BLOCK, Identifier.of(MoreBookshelfVariants.MODID, name), block);
    }
}