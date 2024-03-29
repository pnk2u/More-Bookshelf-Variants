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
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.Condition;
import net.minecraft.data.models.blockstates.MultiPartGenerator;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ModelProvider extends FabricModelProvider {
	private static final Map<ModelCacheKey, ResourceLocation> SLOT_MODEL_CACHE = new HashMap<>();

	private static final Map<Direction, VariantProperties.Rotation> DIRECTIONS = Map.of(
		Direction.NORTH, VariantProperties.Rotation.R0,
		Direction.EAST, VariantProperties.Rotation.R90,
		Direction.SOUTH, VariantProperties.Rotation.R180,
		Direction.WEST, VariantProperties.Rotation.R270
	);

	private static final Map<BooleanProperty, ModelTemplate> STATE_MODELS = Map.of(
		BlockStateProperties.CHISELED_BOOKSHELF_SLOT_0_OCCUPIED,
		ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_LEFT,

		BlockStateProperties.CHISELED_BOOKSHELF_SLOT_1_OCCUPIED,
		ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_MID,

		BlockStateProperties.CHISELED_BOOKSHELF_SLOT_2_OCCUPIED,
		ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_RIGHT,

		BlockStateProperties.CHISELED_BOOKSHELF_SLOT_3_OCCUPIED,
		ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_LEFT,

		BlockStateProperties.CHISELED_BOOKSHELF_SLOT_4_OCCUPIED,
		ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_MID,

		BlockStateProperties.CHISELED_BOOKSHELF_SLOT_5_OCCUPIED,
		ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_RIGHT
	);


	private static final ModelTemplate CHISELED_BOOKSHELF_TEMPLATE = new ModelTemplate(
		Optional.of(new ResourceLocation("minecraft", "block/chiseled_bookshelf")),
		Optional.empty(),
		TextureSlot.TOP,
		TextureSlot.SIDE,
		TextureSlot.PARTICLE
	);


	private static final ModelTemplate CHISELED_BOOKSHELF_INVENTORY_TEMPLATE = new ModelTemplate(
		Optional.of(new ResourceLocation("minecraft", "block/chiseled_bookshelf_inventory")),
		Optional.empty(),
		TextureSlot.TOP,
		TextureSlot.SIDE,
		TextureSlot.FRONT,
		TextureSlot.PARTICLE
	);

	public ModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		generateBookshelfBlockStateModels(blockStateModelGenerator);
		generateChiseledBookshelfBlockStateModels(blockStateModelGenerator);
	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		// No models to generate
	}

	private void generateBookshelfBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
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

	private void generateChiseledBookshelfBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		for (ResourceLocation location : ChiseledBookshelves.CHISELED_BOOKSHELVES.keySet()) {
			Block bookshelf = ChiseledBookshelves.CHISELED_BOOKSHELVES.get(location);
			addChiseledBookshelfBlock(location, bookshelf, blockStateModelGenerator);
			addChiseledBookshelfInventory(location, bookshelf, blockStateModelGenerator);
			addChiseledBookshelfBlockStates(bookshelf, blockStateModelGenerator);
		}
	}

	private void addChiseledBookshelfBlock(ResourceLocation loc, Block block, BlockModelGenerators blockStateModelGenerator) {
		ResourceLocation modelLoc = ModelLocationUtils.getModelLocation(block);
		TextureMapping mapping = new TextureMapping()
			.put(TextureSlot.TOP, new ResourceLocation(loc.getNamespace(), "block/" + loc.getPath() + "_top"))
			.put(TextureSlot.SIDE, new ResourceLocation(loc.getNamespace(), "block/" + loc.getPath() + "_side"))
			.put(TextureSlot.PARTICLE, new ResourceLocation(loc.getNamespace(), "block/" + loc.getPath() + "_top"));

		CHISELED_BOOKSHELF_TEMPLATE.create(modelLoc, mapping, blockStateModelGenerator.modelOutput);
	}

	private void addChiseledBookshelfInventory(ResourceLocation loc, Block block, BlockModelGenerators blockStateModelGenerator) {
		ResourceLocation modelLoc = ModelLocationUtils.getModelLocation(block, "_inventory");
		TextureMapping mapping = new TextureMapping()
			.put(TextureSlot.TOP, new ResourceLocation(loc.getNamespace(), "block/" + loc.getPath() + "_top"))
			.put(TextureSlot.SIDE, new ResourceLocation(loc.getNamespace(), "block/" + loc.getPath() + "_side"))
			.put(TextureSlot.FRONT, new ResourceLocation(loc.getNamespace(), "block/" + loc.getPath() + "_empty"))
			.put(TextureSlot.PARTICLE, new ResourceLocation(loc.getNamespace(), "block/" + loc.getPath() + "_top"));

		CHISELED_BOOKSHELF_INVENTORY_TEMPLATE.create(modelLoc, mapping, blockStateModelGenerator.modelOutput);
	}

	private void addChiseledBookshelfBlockStates(Block block, BlockModelGenerators blockStateModelGenerator) {
		ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation(block);
		MultiPartGenerator multiPartGenerator = MultiPartGenerator.multiPart(block);

		DIRECTIONS.forEach((direction, rotation) -> {
			Condition.TerminalCondition terminalCondition = Condition.condition().term(BlockStateProperties.HORIZONTAL_FACING, direction);
			multiPartGenerator.with(
				terminalCondition,
				Variant.variant()
					.with(VariantProperties.MODEL, resourceLocation)
					.with(VariantProperties.Y_ROT, rotation)
					.with(VariantProperties.UV_LOCK, true)
			);

			STATE_MODELS.forEach((booleanProperty, modelTemplate) -> {
				addChiseledBookshelfSlotModel(block, blockStateModelGenerator, multiPartGenerator, terminalCondition, rotation, booleanProperty, modelTemplate, true);
				addChiseledBookshelfSlotModel(block, blockStateModelGenerator, multiPartGenerator, terminalCondition, rotation, booleanProperty, modelTemplate, false);
			});
		});

		blockStateModelGenerator.blockStateOutput.accept(multiPartGenerator);
		blockStateModelGenerator.delegateItemModel(block, ModelLocationUtils.getModelLocation(block, "_inventory"));
		SLOT_MODEL_CACHE.clear();
	}

	private void addChiseledBookshelfSlotModel(
		Block block,
		BlockModelGenerators blockStateModelGenerator,
		MultiPartGenerator multiPartGenerator,
		Condition.TerminalCondition terminalCondition,
		VariantProperties.Rotation rotation,
		BooleanProperty booleanProperty,
		ModelTemplate modelTemplate,
		boolean bl
	) {
		String string = bl ? "_occupied" : "_empty";
		TextureMapping textureMapping = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block, string));

		ModelCacheKey cacheKey = new ModelCacheKey(modelTemplate, string);
		ResourceLocation resourceLocation = SLOT_MODEL_CACHE.computeIfAbsent(
			cacheKey,
			(k) -> modelTemplate.createWithSuffix(block, string, textureMapping, blockStateModelGenerator.modelOutput)
		);

		multiPartGenerator.with(
			Condition.and(
				terminalCondition,
				Condition.condition().term(booleanProperty, bl)
			),
			Variant.variant()
				.with(VariantProperties.MODEL, resourceLocation)
				.with(VariantProperties.Y_ROT, rotation)
		);
	}

	record ModelCacheKey(ModelTemplate template, String modelSuffix) {
	}
}

