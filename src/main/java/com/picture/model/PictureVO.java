package com.picture.model;

import java.sql.Timestamp;

public class PictureVO {
	private Integer pictureId;
	private String pUrl;
	private Timestamp createTime;
	private String fileKey;
	private String fileName;
	private Long size;

	public Integer getPictureId() {
		return pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public String getpUrl() {
		return pUrl;
	}

	public void setpUrl(String pUrl) {
		this.pUrl = pUrl;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "PictureVO:{pictureId:" + pictureId + ", pUrl:" + pUrl + ", createTime:" + createTime + ", fileKey:"
				+ fileKey + ", fileName:" + fileName + ", size:" + size + "}";
	}




}
