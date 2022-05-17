package com.comment.model;

import java.sql.SQLException;
import java.util.List;

public class CommentService {
    CommentJDBCDAO cdao = new CommentJDBCDAO();


    public List<CommentResult> getCommentByPostId(Integer postId){
        try {
            return cdao.getCommentByPostId(postId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CommentResult> getReplyByCommentId(Integer commentId){
        try {
            return cdao.getReplyByCommentId(commentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CommentResult> getRecentComment(Integer memberId){
        try {
            return cdao.getRecentComment(memberId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
