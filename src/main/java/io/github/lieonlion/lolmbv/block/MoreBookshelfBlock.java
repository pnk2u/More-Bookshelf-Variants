package io.github.lieonlion.lolmbv.block;

import io.github.lieonlion.lolmbv.MoreBookshelfVariants;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static io.github.lieonlion.lolmbv.MoreBookshelfVariants.MODID;

public class MoreBookshelfBlock extends Block {
    public final String bookshelfWoodType;

    public MoreBookshelfBlock(String bookshelfWoodType, MapColor colour) {
        super(AbstractBlock.Settings.copy(Blocks.BOOKSHELF).mapColor(colour).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MODID, bookshelfWoodType + "_bookshelf"))));
        this.bookshelfWoodType = bookshelfWoodType;
    }

    public MoreBookshelfBlock(String bookshelfWoodType, MapColor colour, BlockSoundGroup sound) {
        super(AbstractBlock.Settings.copy(Blocks.BOOKSHELF).mapColor(colour).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(MODID, bookshelfWoodType + "_bookshelf"))));
        this.bookshelfWoodType = bookshelfWoodType;
    }
}