package com.chatroom.model;

public class ChatroomMemberResult {

    private Integer memberId;
    private String name;
    private String previewUrl;

    public ChatroomMemberResult(Integer memberId, String name, String previewUrl) {
        super();
        this.memberId = memberId;
        this.name = name;
        this.previewUrl = previewUrl;
    }

    public ChatroomMemberResult() {
        super();
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

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @Override
    public String toString() {
        return "ChatroomMemberResult{" +
                "memberId=" + memberId +
                ", name='" + name + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                '}';
    }
}
