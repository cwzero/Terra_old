package com.liquidforte.terra.storage;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.liquidforte.terra.model.lock.PackLock;

public class FileStorage implements Storage {
	private ObjectMapper mapper;

	@Inject
	public FileStorage(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public PackLock loadLock() {
		try {
			return mapper.readValue(new File("pack.lock"), PackLock.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new PackLock();
	}

	@Override
	public void saveLock(PackLock lock) {
		try {
			mapper.writeValue(new File("pack.lock"), lock);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
