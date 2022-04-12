package com.likelist.model;

public class LikelistVO implements java.io.Serializable {
	
	private Integer postId;
	private Integer memberId;
	
	public LikelistVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikelistVO(Integer postId, Integer memberId) {
		super();
		this.postId = postId;
		this.memberId = memberId;
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
	
}
