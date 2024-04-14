package io.github.lieonlion.lolmbv.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

public class MoreBookshelfBlock extends Block {
    public MoreBookshelfBlock(MapColor colour) {
        super(AbstractBlock.Settings.copy(Blocks.BOOKSHELF).mapColor(colour));
    }

    public MoreBookshelfBlock(MapColor colour, BlockSoundGroup sound) {
        super(AbstractBlock.Settings.copy(Blocks.BOOKSHELF).mapColor(colour).sounds(sound));
    }
}