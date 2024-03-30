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
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

// Module is currently placed in both Forge and Fabric folders since VanillaGradle doesn't support deobfuscation on-fly
// EveryCompat would need to publish a mojmap build the common project could depend on, which it doesn't
// As a "hack" the module is placed in both projects using a symlink to avoid actually duped code

public class EveryCompatModule extends SimpleModule {
	private final static WoodType SPRUCE_TYPE = new WoodType(new ResourceLocation("spruce"), Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_LOG);

	public final SimpleEntrySet<WoodType, Block> bookshelf;
	public final SimpleEntrySet<WoodType, Block> chiseledBookshelf;

	public EveryCompatModule() {
		super(Constants.MOD_ID, "mbv");

		SimpleEntrySet.Builder<WoodType, Block> bookshelfBuilder = SimpleEntrySet.builder(
				WoodType.class,
				"bookshelf",
				() -> Bookshelves.SPRUCE_BOOKSHELF,
				() -> SPRUCE_TYPE,
				(w) -> new Block(BlockBehaviour.Properties.copy(Blocks.BOOKSHELF))
			)
			.copyParentDrop()
			.defaultRecipe()
			.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
			.addTag(BlockTags.ENCHANTMENT_POWER_PROVIDER, Registries.BLOCK)
			.addTexture(new ResourceLocation("minecraft", "block/spruce_planks"))
			.addTextureM(modRes("block/spruce_bookshelf"), modRes("model/bookshelf_m"));

		SimpleEntrySet.Builder<WoodType, Block> chiseledBookshelfBuilder = SimpleEntrySet.builder(
				WoodType.class,
				"chiseled_bookshelf",
				() -> ChiseledBookshelves.SPRUCE_CHISELED_BOOKSHELF,
				() -> SPRUCE_TYPE,
				(w) -> new ChiseledBookShelfBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_BOOKSHELF))
			)
			.copyParentDrop()
			.defaultRecipe()
			.addTile(getModTile("chiseled_bookshelf"))
			.addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
			.addTexture(modRes("block/spruce_chiseled_bookshelf_top"))
			.addTexture(modRes("block/spruce_chiseled_bookshelf_side"))
			.addTextureM(modRes("block/spruce_chiseled_bookshelf_empty"), modRes("model/chiseled_bookshelf_empty_m"))
			.addTextureM(modRes("block/spruce_chiseled_bookshelf_occupied"), modRes("model/chiseled_bookshelf_occupied_m"));

		addBookshelfTag(bookshelfBuilder, "c");
		if (PlatformServices.PLATFORM.getPlatformName().equals("Forge")) {
			addBookshelfTag(bookshelfBuilder, "forge");
		}

		bookshelf = bookshelfBuilder.build();
		chiseledBookshelf = chiseledBookshelfBuilder.build();

		addEntry(bookshelf);
		addEntry(chiseledBookshelf);
	}

	private static void addBookshelfTag(SimpleEntrySet.Builder<?, ?> builder, String namespace) {
		builder.addTag(
			TagKey.create(
				Registries.BLOCK,
				new ResourceLocation(namespace, "bookshelves")
			),
			Registries.BLOCK
		);

		builder.addTag(
			TagKey.create(
				Registries.ITEM,
				new ResourceLocation(namespace, "bookshelves")
			),
			Registries.ITEM
		);
	}
}
