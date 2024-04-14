package io.github.lieonlion.lolmbv;

import io.github.lieonlion.lolmbv.init.MbvItemInit;
import io.github.lieonlion.lolmbv.init.MbvBlockInit;
import io.github.lieonlion.lolmbv.mixin.FireBlockInvoker;
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
        ((FireBlockInvoker) fireBlock).callRegisterFlammableBlock(MbvBlockInit.SPRUCE_BOOKSHELF, 30, 20);
        ((FireBlockInvoker) fireBlock).callRegisterFlammableBlock(MbvBlockInit.BIRCH_BOOKSHELF, 30, 20);
        ((FireBlockInvoker) fireBlock).callRegisterFlammableBlock(MbvBlockInit.JUNGLE_BOOKSHELF, 30, 20);
        ((FireBlockInvoker) fireBlock).callRegisterFlammableBlock(MbvBlockInit.ACACIA_BOOKSHELF, 30, 20);
        ((FireBlockInvoker) fireBlock).callRegisterFlammableBlock(MbvBlockInit.DARK_OAK_BOOKSHELF, 30, 20);
        ((FireBlockInvoker) fireBlock).callRegisterFlammableBlock(MbvBlockInit.MANGROVE_BOOKSHELF, 30, 20);
        ((FireBlockInvoker) fireBlock).callRegisterFlammableBlock(MbvBlockInit.CHERRY_BOOKSHELF, 30, 20);
        ((FireBlockInvoker) fireBlock).callRegisterFlammableBlock(MbvBlockInit.BAMBOO_BOOKSHELF, 30, 20);
    }
}