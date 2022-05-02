package com.picture.model;

public class PictureResult extends PictureVO {
	private Integer isCover;

	public PictureResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PictureResult(Integer isCover) {
		super();
		this.isCover = isCover;
	}

	public Integer getIsCover() {
		return isCover;
	}

	public void setIsCover(Integer isCover) {
		this.isCover = isCover;
	}

}
