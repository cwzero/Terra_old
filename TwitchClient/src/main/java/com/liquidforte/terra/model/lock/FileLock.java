package com.liquidforte.terra.model.lock;

import java.util.ArrayList;
import java.util.List;

import com.liquidforte.terra.model.FileBase;
import com.liquidforte.terra.model.lock.FileDependency;

public class FileLock extends FileBase {
	private List<FileDependency> dependencies = new ArrayList<FileDependency>();

	public List<FileDependency> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<FileDependency> dependencies) {
		this.dependencies = dependencies;
	}
}
