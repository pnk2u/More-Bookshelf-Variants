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
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Bookshelves {
	public static final Map<ResourceLocation, Block> BOOKSHELVES = new LinkedHashMap<>();
	public static final Map<ResourceLocation, Block> WOOD_MAPPINGS = new LinkedHashMap<>();

	public static final Block SPRUCE_BOOKSHELF = registerBookshelf(Blocks.SPRUCE_PLANKS, "spruce_bookshelf");
	public static final Block BIRCH_BOOKSHELF = registerBookshelf(Blocks.BIRCH_PLANKS, "birch_bookshelf");
	public static final Block JUNGLE_BOOKSHELF = registerBookshelf(Blocks.JUNGLE_PLANKS, "jungle_bookshelf");
	public static final Block ACACIA_BOOKSHELF = registerBookshelf(Blocks.ACACIA_PLANKS, "acacia_bookshelf");
	public static final Block DARK_OAK_BOOKSHELF = registerBookshelf(Blocks.DARK_OAK_PLANKS, "dark_oak_bookshelf");
	public static final Block MANGROVE_BOOKSHELF = registerBookshelf(Blocks.MANGROVE_PLANKS, "mangrove_bookshelf");
	public static final Block CHERRY_BOOKSHELF = registerBookshelf(Blocks.CHERRY_PLANKS, "cherry_bookshelf");
	public static final Block BAMBOO_BOOKSHELF = registerBookshelf(Blocks.BAMBOO_PLANKS, "bamboo_bookshelf");
	public static final Block CRIMSON_BOOKSHELF = registerBookshelf(Blocks.CRIMSON_PLANKS, "crimson_bookshelf");
	public static final Block WARPED_BOOKSHELF = registerBookshelf(Blocks.WARPED_PLANKS, "warped_bookshelf");

	public static final List<Block> FIREPROOF_BOOKSHELVES = List.of(CRIMSON_BOOKSHELF, WARPED_BOOKSHELF);

	private static Block registerBookshelf(Block planks, String id) {
		ResourceLocation loc = new ResourceLocation(Constants.MOD_ID, id);
		Block bookshelf = new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF));

		BOOKSHELVES.put(loc, bookshelf);
		WOOD_MAPPINGS.put(loc, planks);
		return bookshelf;
	}
}
