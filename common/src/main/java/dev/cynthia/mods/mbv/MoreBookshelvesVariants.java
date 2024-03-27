/*
 * Copyright (c) Cynthia Rey et al., All rights reserved.
 * SPDX-License-Identifier: MPL-2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.cynthia.mods.mbv;

import dev.cynthia.mods.mbv.core.Bookshelves;
import dev.cynthia.mods.mbv.core.ChiseledBookshelves;
import dev.cynthia.mods.mbv.mixin.FireBlockInvoker;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.entity.BlockEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MoreBookshelvesVariants {
	public final Map<ResourceLocation, Block> BLOCKS = new LinkedHashMap<>();

	public final Map<ResourceLocation, Item> ITEMS = new LinkedHashMap<>();

	public void initBlocks() {
		registerBlocksFromMap(Bookshelves.BOOKSHELVES);
		registerBlocksFromMap(ChiseledBookshelves.CHISELED_BOOKSHELVES);

		// Make things flammable
		registerFlammableBlocks();
	}

	public void initItems() {
		registerItemsFromMap(Bookshelves.BOOKSHELVES);
		registerItemsFromMap(ChiseledBookshelves.CHISELED_BOOKSHELVES);
	}

	private void registerFlammableBlocks() {
		FireBlock fireBlock = (FireBlock) Blocks.FIRE;

		for (Block block : Bookshelves.BOOKSHELVES.values()) {
			if (!Bookshelves.FIREPROOF_BOOKSHELVES.contains(block)) {
				((FireBlockInvoker) fireBlock).callSetFlammable(block, 30, 20);
			}
		}
	}

	private void registerBlocksFromMap(Map<ResourceLocation, Block> map) {
		BLOCKS.putAll(map);
	}

	private void registerItemsFromMap(Map<ResourceLocation, Block> map) {
		for (Map.Entry<ResourceLocation, Block> mapping : map.entrySet()) {
			ITEMS.put(mapping.getKey(), new BlockItem(mapping.getValue(), new Item.Properties()));
		}
	}
}
