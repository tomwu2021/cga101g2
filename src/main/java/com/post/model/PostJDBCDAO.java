package com.post.model;
import com.picture.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.model.PublicAccessBlockConfiguration;
import com.picture.model.PictureVO;
import com.post_pic.model.Post_PicVO;

import connection.JDBCConnection;

public class PostJDBCDAO implements PostDAO_interface {
	
	Connection con;
	
	@Override
	public PostVO insert(PostVO postVO) {
		PostVO postVO2 =null;
		try {
		con = JDBCConnection.getRDSConnection();
		postVO2 = insert(postVO, con);
		
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "+ e.getMessage());
		} 
		return postVO2;
	}
	
	public PostVO insert(PostVO postVO, Connection con) throws SQLException {
		final String INSERT ="insert into post (member_id, content, like_count, status, authority) values (?, ?, ?, ?, ? ) ";
		
		if (con != null) {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, postVO.getMemberId());
				pstmt.setString(2, postVO.getContent());
				pstmt.setInt(3, 0);
				pstmt.setInt(4, 0);
				pstmt.setInt(5, 0);
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					postVO.setPostId(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
		}
				return postVO;
			
	}

	@Override
	public boolean delete(PostVO t) {
		// TODO Auto-generated method stub
		return false;
	}

	
	//修改貼文內容
	@Override
	public PostVO update(PostVO postVO) {
		con = JDBCConnection.getRDSConnection();
		PostVO postVO2 = update(postVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} 
		return postVO2;
	}
	
	public PostVO update(PostVO postVO, Connection con) {
		
		final String UPDATE= "update post set content = ? WHERE (post_id = ?)";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE);

				pstmt.setString(1, postVO.getContent());
				pstmt.setInt(2, postVO.getPostId());
				
				pstmt.executeUpdate();
				
			}catch (SQLException e) {
				throw new RuntimeException("A database error occured. " + e.getMessage());
			} 
		}
		return null;
	}
	
	
	@Override
	public PostVO getOneById(Integer id) {
		
		final String GETONE ="select post_id, member_id, content, like_count, status, authority, create_time, update_time from post where post_id = ? ";
		
		try(Connection con =JDBCConnection.getRDSConnection();
			PreparedStatement pstmt = con.prepareStatement(GETONE);){
			
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				PostVO postVO = new PostVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setMemberId(rs.getInt("member_id"));
				postVO.setContent(rs.getString("content"));
				postVO.setLikeCount(rs.getInt("like_count"));
				postVO.setStatus(rs.getInt("status"));
				postVO.setAuthority(rs.getInt("authority"));
				postVO.setCreateTime(rs.getDate("create_time"));
				postVO.setUpdateTime(rs.getDate("update_time"));	
				return postVO;
			}
				
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. " + e.getMessage());
			} 
			return null;
	}

	@Override
	public List<PostVO> getAll() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO =null;
		final String GETAll = "select post_id, member_id, content, like_count, status, authority, create_time, update_time from post";

		
		try(Connection con =JDBCConnection.getRDSConnection();
			PreparedStatement pstmt = con.prepareStatement(GETAll);
			ResultSet rs = pstmt.executeQuery();){
			
			while(rs.next()) {
				postVO = new PostVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setMemberId(rs.getInt("member_id"));
				postVO.setContent(rs.getString("content"));
				postVO.setLikeCount(rs.getInt("like_count"));
				postVO.setStatus(rs.getInt("status"));
				postVO.setAuthority(rs.getInt("authority"));
				postVO.setCreateTime(rs.getDate("create_time"));
				postVO.setUpdateTime(rs.getDate("update_time"));
				
				list.add(postVO);	
			}	
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} 
		return list;
	}
	
	
	//查詢個人頁面
	@Override
	public List<PostVO> selectPost(Integer memberid) {
		final String SELECT_POST = "select * from post where member_id =? order by create_time desc ";
		
		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(SELECT_POST);) {

			pstmt.setInt(1, memberid);
			ResultSet rs = pstmt.executeQuery();
			
			List< PostVO> postList = new ArrayList<PostVO>();
			
			while (rs.next()) {
				PostVO postVO = new PostVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setMemberId(rs.getInt("member_id"));
				postVO.setContent(rs.getString("content"));
				postVO.setLikeCount(rs.getInt("like_count"));
				postVO.setStatus(rs.getInt("status"));
				postVO.setAuthority(rs.getInt("authority"));
				postVO.setCreateTime(rs.getDate("create_time"));
				postVO.setUpdateTime(rs.getDate("update_time"));
				postList.add(postVO);
			}
			
			return postList;
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
	}
	
	
	//查詢貼文，顯示 status狀態0:正常1:審核中2:刪除
	@Override
	public List<PostVO> selectChangePost(Integer memberid) {
		final String SELECT_CHANGEPOST = "select p.post_id, member_id, content, like_count, create_time, url "
				+ "from post p join post_pic pc on p.post_id = pc.post_id "
				+ "			   join picture pi on pc.picture_id = pi.picture_id "
				+ "			   where status = 0 AND member_id = ? "
				+ "order by create_time desc";
				
		
		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(SELECT_CHANGEPOST);) {
			pstmt.setInt(1, memberid);
			ResultSet rs = pstmt.executeQuery();
			
			List< PostVO> poList = new ArrayList<PostVO>();
			
			while (rs.next()) {
				PostVO postVO = new PostVO();
				PictureVO pictureVO = new PictureVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setMemberId(rs.getInt("member_id"));
				postVO.setContent(rs.getString("content"));
				postVO.setLikeCount(rs.getInt("like_count"));
				postVO.setCreateTime(rs.getDate("create_time"));
				
				pictureVO.setUrl(rs.getString("url"));  //這個pictureVO放url
				
				postVO.setPictureVO(pictureVO);
				
				poList.add(postVO);
				
			}
			return poList;      //顯示status狀態正常(0)含圖的貼文
			
			
			
			
		}catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
		
		
	}
	
	//查詢熱門貼文
	@Override
	public List<PostVO> selectHotPost() {
		final String SELECT_HOTPOST = "select * from post "
				+ "where DateDiff(curdate(), create_time) <= 7 "
				+ "order by like_count desc "
				+ "limit 0,3";
		
		List< PostVO> hotpostlist = new ArrayList<PostVO>();
		
		try (Connection con = JDBCConnection.getRDSConnection();
			 PreparedStatement pstmt = con.prepareStatement(SELECT_HOTPOST);
			 ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {
				PostVO postVO = new PostVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setContent(rs.getString("content"));
				postVO.setLikeCount(rs.getInt("like_count"));
				postVO.setStatus(rs.getInt("status"));
				postVO.setAuthority(rs.getInt("authority"));
				postVO.setCreateTime(rs.getDate("create_time"));
				postVO.setUpdateTime(rs.getDate("update_time"));

				hotpostlist.add(postVO);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} 
		return hotpostlist;
	
	}
	
	
}
