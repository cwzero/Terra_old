package com.liquidforte.terra.cache;

import com.google.inject.Inject;
import com.liquidforte.terra.client.TerraClient;
import com.liquidforte.terra.database.CacheDatabase;
import com.liquidforte.terra.model.lock.ModLock;

public class ModCache extends AbstractCache<CacheDatabase> {
	@Inject
	public ModCache(TerraClient client, CacheDatabase database) {
		super(client, database);
	}

	public long getAddonId(String slug) {
		long id = getDatabase().getAddonId(slug);

		if (id <= 0) {
			ModLock mod = getClient().getMod(slug);
			id = mod.getAddonId();
			getDatabase().insertMod(mod);
		}

		return id;
	}
}
