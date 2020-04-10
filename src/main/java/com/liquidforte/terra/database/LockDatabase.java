package com.liquidforte.terra.database;

import com.google.inject.Inject;
import com.liquidforte.terra.config.LockConfig;

public class LockDatabase extends AbstractDatabase {
	@Inject
	public LockDatabase(LockConfig lockConfig) {
		super(lockConfig.getUrl(), lockConfig.getUsername(), lockConfig.getPassword());
	}

	public void init() {
		try {
			useJdbiExtension(LockDao.class, dao -> dao.createTable());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long getLock(long addonId, String filter) {
		try {
			return withJdbiExtension(LockDao.class, dao -> dao.getLock(addonId, filter));
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

	public void lock(long addonId, String filter, long fileId) {
		if (addonId == -1 || fileId == -1) {
			throw new RuntimeException("Can't insert -1");
		}
		try {
			useJdbiExtension(LockDao.class, dao -> dao.lock(addonId, filter, fileId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
