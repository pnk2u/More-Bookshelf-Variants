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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Map;

@Mod(Constants.MOD_ID)
public class MoreBookshelvesVariantsForge {
	public static final MoreBookshelvesVariants COMMON = new MoreBookshelvesVariants();

	public MoreBookshelvesVariantsForge() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void register(RegisterEvent event) {
		event.register(ForgeRegistries.Keys.BLOCKS, (helper) -> {
			COMMON.initBlocks();
			for (Map.Entry<ResourceLocation, Block> entry : COMMON.BLOCKS.entrySet()) {
				helper.register(entry.getKey(), entry.getValue());
			}
		});

		event.register(ForgeRegistries.Keys.ITEMS, (helper) -> {
			COMMON.initItems();
			for (Map.Entry<ResourceLocation, Item> entry : COMMON.ITEMS.entrySet()) {
				helper.register(entry.getKey(), entry.getValue());
			}
		});
	}

	@SubscribeEvent
	public void buildCreativeTab(BuildCreativeModeTabContentsEvent event) {
		// Add to ingredients tab
		if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			ItemStack before = new ItemStack(Items.BOOKSHELF);
			for (Block bookshelf : Bookshelves.BOOKSHELVES.values()) {
				ItemStack stack = new ItemStack(bookshelf);
				event.getEntries().putAfter(before, stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
				before = stack;
			}
		}
	}
}
