package com.comment.model;

import java.sql.Timestamp;
import java.util.List;

public class CommentVO {

    private Integer commentId;
    private Integer postId;
    private Integer memberId;
    private String content;
    private Timestamp commentTime;
    private Integer status;
    private Integer targetId;

    private List<CommentResult> commentResultList;

    public CommentVO() {

    }

    public CommentVO(Integer commentId, Integer postId, Integer memberId, String content, Timestamp commentTime, Integer status, Integer targetId) {
        this.commentId = commentId;
        this.postId = postId;
        this.memberId = memberId;
        this.content = content;
        this.commentTime = commentTime;
        this.status = status;
        this.targetId = targetId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public List<CommentResult> getCommentResultList() {
        return commentResultList;
    }

    public void setCommentResultList(List<CommentResult> commentResultList) {
        this.commentResultList = commentResultList;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "commentId=" + commentId +
                ", postId=" + postId +
                ", memberId=" + memberId +
                ", content='" + content + '\'' +
                ", commentTime=" + commentTime +
                ", status=" + status +
                ", targetId=" + targetId +
                '}';
    }
}
