package com.liquidforte.terra.twitch;

import com.liquidforte.terra.model.lock.FileDependency;
import com.liquidforte.terra.model.lock.FileLock;
import com.liquidforte.terra.model.lock.ModLock;

public class TwitchMapper {
	public ModLock mapAddon(TwitchAddon addon) {
		ModLock lock = new ModLock();
		lock.setAddonId(addon.getAddonId());
		lock.setSlug(addon.getSlug());
		return lock;
	}

	public FileLock mapFile(TwitchFile file) {
		FileLock result = new FileLock();

		result.setAddonId(file.getAddonId());
		result.setDownloadUrl(file.getDownloadUrl());
		result.setFileDate(file.getFileDate());
		result.setFileId(file.getFileId());
		result.setFileName(file.getFileName());
		result.setFingerprint(file.getFingerprint());

		for (TwitchFileDependency dep : file.getDependencies()) {
			FileDependency d = mapDependency(file.getAddonId(), file.getFileId(), dep);
			if (d != null) {
				result.getDependencies().add(d);
			}
		}

		return result;
	}

	public FileDependency mapDependency(long sourceAddonId, long sourceFileId, TwitchFileDependency dependency) {
		if (dependency.getType() != 3) {
			return null;
		}

		FileDependency dep = new FileDependency();

		dep.setSourceAddonId(sourceAddonId);
		dep.setAddonId(dependency.getAddonId());

		return dep;
	}
}
