package com.liquidforte.terra.database;

import com.google.inject.Inject;
import com.liquidforte.terra.config.CacheConfig;
import com.liquidforte.terra.model.lock.FileDependency;
import com.liquidforte.terra.model.lock.FileLock;
import com.liquidforte.terra.model.lock.ModLock;

public class CacheDatabase extends AbstractDatabase {
	@Inject
	public CacheDatabase(CacheConfig cacheConfig) {
		super(cacheConfig.getUrl(), cacheConfig.getUsername(), cacheConfig.getPassword());
	}

	public void init() {
		runJdbiTransaction(jdbi -> {
			jdbi.useExtension(DependencyDao.class, dao -> dao.createTable());
			jdbi.useExtension(ModDao.class, dao -> dao.createTable());
			jdbi.useExtension(FileDao.class, dao -> dao.createTable());
		});
	}

	public long getAddonId(String slug) {
		try {
			return (long) withJdbiExtension(ModDao.class, dao -> dao.getAddonIdBySlug(slug));
		} catch (IllegalStateException e) {
			if (e.getMessage().contains("no results")) {
			} else {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public void insertMod(ModLock mod) {
		try {
			useJdbiExtension(ModDao.class, dao -> dao.insertMod(mod));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FileLock getFile(long addonId, long fileId) {
		try {
			FileLock lock = withJdbiExtension(FileDao.class, dao -> dao.getFile(addonId, fileId));
			
			if (lock == null) {
				return null;
			}

			useJdbiExtension(DependencyDao.class, dao -> {
				dao.getDependencies(lock.getFileId()).forEach(d -> {
					lock.getDependencies().add(d);
				});
			});

			return lock;
		} catch (IllegalStateException e) {
			if (e.getMessage().contains("no results")) {
			} else {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveFile(FileLock file) {
		try {
			useJdbiExtension(FileDao.class, dao -> dao.insertFile(file));
			
			for (FileDependency dep : file.getDependencies()) {
				dep.setSourceAddonId(file.getAddonId());
				useJdbiExtension(DependencyDao.class, dao -> {
					dao.insertDependency(dep);
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
