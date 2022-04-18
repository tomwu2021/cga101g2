package com.picture.model;

import java.sql.Timestamp;

public class PictureVO {
	private Integer pictureId;
	private String url;
	private Timestamp createTime;
	private String fileKey;
	private String fileName;
	private Long size;
	private String previewUrl;
	private String previewKey;

	public Integer getPictureId() {
		return pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getPreviewUrl() {
		return previewUrl;
	}

	public void setPreviewUrl(String previewUrl) {
		this.previewUrl = previewUrl;
	}

	public String getPreviewKey() {
		return previewKey;
	}

	public void setPreviewKey(String previewKey) {
		this.previewKey = previewKey;
	}

	@Override
	public String toString() {
		return "PictureVO{" +
				"pictureId=" + pictureId +
				", url='" + url + '\'' +
				", createTime=" + createTime +
				", fileKey='" + fileKey + '\'' +
				", fileName='" + fileName + '\'' +
				", size=" + size +
				", previewUrl='" + previewUrl + '\'' +
				", previewKey='" + previewKey + '\'' +
				'}';
	}
}
