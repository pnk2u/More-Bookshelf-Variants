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
			addChiseledBookshelfBlockStates(location, bookshelf, blockStateModelGenerator);
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

	private void addChiseledBookshelfBlockStates(ResourceLocation loc, Block block, BlockModelGenerators blockStateModelGenerator) {
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
