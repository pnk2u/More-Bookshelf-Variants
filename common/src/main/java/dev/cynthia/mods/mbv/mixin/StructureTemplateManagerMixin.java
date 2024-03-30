/*
 * Copyright (c) Cynthia Rey et al., All rights reserved.
 * SPDX-License-Identifier: MPL-2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.cynthia.mods.mbv.mixin;

import dev.cynthia.mods.mbv.Constants;
import dev.cynthia.mods.mbv.ContextHolder;
import dev.cynthia.mods.mbv.tags.StructureTags;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

@Mixin(StructureTemplateManager.class)
public class StructureTemplateManagerMixin {
	@Inject(method = "loadFromResource", at = @At("TAIL"))
	public void patchBookshelvesToVariant(ResourceLocation loc, CallbackInfoReturnable<Optional<StructureTemplate>> cir) {
		Optional<StructureTemplate> maybeStructure = cir.getReturnValue();
		if (maybeStructure.isEmpty()) return; // Load failed??

		Optional<Holder<Structure>> structureOptional = ContextHolder.getContext().getCurrentStructure();
		if (structureOptional.isEmpty()) return; // No data available

		Block bookshelf = null;
		Holder.Reference<Structure> structure = (Holder.Reference<Structure>) structureOptional.get();
		for (Map.Entry<TagKey<Structure>, Block> entry : StructureTags.TAG_BOOKSHELF_MAP.entrySet()) {
			TagKey<Structure> tag = entry.getKey();

			if (structure.is(tag)) {
				bookshelf = entry.getValue();
				break;
			}
		}

		if (bookshelf == null) return; // No patch to apply

		// Patch!
		Constants.LOG.info("Patching template {} in {}: replacing bookshelves with {}", loc, structure.key(), BuiltInRegistries.BLOCK.getKey(bookshelf));
		List<StructureTemplate.Palette> palettes = ((StructureTemplateAccessor) maybeStructure.get()).getPalettes();

		for (StructureTemplate.Palette palette : palettes) {
			for (ListIterator<StructureTemplate.StructureBlockInfo> it = palette.blocks().listIterator(); it.hasNext(); ) {
				StructureTemplate.StructureBlockInfo blockInfo = it.next();
				if (blockInfo.state().is(Blocks.BOOKSHELF)) {
					it.set(
						new StructureTemplate.StructureBlockInfo(
							blockInfo.pos(),
							bookshelf.defaultBlockState(),
							blockInfo.nbt()
						)
					);
				}
			}
		}
	}

	@Mixin(Structure.class)
	static class StructureMixin {
		@Inject(method = "generate", at = @At("HEAD"))
		public void generateStart(RegistryAccess $$0, ChunkGenerator $$1, BiomeSource $$2, RandomState $$3, StructureTemplateManager $$4, long $$5, ChunkPos $$6, int $$7, LevelHeightAccessor $$8, Predicate<Holder<Biome>> $$9, CallbackInfoReturnable<StructureStart> cir) {
			Structure structure = (Structure) (Object) this;
			ContextHolder.getContext().setCurrentStructure(Optional.of($$0.registryOrThrow(Registries.STRUCTURE).wrapAsHolder(structure)));
		}

		@Inject(method = "generate", at = @At("RETURN"))
		public void generateEnd(RegistryAccess $$0, ChunkGenerator $$1, BiomeSource $$2, RandomState $$3, StructureTemplateManager $$4, long $$5, ChunkPos $$6, int $$7, LevelHeightAccessor $$8, Predicate<Holder<Biome>> $$9, CallbackInfoReturnable<StructureStart> cir) {
			ContextHolder.getContext().setCurrentStructure(Optional.empty());
		}
	}
}
