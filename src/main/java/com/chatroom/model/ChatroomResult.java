package com.chatroom.model;

import com.members.model.MembersVO;

import java.sql.Timestamp;
import java.util.List;

public class ChatroomResult extends ChatroomVO{
    private String name;
    private Integer memberId;
    private String previewUrl;
    private Integer pictureId;

    private List<ChatroomMemberResult> crms;
    public ChatroomResult() {
        super();
    }

    public ChatroomResult(String name, Integer memberId, String previewUrl, Integer pictureId) {
        this.name = name;
        this.memberId = memberId;
        this.previewUrl = previewUrl;
        this.pictureId = pictureId;
    }

    public ChatroomResult(Integer chatroomId, String chatroomName, Integer chatroomType, Timestamp createTime, String name, Integer memberId, String previewUrl, Integer pictureId) {
        super(chatroomId, chatroomName, chatroomType, createTime);
        this.name = name;
        this.memberId = memberId;
        this.previewUrl = previewUrl;
        this.pictureId = pictureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }


    public List<ChatroomMemberResult> getCrms() {
        return crms;
    }

    public void setCrms(List<ChatroomMemberResult> crms) {
        this.crms = crms;
    }
}
