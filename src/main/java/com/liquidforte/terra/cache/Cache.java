package com.liquidforte.terra.cache;

import com.liquidforte.terra.client.Client;
import com.liquidforte.terra.database.Database;

public interface Cache<D extends Database> {
	Client getClient();
	
	D getDatabase();
}
