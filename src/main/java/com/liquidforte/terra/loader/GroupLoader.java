package com.liquidforte.terra.loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.liquidforte.terra.config.AppOptions;
import com.liquidforte.terra.model.Group;
import com.liquidforte.terra.util.FileUtil;

public class GroupLoader {
	private ObjectMapper mapper;
	private AppOptions options;

	@Inject
	public GroupLoader(ObjectMapper mapper, AppOptions options) {
		this.mapper = mapper;
		this.options = options;
	}

	public List<Group> loadGroups() {
		List<Group> result = new ArrayList<Group>();

		File groupsDir = options.getGroupsDir().toFile();
		groupsDir.mkdirs();
		String[] extensions = { "json" };

		for (File groupFile : FileUtils.listFiles(groupsDir, extensions, true)) {
			try {
				Group group = mapper.readValue(groupFile, Group.class);
				if (Strings.isNullOrEmpty(group.getId())) {
					String id = FileUtil.getRelativePath(groupsDir, groupFile).replace("\\", "/").replace("/", ".");
					group.setId(id);
				}
				result.add(group);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
