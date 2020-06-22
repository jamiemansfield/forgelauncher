/*
 * This file is part of forgelauncher, licensed under the MIT License (MIT).
 *
 * Copyright (c) Jamie Mansfield <https://www.jamiemansfield.me/>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.jamiemansfield.sponge.launch.language;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import org.spongepowered.plugin.metadata.PluginMetadata;

import java.util.ArrayList;
import java.util.List;

public final class SpongeModFileParser {

	public static UnmodifiableConfig createConfig(final PluginMetadata metadata) {
		final Config config = Config.inMemory();
		config.set("modLoader", "sponge");
		config.set("loaderVersion", "[31,)");

		final List<UnmodifiableConfig> mods = new ArrayList<>();
		final Config modConfig = Config.inMemory();
		modConfig.set("modId", metadata.getId());
		modConfig.set("displayName", metadata.getName());
		modConfig.set("version", metadata.getVersion());
		modConfig.set("description", metadata.getDescription());
		mods.add(modConfig);

		final Config properties = Config.inMemory();
		properties.set("sponge-metadata", metadata);

		final Config modProperties = Config.inMemory();
		modProperties.set(metadata.getId(), properties);

		config.set("mods", mods);
		config.set("modproperties", modProperties);
		return config;
	}

	private SpongeModFileParser() {
	}

}
