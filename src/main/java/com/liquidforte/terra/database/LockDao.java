package com.liquidforte.terra.database;

import java.util.List;

import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface LockDao {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS LOCK(ADDON_ID BIGINT, FILTER VARCHAR, FILE_ID BIGINT, PRIMARY KEY (ADDON_ID, FILTER))")
	void createTable();

	@SqlUpdate("MERGE INTO LOCK(ADDON_ID, FILTER, FILE_ID) values (?, ?, ?)")
	void lock(long addonId, String filter, long fileId);

	@SqlQuery("SELECT FILE_ID FROM LOCK WHERE ADDON_ID = ? AND FILTER = ?")
	long getLock(long addonId, String filter);
	
	@SqlQuery("SELECT ADDON_ID FROM LOCK")
	List<Long> getAddons();
}