/*

ceaa71,c29d62,9f844d,967441,b8945f,af8f55

ceaa71,c29d62,9f844d,b8945f,af8f55,967441


                     444444 555555 222222        666666 333333
7d6739,887143,846d40,b8945f,af8f55,c29d62,7e6237,967441,9f844d OAK

7d6739,887143,846d40,b8945f,af8f55,c29d62,7e6237,967441,9f844d

cca66c,b38c51,9f844d,7c623e,bc9862,9b7742

111111        555555 666666 222222        444444 333333

       111111 555555               444444 666666 333333 222222
ceaa70,cca76d,b8945f,af8f55,c29d62,7e6237,967441,9f844d,b38c51 OAK
9f733b,9b703b,82613a,7a5a34,886539,5a4424,614b2e,70522e,755832 SPRUCE
e1d69d,dfd499,d7c185,c8b77a,d7cb8d,a59467,ae9f76,b8a875,cfc27b BIRCH
cb9b78,c99875,b88764,aa7954,bf8e6b,      ,976a44,9f714a,b6815a JUNGLE
ce7a4d,cc774a,ba6337,ad5d32,c26d3f,8f4c2a,99502b,a05630,af6239 ACACIA
6a451d,65431c,4f3218,492f17,53381a,301e0e,3a2411,3e2912,3f2b14 DARK_OAK
964836,914736,773934,6f2a2d,7f4234,      ,5d1c1e,642423,6c392d MANGROVE
efd6cc,eed3c9,e7beb4,e6b5ad,e7c7bb,cd8580,dd9d97,e1a8a1,e0b8a9 CHERRY
ac416c,a74069,863e5a,7e3a56,943f61,4b2737,5c3042,6a344b,813855 CRIMSON
37b6a7,37b1a3,3a8e8c,398382,369d91,1f5752,2e5f51,287067,30897f WARPED


c09d62,ae8e52,3e4453,495065,b99558,a2804b,997c43,252c3d,5b647c,917541,c5a469

19130a,1d170d,271e11,41311a,604a2c

745a36,4c3d26,917142,382b18,5f4a2b,987849

2e1c0a


	spruce_5: #4e351c;
	spruce_4: #2e1f11;
	spruce_3: #120c06;
	spruce_2: #070502;
	spruce_1: #030201;

 */
