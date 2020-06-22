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

import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.LogMarkers;
import net.minecraftforge.fml.loading.moddiscovery.CoreModFile;
import net.minecraftforge.fml.loading.moddiscovery.ModFile;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.language.IModInfo;
import net.minecraftforge.forgespi.language.IModLanguageProvider;
import net.minecraftforge.forgespi.locating.IModLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class SpongeModFile extends ModFile {

	private static final Logger log = LogManager.getLogger();

	private IModFileInfo modFileInfo;
	private IModLanguageProvider loader;

	public SpongeModFile(final Path path, final IModLocator locator) {
		super(path, locator);
	}

	@Override
	public Supplier<Map<String, Object>> getSubstitutionMap() {
		return Collections::emptyMap;
	}

	@Override
	public Type getType() {
		return Type.MOD;
	}

	@Override
	public void identifyLanguage() {
		this.loader = FMLLoader.getLanguageLoadingProvider().findLanguage(this, this.modFileInfo.getModLoader(), this.modFileInfo.getModLoaderVersion());
	}

	@Override
	public IModLanguageProvider getLoader() {
		return this.loader;
	}

	@Override
	public List<IModInfo> getModInfos() {
		return this.modFileInfo.getMods();
	}

	@Override
	public boolean identifyMods() {
		//this.modFileInfo = SpongeModFileParser.readModList(this); // todo
		if (this.modFileInfo == null) return false;

		log.debug(LogMarkers.LOADING, "Loading mod file {} with language {}", this.getFilePath(), this.modFileInfo.getModLoader());
		return true;
	}

	@Override
	public IModFileInfo getModFileInfo() {
		return this.modFileInfo;
	}

	@Override
	public List<CoreModFile> getCoreMods() {
		return Collections.emptyList();
	}

	@Override
	public Optional<Path> getAccessTransformer() {
		return Optional.empty();
	}

	@Override
	public String toString() {
		return "SpongeModFile{" +
				"path=" + this.getFilePath() +
				'}';
	}

}
