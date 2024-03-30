/*
 * Copyright (c) Cynthia Rey et al., All rights reserved.
 * SPDX-License-Identifier: MPL-2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.cynthia.mods.mbv.tags;

import dev.cynthia.mods.mbv.Constants;
import dev.cynthia.mods.mbv.blocks.Bookshelves;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.Map;

public class StructureTags {
	public static final TagKey<Structure> HAS_SPRUCE_BOOKSHELVES = createTag("has_spruce_bookshelves");
	public static final TagKey<Structure> HAS_BIRCH_BOOKSHELVES = createTag("has_birch_bookshelves");
	public static final TagKey<Structure> HAS_JUNGLE_BOOKSHELVES = createTag("has_jungle_bookshelves");
	public static final TagKey<Structure> HAS_ACACIA_BOOKSHELVES = createTag("has_acacia_bookshelves");
	public static final TagKey<Structure> HAS_DARK_OAK_BOOKSHELVES = createTag("has_dark_oak_bookshelves");
	public static final TagKey<Structure> HAS_MANGROVE_BOOKSHELVES = createTag("has_mangrove_bookshelves");
	public static final TagKey<Structure> HAS_CHERRY_BOOKSHELVES = createTag("has_cherry_bookshelves");
	public static final TagKey<Structure> HAS_BAMBOO_BOOKSHELVES = createTag("has_bamboo_bookshelves");
	public static final TagKey<Structure> HAS_CRIMSON_BOOKSHELVES = createTag("has_crimson_bookshelves");
	public static final TagKey<Structure> HAS_WARPED_BOOKSHELVES = createTag("has_warped_bookshelves");

	public static final Map<TagKey<Structure>, Block> TAG_BOOKSHELF_MAP = Map.of(
		HAS_SPRUCE_BOOKSHELVES, Bookshelves.SPRUCE_BOOKSHELF,
		HAS_BIRCH_BOOKSHELVES, Bookshelves.BIRCH_BOOKSHELF,
		HAS_JUNGLE_BOOKSHELVES, Bookshelves.JUNGLE_BOOKSHELF,
		HAS_ACACIA_BOOKSHELVES, Bookshelves.ACACIA_BOOKSHELF,
		HAS_DARK_OAK_BOOKSHELVES, Bookshelves.DARK_OAK_BOOKSHELF,
		HAS_MANGROVE_BOOKSHELVES, Bookshelves.MANGROVE_BOOKSHELF,
		HAS_CHERRY_BOOKSHELVES, Bookshelves.CHERRY_BOOKSHELF,
		HAS_BAMBOO_BOOKSHELVES, Bookshelves.BAMBOO_BOOKSHELF,
		HAS_CRIMSON_BOOKSHELVES, Bookshelves.CRIMSON_BOOKSHELF,
		HAS_WARPED_BOOKSHELVES, Bookshelves.WARPED_BOOKSHELF
	);

	private static TagKey<Structure> createTag(String tag) {
		return TagKey.create(Registries.STRUCTURE, new ResourceLocation(Constants.MOD_ID, tag));
	}
}
