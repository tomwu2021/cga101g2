package com.post.model;

import java.sql.Date;

public class PostVO implements java.io.Serializable{
	private Integer postId;
	private Integer memberId;	
	private String content;	
	private Integer likeCount;
	private Integer status;	
	private Integer authority;
	private Date createTime;	
	private Date updateTime;
	
	
	public PostVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostVO(Integer postId, Integer memberId, String content, Integer likeCount, Integer status,
			Integer authority, Date createTime, Date updateTime) {
		super();
		this.postId = postId;
		this.memberId = memberId;
		this.content = content;
		this.likeCount = likeCount;
		this.status = status;
		this.authority = authority;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}


