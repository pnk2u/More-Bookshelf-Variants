package io.github.lieonlion.lolmbv;

import io.github.lieonlion.lolmbv.init.MbvItemInit;
import io.github.lieonlion.lolmbv.init.MbvBlockInit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;

public class MoreBookshelfVariants implements ModInitializer {
    public static final String MODID = "lolmbv";

    @Override
    public void onInitialize() {
        MbvBlockInit.registerBlocks();
        MbvItemInit.registerItems();

        registerFlammableBlocks();
    }

    private void registerFlammableBlocks() {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.registerFlammableBlock(MbvBlockInit.SPRUCE_BOOKSHELF, 30, 20);
        fireBlock.registerFlammableBlock(MbvBlockInit.BIRCH_BOOKSHELF, 30, 20);
        fireBlock.registerFlammableBlock(MbvBlockInit.JUNGLE_BOOKSHELF, 30, 20);
        fireBlock.registerFlammableBlock(MbvBlockInit.ACACIA_BOOKSHELF, 30, 20);
        fireBlock.registerFlammableBlock(MbvBlockInit.DARK_OAK_BOOKSHELF, 30, 20);
        fireBlock.registerFlammableBlock(MbvBlockInit.MANGROVE_BOOKSHELF, 30, 20);
        fireBlock.registerFlammableBlock(MbvBlockInit.CHERRY_BOOKSHELF, 30, 20);
        fireBlock.registerFlammableBlock(MbvBlockInit.BAMBOO_BOOKSHELF, 30, 20);
    }
}