package com.comment.model;

public class CommentResult extends CommentVO{

    private String name;
    private String previewUrl;
    private String url;
    private Integer postMemberId;
    private Integer pictureId;

    public CommentResult() {
        super();
    }



    public CommentResult(String name, String previewUrl, String url, Integer postMemberId, Integer pictureId) {
		super();
		this.name = name;
		this.previewUrl = previewUrl;
		this.url = url;
		this.postMemberId = postMemberId;
		this.pictureId = pictureId;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public Integer getPostMemberId() {
		return postMemberId;
	}

	public void setPostMemberId(Integer postMemberId) {
		this.postMemberId = postMemberId;
	}

	
	
	@Override
    public String toString() {
        return "CommentResult{" +
                ", name='" + name + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
