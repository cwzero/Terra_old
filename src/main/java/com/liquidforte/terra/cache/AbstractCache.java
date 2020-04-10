package com.liquidforte.terra.cache;

import com.liquidforte.terra.client.Client;
import com.liquidforte.terra.database.Database;

public abstract class AbstractCache<D extends Database> implements Cache<D> {
	private Client client;
	private D database;

	public AbstractCache(Client client, D database) {
		this.client = client;
		this.database = database;
	}
	
	public Client getClient() {
		return client;
	}

	@Override
	public D getDatabase() {
		return database;
	}
}
