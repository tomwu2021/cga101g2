package com.article.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import connection.JDBCConnection;

public class ArticleJDBCDAO  implements ArticleDAO_interface{
	Connection con;
	
//	① create 發布最新消息(後)------------------------	
	@Override
	public ArticleVO insert(ArticleVO articleVO) {
		con = JDBCConnection.getRDSConnection();
		ArticleVO articleVO2 = insert(articleVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleVO2;
	}
	/** @see #insert */
	public ArticleVO insert(ArticleVO articleVO, Connection con) {
		final String INSERT = "INSERT INTO article(type, title, content, emp_no) VALUES(?, ?, ?, ?)";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				if(articleVO.getType() != null) pstmt.setInt(index++, articleVO.getType());
				if(articleVO.getTitle() != null && !articleVO.getTitle().trim().isEmpty()) pstmt.setString(index++, articleVO.getTitle().trim());
				if(articleVO.getContent() != null && !articleVO.getContent().trim().isEmpty()) pstmt.setString(index++, articleVO.getContent().trim());
				if(articleVO.getEmpNo() != null) pstmt.setInt(index++, articleVO.getEmpNo());
				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					articleVO.setArticleId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return articleVO;
	}

//	② delete 下架最新消息(後)------------------------
	@Override
	public boolean delete(ArticleVO articleVO) {
		con = JDBCConnection.getRDSConnection();
		boolean boo = delete(articleVO, con);
		
		try {
			con.close();
			return boo;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/** @see #delete */
	public boolean delete(ArticleVO articleVO, Connection con) {
		final String DELETE = "DELETE FROM article WHERE article_id = ?";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				pstmt.setInt(1, articleVO.getArticleId());
				pstmt.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}	
		} else {
			return false;
		}
	}
	
	
//	③ update 修改最新消息(後)------------------------	
	@Override
	public ArticleVO update(ArticleVO articleVO) {
		con = JDBCConnection.getRDSConnection();
		ArticleVO articleVO2 = update(articleVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleVO2;
	}
	/** @see #update */
	public ArticleVO update(ArticleVO articleVO, Connection con) {
		final StringBuffer UPDATE = new StringBuffer("UPDATE article SET ");
		final Integer type = articleVO.getType();
			if (type != null) UPDATE.append("type = ?,");
		final String title = articleVO.getTitle().trim();
			if (title != null && !title.isEmpty()) UPDATE.append("title = ?,");
		final String content = articleVO.getContent().trim();
			if (content != null && !content.isEmpty()) UPDATE.append("content = ?,");
		UPDATE.append("emp_no = ? WHERE article_id = ?");
		if (con != null) {
			int offset = 1;
			try {				
				PreparedStatement pstmt = con.prepareStatement(UPDATE.toString());
				if (type != null) pstmt.setInt(offset++, type);
				if (title != null && !title.trim().isEmpty()) pstmt.setString(offset++, title);
				if (content != null && !content.trim().isEmpty()) pstmt.setString(offset++, content);
				pstmt.setInt(offset++, articleVO.getEmpNo());
				pstmt.setInt(offset++, articleVO.getArticleId());
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return articleVO;		
	}

//	④ read 查詢一則最新消息------------------------
	@Override
	public ArticleVO getOneById(Integer id) {
		final String GET_ONE = "SELECT article_id, type, title, content, create_time, emp_no "
							 + "FROM article WHERE article_id = ?";
		con = JDBCConnection.getRDSConnection();
		ArticleVO articleVO =new ArticleVO();
			
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ONE);
				int index = 1;
				pstmt.setInt(index, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					articleVO.setArticleId(rs.getInt(index++));
					articleVO.setType(rs.getInt(index++));
					articleVO.setTitle(rs.getString(index++));
					articleVO.setContent(rs.getString(index++));
					articleVO.setCreateTime(rs.getTimestamp(index++));
					articleVO.setEmpNo(rs.getInt(index++));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleVO;		
	}
		
//	⑤ read 查詢所有最新消息------------------------			
	@Override
	public List<ArticleVO> getAll() {
		final String GET_ALL = "SELECT article_id, type, title, content, create_time, emp_no FROM article ORDER BY article_id DESC";
		con = JDBCConnection.getRDSConnection();
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ALL);
				int index = 1;
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ArticleVO articleVO =new ArticleVO();
					articleVO.setArticleId(rs.getInt(index++));
					articleVO.setType(rs.getInt(index++));
					articleVO.setTitle(rs.getString(index++));
					articleVO.setContent(rs.getString(index++));
					articleVO.setCreateTime(rs.getTimestamp(index++));
					articleVO.setEmpNo(rs.getInt(index++));
					list.add(articleVO);
					index = 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
//	⑥ read 查詢一類最新消息(後)------------------------	
	@Override
	public List<ArticleVO> getAllByType(Integer type) {
		final String GET_TYPE = "SELECT article_id, type, title, content, create_time, emp_no FROM article WHERE type = ? ORDER BY article_id DESC";
		con = JDBCConnection.getRDSConnection();
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_TYPE);
				int index = 1;
				pstmt.setInt(index, type);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					ArticleVO articleVO =new ArticleVO();
					articleVO.setArticleId(rs.getInt(index++));
					articleVO.setType(rs.getInt(index++));
					articleVO.setTitle(rs.getString(index++));
					articleVO.setContent(rs.getString(index++));
					articleVO.setCreateTime(rs.getTimestamp(index++));
					articleVO.setEmpNo(rs.getInt(index++));
					list.add(articleVO);
					index = 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
