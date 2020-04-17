package com.liquidforte.terra.storage;

import com.liquidforte.terra.model.lock.PackLock;

public interface Storage {
	PackLock loadLock();

	void saveLock(PackLock lock);
}
