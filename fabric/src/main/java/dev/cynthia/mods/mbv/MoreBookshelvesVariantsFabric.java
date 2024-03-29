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
import dev.cynthia.mods.mbv.platform.PlatformServices;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.mehvahdjukaar.every_compat.api.EveryCompatAPI;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;

import java.util.Map;

public class MoreBookshelvesVariantsFabric implements ModInitializer {
	public static final MoreBookshelvesVariants COMMON = new MoreBookshelvesVariants();

	@Override
	public void onInitialize() {
		COMMON.initBlocks();
		COMMON.initItems();

		for (Map.Entry<ResourceLocation, Block> entry : COMMON.BLOCKS.entrySet()) {
			Registry.register(BuiltInRegistries.BLOCK, entry.getKey(), entry.getValue());
		}

		for (Map.Entry<ResourceLocation, Item> entry : COMMON.ITEMS.entrySet()) {
			Registry.register(BuiltInRegistries.ITEM, entry.getKey(), entry.getValue());
		}

		Block[] chiseledBookshelves = new Block[ChiseledBookshelves.CHISELED_BOOKSHELVES.size()];
		ChiseledBookshelves.CHISELED_BOOKSHELVES.values().toArray(chiseledBookshelves);
		Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			new ResourceLocation(Constants.MOD_ID, "chiseled_bookshelf"),
			BlockEntityType.Builder.of(
				ChiseledBookShelfBlockEntity::new,
				ChiseledBookshelves.CHISELED_BOOKSHELVES.values().toArray(chiseledBookshelves)
			).build(null)
		);

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(content -> {
			Block[] bookshelves = new Block[Bookshelves.BOOKSHELVES.size()];
			content.addAfter(Items.BOOKSHELF, Bookshelves.BOOKSHELVES.values().toArray(bookshelves));
			content.addAfter(Items.CHISELED_BOOKSHELF, ChiseledBookshelves.CHISELED_BOOKSHELVES.values().toArray(chiseledBookshelves));
		});

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(content -> {
			content.addAfter(Items.CHISELED_BOOKSHELF, ChiseledBookshelves.CHISELED_BOOKSHELVES.values().toArray(chiseledBookshelves));
		});

		if (PlatformServices.PLATFORM.isModLoaded("everycomp")) {
			EveryCompatAPI.registerModule(new EveryCompatModule());
		}
	}
}
