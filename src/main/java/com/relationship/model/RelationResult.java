package com.relationship.model;

public class RelationResult {

    private Integer memberId;
    private String name;
    private String account;
    private String rankName;
    private String previewUrl;
    private String url;

    public RelationResult() {
        super();
	}

	public RelationResult(Integer memberId, String name, String account, String rankName, String previewUrl, String url) {
		super();
		this.memberId = memberId;
        this.name = name;
        this.account = account;
        this.rankName = rankName;
        this.previewUrl = previewUrl;
        this.url = url;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getRankName() {
        return rankName;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	@Override
	public String toString() {
		return "RelationResult:{memberId:" + memberId + ", name:" + name + ", account:" + account + ", rankName:" + rankName
				+ ", previewUrl:" + previewUrl + ", url:" + url + "}";
	}

    

}
