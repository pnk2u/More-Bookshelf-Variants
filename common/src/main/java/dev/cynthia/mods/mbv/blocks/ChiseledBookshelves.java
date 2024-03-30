/*
 * Copyright (c) Cynthia Rey et al., All rights reserved.
 * SPDX-License-Identifier: MPL-2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.cynthia.mods.mbv.blocks;

import dev.cynthia.mods.mbv.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChiseledBookshelves {
	public static final Map<ResourceLocation, Block> CHISELED_BOOKSHELVES = new LinkedHashMap<>();
	public static final Map<ResourceLocation, Block> WOOD_MAPPINGS = new LinkedHashMap<>();

	public static final Block SPRUCE_CHISELED_BOOKSHELF = registerChiseledBookshelf(Blocks.SPRUCE_PLANKS, "spruce_chiseled_bookshelf");
	public static final Block BIRCH_CHISELED_BOOKSHELF = registerChiseledBookshelf(Blocks.BIRCH_PLANKS, "birch_chiseled_bookshelf");
	public static final Block JUNGLE_CHISELED_BOOKSHELF = registerChiseledBookshelf(Blocks.JUNGLE_PLANKS, "jungle_chiseled_bookshelf");
	public static final Block ACACIA_CHISELED_BOOKSHELF = registerChiseledBookshelf(Blocks.ACACIA_PLANKS, "acacia_chiseled_bookshelf");
	public static final Block DARK_OAK_CHISELED_BOOKSHELF = registerChiseledBookshelf(Blocks.DARK_OAK_PLANKS, "dark_oak_chiseled_bookshelf");
	public static final Block MANGROVE_CHISELED_BOOKSHELF = registerChiseledBookshelf(Blocks.MANGROVE_PLANKS, "mangrove_chiseled_bookshelf");
	public static final Block CHERRY_CHISELED_BOOKSHELF = registerChiseledBookshelf(Blocks.CHERRY_PLANKS, "cherry_chiseled_bookshelf");
	public static final Block BAMBOO_CHISELED_BOOKSHELF = registerChiseledBookshelf(Blocks.BAMBOO_PLANKS, "bamboo_chiseled_bookshelf");
	public static final Block CRIMSON_CHISELED_BOOKSHELF = registerChiseledBookshelf(Blocks.CRIMSON_PLANKS, "crimson_chiseled_bookshelf");
	public static final Block WARPED_CHISELED_BOOKSHELF = registerChiseledBookshelf(Blocks.WARPED_PLANKS, "warped_chiseled_bookshelf");

	private static Block registerChiseledBookshelf(Block planks, String id) {
		ResourceLocation loc = new ResourceLocation(Constants.MOD_ID, id);
		Block bookshelf = new ChiseledBookShelfBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_BOOKSHELF));

		CHISELED_BOOKSHELVES.put(loc, bookshelf);
		WOOD_MAPPINGS.put(loc, planks);
		return bookshelf;
	}
}
