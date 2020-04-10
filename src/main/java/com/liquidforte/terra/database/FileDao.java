package com.liquidforte.terra.database;

import java.util.List;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import com.liquidforte.terra.model.lock.FileLock;

public interface FileDao {
	@SqlUpdate("CREATE TABLE IF NOT EXISTS FILE(FILE_ID BIGINT PRIMARY KEY, ADDON_ID BIGINT, FILE_DATE DATETIME, FILE_NAME VARCHAR, DOWNLOAD_URL VARCHAR, FINGERPRINT BIGINT)")
	void createTable();

	@SqlUpdate("MERGE INTO FILE(FILE_ID, ADDON_ID, FILE_NAME, FILE_DATE, DOWNLOAD_URL, FINGERPRINT) VALUES (:fileId, :addonId, :fileName, :fileDate, :downloadUrl, :fingerprint)")
	void insertFile(@BindBean FileLock file);

	@SqlQuery("SELECT * FROM FILE WHERE FILE_ID = :fileId AND ADDON_ID = :addonId")
	@RegisterBeanMapper(FileLock.class)
	FileLock getFile(@Bind("addonId") long addonId, @Bind("fileId") long fileId);

	@SqlQuery("SELECT * FROM FILE")
	@RegisterBeanMapper(FileLock.class)
	List<FileLock> getAddonFiles(long addonId);
}
