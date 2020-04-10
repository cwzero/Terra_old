package com.liquidforte.terra.database;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.liquidforte.terra.model.lock.FileDependency;

public interface DependencyDao {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS FILE_DEPENDENCY(SOURCE_ADDON_ID BIGINT, ADDON_ID BIGINT, PRIMARY KEY(SOURCE_ADDON_ID, ADDON_ID))")
	void createTable();

	@SqlUpdate("MERGE INTO FILE_DEPENDENCY(SOURCE_ADDON_ID, ADDON_ID) VALUES (:sourceAddonId, :addonId)")
	void insertDependency(@BindBean FileDependency dependency);

	@SqlQuery("SELECT * FROM FILE_DEPENDENCY WHERE SOURCE_ADDON_ID = ?")
	@RegisterBeanMapper(FileDependency.class)
	List<FileDependency> getDependencies(long sourceAddonId);

	@SqlQuery("SELECT * FROM FILE_DEPENDENCY")
	@RegisterBeanMapper(FileDependency.class)
	List<FileDependency> getDependencies();
}
