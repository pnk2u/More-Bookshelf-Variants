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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.function.Consumer;

public class RecipeProvider extends FabricRecipeProvider {
	private static final Map<Block, Block> SLAB_MAPPING = Map.of(
		Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_SLAB,
		Blocks.BIRCH_PLANKS, Blocks.BIRCH_SLAB,
		Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_SLAB,
		Blocks.ACACIA_PLANKS, Blocks.ACACIA_SLAB,
		Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_SLAB,
		Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_SLAB,
		Blocks.CHERRY_PLANKS, Blocks.CHERRY_SLAB,
		Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_SLAB,
		Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_SLAB,
		Blocks.WARPED_PLANKS, Blocks.WARPED_SLAB
	);

	public RecipeProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> exporter) {
		buildBookshelfRecipes(exporter);
		buildChiseledBookshelfRecipes(exporter);
	}

	private void buildBookshelfRecipes(Consumer<FinishedRecipe> exporter) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.BOOKSHELF)
			.pattern("PPP")
			.pattern("BBB")
			.pattern("PPP")
			.define('P', Blocks.OAK_PLANKS)
			.define('B', Items.BOOK)
			.unlockedBy("has_book", FabricRecipeProvider.has(Items.BOOK))
			.save(exporter, BuiltInRegistries.BLOCK.getKey(Blocks.BOOKSHELF));

		for (Map.Entry<ResourceLocation, Block> mapping : Bookshelves.WOOD_MAPPINGS.entrySet()) {
			Block bookshelf = Bookshelves.BOOKSHELVES.get(mapping.getKey());

			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf)
				.pattern("PPP")
				.pattern("BBB")
				.pattern("PPP")
				.define('P', mapping.getValue())
				.define('B', Items.BOOK)
				.unlockedBy("has_book", FabricRecipeProvider.has(Items.BOOK))
				.save(exporter, mapping.getKey());
		}
	}

	private void buildChiseledBookshelfRecipes(Consumer<FinishedRecipe> exporter) {
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CHISELED_BOOKSHELF)
			.pattern("PPP")
			.pattern("SSS")
			.pattern("PPP")
			.define('P', Blocks.OAK_PLANKS)
			.define('S', Blocks.OAK_SLAB)
			.unlockedBy("has_book", FabricRecipeProvider.has(Items.BOOK))
			.save(exporter, BuiltInRegistries.BLOCK.getKey(Blocks.CHISELED_BOOKSHELF));

		for (Map.Entry<ResourceLocation, Block> mapping : ChiseledBookshelves.WOOD_MAPPINGS.entrySet()) {
			Block bookshelf = ChiseledBookshelves.CHISELED_BOOKSHELVES.get(mapping.getKey());

			ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf)
				.pattern("PPP")
				.pattern("SSS")
				.pattern("PPP")
				.define('P', mapping.getValue())
				.define('S', SLAB_MAPPING.get(mapping.getValue()))
				.unlockedBy("has_book", FabricRecipeProvider.has(Items.BOOK))
				.save(exporter, mapping.getKey());
		}
	}
}
