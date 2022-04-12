package com.album.model;

import java.sql.Timestamp;

public class AlbumVO {



	private Integer albumId;
	private Integer memberId;
	private String name;
	private Integer authority;
	private Timestamp createTime;

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "AlbumVO:[{albumId:" + albumId + ", memberId:" + memberId + ", name:" + name + ", authority:" + authority
				+ ", createTime:" + createTime + "}]";
	}
}
