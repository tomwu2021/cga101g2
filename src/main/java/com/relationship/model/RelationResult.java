package com.relationship.model;

import connection.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class RelationResult {

    private Integer memberId;
    private String name;
    private String account;
    private String rankName;
    private String previewUrl;
    private String url;

    private Integer isFriend;
    private Integer isBlock;
    private Integer isInviting;
    private Integer isInvited;
    private Integer isBlocked;


    public RelationResult() {
        super();
	}

    public Integer getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(Integer isFriend) {
        this.isFriend = isFriend;
    }

    public Integer getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(Integer isBlock) {
        this.isBlock = isBlock;
    }

    public Integer getIsInviting() {
        return isInviting;
    }

    public void setIsInviting(Integer isInviting) {
        this.isInviting = isInviting;
    }

    public Integer getIsInvited() {
        return isInvited;
    }

    public void setIsInvited(Integer isInvited) {
        this.isInvited = isInvited;
    }

    public RelationResult(Integer memberId,Integer targetId) {
        super();
        try {
            Connection con = JDBCConnection.getRDSConnection();
            RelationshipService rServ = new RelationshipService();
            this.isFriend = rServ.isFriend(memberId,targetId,con);
            this.isBlock = rServ.isBlock(memberId,targetId,con);
            this.isBlocked = rServ.isBlocked(memberId,targetId,con);
            this.isInviting =rServ.isInviting(memberId,targetId,con);
            this.isInvited = rServ.isInvited(memberId,targetId,con);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
