package com.comment.model;

import connection.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommentJDBCDAO {

    public List<CommentResult> getCommentByPostId(Integer postId) throws SQLException {
        String sql = " SELECT c.*,m.member_id,m.name,pic.picture_id,pic.preview_url,pic.url" +
                     " FROM comment c JOIN members m ON(m.member_id = c.member_id) " +
                     " JOIN pet p ON(p.member_id = m.member_id) " +
                     " JOIN picture pic ON(p.picture_id = pic.picture_id)" +
                     " WHERE c.post_id = ? AND c.status = 0 AND c.target_id = ? ";
        Connection con = JDBCConnection.getRDSConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        List<CommentResult> cmrs = new ArrayList<>();
        stmt.setInt(1,postId);
        stmt.setInt(2,0);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            cmrs.add(buildCommentResult(rs));
        }
        rs.close();
        stmt.close();
        con.close();
        return cmrs;
    }
    public List<CommentResult> getReplyByCommentId(Integer commentId) throws SQLException {
        String sql = " SELECT c.*,m.member_id,m.name,pic.picture_id,pic.preview_url,pic.url" +
                " FROM comment c JOIN members m ON(m.member_id = c.member_id) " +
                " JOIN pet p ON(p.member_id = m.member_id) " +
                " JOIN picture pic ON(p.picture_id = pic.picture_id)" +
                " WHERE c.status = 0 AND c.target_id = ? ";
        Connection con = JDBCConnection.getRDSConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        List<CommentResult> cmrs = new ArrayList<>();
        stmt.setInt(1,commentId);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            cmrs.add(buildCommentResult(rs));
        }
        rs.close();
        stmt.close();
        con.close();
        return cmrs;
    }

    public List<CommentResult> getRecentComment(Integer memberId) throws SQLException {
        String sql = " SELECT m.name,c.*,pic.picture_id,pic.preview_url,pic.url " +
                " FROM comment c " +
                " JOIN post po ON (po.post_id = c.post_id) " +
                " JOIN members m ON(m.member_id = c.member_id) " +
                " JOIN pet p ON(m.member_id = p.member_id)" +
                " JOIN picture pic ON(pic.picture_id = p.picture_id)" +
                " WHERE c.post_id IN (SELECT post_id FROM post WHERE member_id = ?) " +
                "   AND c.target_id = 0 " +
                " ORDER BY c.comment_time DESC " +
                " LIMIT 8 ";
        Connection con = JDBCConnection.getRDSConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        List<CommentResult> cmrs = new ArrayList<>();
        stmt.setInt(1,memberId);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            cmrs.add(buildCommentResult(rs));
        }
        rs.close();
        stmt.close();
        con.close();
        return cmrs;
    }


    public CommentResult buildCommentResult(ResultSet rs) throws SQLException {
        CommentResult cmr = new CommentResult();
        cmr.setCommentId(rs.getInt("comment_id"));
        cmr.setPostId(rs.getInt("c.post_id"));
        cmr.setMemberId(rs.getInt("c.member_id"));
        cmr.setContent(rs.getString("c.content"));
        cmr.setCommentTime(rs.getTimestamp("c.comment_time"));
        cmr.setTargetId(rs.getInt("c.target_id"));
        cmr.setName(rs.getString("m.name"));
        cmr.setPreviewUrl(rs.getString("pic.preview_url"));
        cmr.setUrl(rs.getString("pic.url"));
        return cmr;
    }
}
