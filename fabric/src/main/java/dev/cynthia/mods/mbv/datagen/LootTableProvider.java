/*
 * Copyright (c) Cynthia Rey et al., All rights reserved.
 * SPDX-License-Identifier: MPL-2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.cynthia.mods.mbv.datagen;

import dev.cynthia.mods.mbv.blocks.Bookshelves;
import dev.cynthia.mods.mbv.blocks.ChiseledBookshelves;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class LootTableProvider extends FabricBlockLootTableProvider {
	public LootTableProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generate() {
		for (Block bookshelf : Bookshelves.BOOKSHELVES.values()) {
			this.add(bookshelf, (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
		}

		for (Block bookshelf : ChiseledBookshelves.CHISELED_BOOKSHELVES.values()) {
			this.dropWhenSilkTouch(bookshelf);
		}
	}
}
