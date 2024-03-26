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
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class BookshelfDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(BlockTagsProvider::new);
		pack.addProvider(ItemTagsProvider::new);
		pack.addProvider(RecipeProvider::new);
		pack.addProvider(LootTableProvider::new);
		pack.addProvider(ModelProviders::new);
	}

	private static class BlockTagsProvider extends BlockTagProvider {
		protected BlockTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected void addTags(HolderLookup.Provider arg) {
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
	}

	private static class ItemTagsProvider extends ItemTagProvider {
		protected ItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected void addTags(HolderLookup.Provider arg) {
			Collection<Block> bookshelves = Bookshelves.BOOKSHELVES.values();
			FabricTagBuilder conventionalBookshelves = getOrCreateTagBuilder(ConventionalItemTags.BOOKSHELVES);

			for (Block bookshelf : bookshelves) {
				Item item = bookshelf.asItem();
				conventionalBookshelves.add(item);
			}
		}
	}

	private static class RecipeProvider extends FabricRecipeProvider {
		protected RecipeProvider(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void buildRecipes(Consumer<FinishedRecipe> exporter) {
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
	}

	private static class LootTableProvider extends FabricBlockLootTableProvider {
		protected LootTableProvider(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generate() {
			for (Block bookshelf : Bookshelves.BOOKSHELVES.values()) {
				this.add(bookshelf, (block) -> createSingleItemTableWithSilkTouch(block, Items.BOOK, ConstantValue.exactly(3.0F)));
			}
		}
	}

	private static class ModelProviders extends FabricModelProvider {
		protected ModelProviders(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
			for (ResourceLocation location : Bookshelves.BOOKSHELVES.keySet()) {
				Block bookshelf = Bookshelves.BOOKSHELVES.get(location);
				Block planks = Bookshelves.WOOD_MAPPINGS.get(location);

				ResourceLocation bookshelfTexture = TextureMapping.getBlockTexture(bookshelf);
				ResourceLocation planksTexture = TextureMapping.getBlockTexture(planks);

				TextureMapping textureMapping = TextureMapping.column(bookshelfTexture, planksTexture);
				ResourceLocation resourceLocation = ModelTemplates.CUBE_COLUMN.create(
					bookshelf,
					textureMapping,
					blockStateModelGenerator.modelOutput
				);

				blockStateModelGenerator.blockStateOutput.accept(
					BlockModelGenerators.createSimpleBlock(bookshelf, resourceLocation)
				);

				blockStateModelGenerator.delegateItemModel(bookshelf, resourceLocation);
			}
		}

		@Override
		public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		}
	}
}
