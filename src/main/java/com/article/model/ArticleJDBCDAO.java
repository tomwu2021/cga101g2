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

	private static final String INSERT = "INSERT INTO article(type, title, content, emp_no) VALUES(?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM article WHERE article_id = ?";
	private static final String UPDATE = "UPDATE article SET type = ?, title = ?, content = ?, emp_no = ? WHERE article_id = ?";
	private static final String GET_ONE = "SELECT article_id, type, title, content, create_time, emp_no FROM article WHERE article_id = ?";
	private static final String GET_ALL = "SELECT article_id, type, title, content, create_time, emp_no FROM article ORDER BY article_id";
	
	
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
	
	public ArticleVO insert(ArticleVO articleVO, Connection con) {
	
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setInt(index++, articleVO.getType());
				pstmt.setString(index++, articleVO.getTitle());
				pstmt.setString(index++, articleVO.getContent());
				pstmt.setInt(index++, articleVO.getEmpNo());
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
	
	public boolean delete(ArticleVO articleVO, Connection con) {
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				int index = 1;
				pstmt.setInt(index, articleVO.getArticleId());
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
	
	public ArticleVO update(ArticleVO articleVO, Connection con) {
		
		if (con != null) {
			try {
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setInt(index++, articleVO.getType());
				pstmt.setString(index++, articleVO.getTitle());
				pstmt.setString(index++, articleVO.getContent());
				pstmt.setInt(index++, articleVO.getEmpNo());
				pstmt.setInt(index++, articleVO.getArticleId());
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

	
	@Override
	public ArticleVO getOneById(Integer id) {
		
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
		
		
	@Override
	public List<ArticleVO> getAll() {
		
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
	

}
