/*
 * Copyright (c) Cynthia Rey et al., All rights reserved.
 * SPDX-License-Identifier: MPL-2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.cynthia.mods.mbv.datagen;

import dev.cynthia.mods.mbv.core.Bookshelves;
import dev.cynthia.mods.mbv.core.ChiseledBookshelves;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class TagsProvider {
	public static class BlockTagsProvider extends FabricTagProvider.BlockTagProvider {
		public BlockTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected void addTags(HolderLookup.Provider arg) {
			addBookshelfTags();
			addChiseledBookshelfTags();
		}

		private void addBookshelfTags() {
			Collection<Block> bookshelves = Bookshelves.BOOKSHELVES.values();
			FabricTagBuilder mineableWithAxe = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE);
			FabricTagBuilder enchantmentPowerProvider = getOrCreateTagBuilder(BlockTags.ENCHANTMENT_POWER_PROVIDER);
			FabricTagBuilder conventionalBookshelves = getOrCreateTagBuilder(ConventionalBlockTags.BOOKSHELVES);

			for (Block bookshelf : bookshelves) {
				mineableWithAxe.add(bookshelf);
				enchantmentPowerProvider.add(bookshelf);
				conventionalBookshelves.add(bookshelf);
			}
		}

		private void addChiseledBookshelfTags() {
			Collection<Block> bookshelves = ChiseledBookshelves.CHISELED_BOOKSHELVES.values();
			FabricTagBuilder mineableWithAxe = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE);

			for (Block bookshelf : bookshelves) {
				mineableWithAxe.add(bookshelf);
			}
		}
	}

	public static class ItemTagsProvider extends FabricTagProvider.ItemTagProvider {
		public ItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
			super(output, completableFuture);
		}

		@Override
		protected void addTags(HolderLookup.Provider arg) {
			addBookshelfTags();
			addChiseledBookshelfTags();
		}

		private void addBookshelfTags() {
			Collection<Block> bookshelves = Bookshelves.BOOKSHELVES.values();
			FabricTagBuilder conventionalBookshelves = getOrCreateTagBuilder(ConventionalItemTags.BOOKSHELVES);

			for (Block bookshelf : bookshelves) {
				Item item = bookshelf.asItem();
				conventionalBookshelves.add(item);
			}
		}

		private void addChiseledBookshelfTags() {
			// No tags to add
		}
	}
}
