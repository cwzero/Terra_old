package com.liquidforte.terra.command;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.liquidforte.terra.cache.IFileCache;
import com.liquidforte.terra.cache.ILockCache;
import com.liquidforte.terra.cache.ModCache;
import com.liquidforte.terra.config.AppConfig;
import com.liquidforte.terra.config.AppOptions;
import com.liquidforte.terra.loader.GroupLoader;
import com.liquidforte.terra.manifest.TwitchManifest;
import com.liquidforte.terra.manifest.TwitchManifestFile;
import com.liquidforte.terra.manifest.TwitchMinecraft;
import com.liquidforte.terra.mode.Mode;
import com.liquidforte.terra.model.Group;
import com.liquidforte.terra.model.ModSpec;

public class BuildCommand extends CommandBase {
	private ObjectMapper mapper;
	private TwitchMinecraft twitchMinecraft;

	@Inject
	public BuildCommand(ObjectMapper mapper, Mode mode, GroupLoader loader, IFileCache fileCache, ModCache modCache,
			ILockCache lockCache, AppOptions options, AppConfig config, TwitchMinecraft twitchMinecraft) {
		super(mode, loader, fileCache, modCache, lockCache, options, config);
		this.mapper = mapper;
		this.twitchMinecraft = twitchMinecraft;
	}

	@Override
	public Object apply(Mode mode) {
		TwitchManifest manifest = new TwitchManifest(getConfig().getPackName(), getConfig().getPackVersion(),
				getConfig().getPackAuthor(), getConfig().getTwitchOverride(), twitchMinecraft);

		for (Group group : loader.loadGroups()) {
			for (ModSpec spec : group.getMods()) {
				long addonId = modCache.getAddonId(spec.getSlug());
				long fileId = lockCache.getLock(spec.getSlug(), spec.getFilter());

				manifest.getFiles().add(new TwitchManifestFile(addonId, fileId));
			}
		}

		String manifestPath = getOptions().getManifestPath(getConfig().getManifestPath());
		File manifestFile = new File(manifestPath);
		
		if (!manifestFile.getParentFile().exists()) {
			manifestFile.getParentFile().mkdirs();
		}

		try {
			mapper.writeValue(manifestFile, manifest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
