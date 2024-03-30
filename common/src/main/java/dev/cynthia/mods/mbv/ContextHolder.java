/*
 * Copyright (c) Cynthia Rey et al., All rights reserved.
 * SPDX-License-Identifier: MPL-2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.cynthia.mods.mbv;

import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.Optional;

/**
 * A thread-aware context holder.
 * While it is unnecessary on its own, there exists contexts where mods implement multithreaded chunks.
 * It is also possible some mods may cause concurrent worlds to coexist and this needs to be handled as well.
 */
public class ContextHolder {
	private static final ThreadLocal<ContextHolder> CURRENT_STRUCTURE = ThreadLocal.withInitial(ContextHolder::new);

	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	private Optional<Holder<Structure>> currentStructure = Optional.empty();

	public Optional<Holder<Structure>> getCurrentStructure() {
		return currentStructure;
	}

	@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
	public void setCurrentStructure(Optional<Holder<Structure>> currentStructure) {
		this.currentStructure = currentStructure;
	}

	public static ContextHolder getContext() {
		return CURRENT_STRUCTURE.get();
	}
}
