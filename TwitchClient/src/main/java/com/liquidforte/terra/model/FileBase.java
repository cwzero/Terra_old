package com.liquidforte.terra.model;

import java.util.Date;

import com.google.common.base.Strings;

public abstract class FileBase implements Comparable<FileBase> {
	private long addonId = -1;
	private long fileId = -1;
	private String fileName = "";
	private Date fileDate;
	private String downloadUrl = "";
	private long fingerprint = -1;

	public long getAddonId() {
		return addonId;
	}

	public void setAddonId(long addonId) {
		this.addonId = addonId;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getFileDate() {
		return fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public long getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(long fingerprint) {
		this.fingerprint = fingerprint;
	}

	public boolean isValid() {
		return !Strings.isNullOrEmpty(fileName) && !Strings.isNullOrEmpty(downloadUrl) && fingerprint > 0;
	}

	@Override
	public int compareTo(FileBase o) {
		return o.getFileDate().compareTo(getFileDate());
	}
}
