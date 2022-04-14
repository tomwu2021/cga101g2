package com.post_pic.model;

public class Post_PicVO implements java.io.Serializable{
	private Integer pictureId;
	private Integer postId;
	
	public Post_PicVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post_PicVO(Integer pictureId, Integer postId) {
		super();
		this.pictureId = pictureId;
		this.postId = postId;
	}

	public Integer getPictureId() {
		return pictureId;
	}

	public void setPictureId(Integer pictureId) {
		this.pictureId = pictureId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	
}
