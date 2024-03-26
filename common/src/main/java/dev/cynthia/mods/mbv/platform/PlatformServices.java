/*
 * Copyright (c) Cynthia Rey et al., All rights reserved.
 * SPDX-License-Identifier: MPL-2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package dev.cynthia.mods.mbv.platform;

import dev.cynthia.mods.mbv.Constants;
import dev.cynthia.mods.mbv.platform.services.PlatformHelper;

import java.util.ServiceLoader;

public class PlatformServices {
	public static final PlatformHelper PLATFORM = load(PlatformHelper.class);

	public static <T> T load(Class<T> clazz) {
		final T loadedService = ServiceLoader.load(clazz)
			.findFirst()
			.orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));

		Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
		return loadedService;
	}
}
