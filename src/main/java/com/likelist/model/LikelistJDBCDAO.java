package com.likelist.model;

import static connection.JNDIConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.post.model.PostJDBCDAO;

import connection.JDBCConnection;
import connection.JNDIConnection;

public class LikelistJDBCDAO implements LikelistDAO_interface {

	Connection con;

	@Override
	public LikelistVO insert(LikelistVO likelistVO) {
		con = JDBCConnection.getRDSConnection();
		LikelistVO likelistVO2 = insert(likelistVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likelistVO2;
	}

	public LikelistVO insert(LikelistVO likelistVO, Connection con) {
		final String INSERT = "insert into likelist (post_id , member_id) values (?, ?)";

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, likelistVO.getPostId());
				pstmt.setInt(2, likelistVO.getMemberId());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					likelistVO.setPostId(rs.getInt(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return likelistVO;

	}

	@Override
	public boolean delete(LikelistVO likelistVO) {
		int rowCount = 0;
		final String DELETE = "DELETE FROM cga_02.post WHERE post_id = ? AND member_id =?";

		try (Connection con = JNDIConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(DELETE)) {

			pstmt.setInt(1, likelistVO.getPostId());
			pstmt.setInt(2, likelistVO.getMemberId());

			rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) delete!");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (rowCount == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public LikelistVO update(LikelistVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LikelistVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LikelistVO> getAll() {
		List<LikelistVO> list = new ArrayList<LikelistVO>();

		final String GETAll = "select post_id, member_id from likelist";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETAll);
				ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {
				LikelistVO likelistVO = new LikelistVO();

				likelistVO.setPostId(rs.getInt("post_id"));
				likelistVO.setMemberId(rs.getInt("member_id"));

				list.add(likelistVO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public boolean delete(LikelistVO likelistVO, Integer newLikeCount, Integer postId) {
		int rowCount1 = 0;
		int rowCount2 = 0;
		final String DELETE = "delete from likelist where post_id =? and member_id=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JNDIConnection.getRDSConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, likelistVO.getPostId());
			pstmt.setInt(2, likelistVO.getMemberId());
			rowCount1 = pstmt.executeUpdate();
			System.out.println(rowCount1 + "row(s) LikelistVO delete!");

			// 再同時更新POST表
			PostJDBCDAO dao = new PostJDBCDAO();
			rowCount2 = dao.updateOnePostLikeCount(newLikeCount, postId, con);

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println(rowCount2 + "row(s) Post updateOnePostLikeCount!");
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		if (rowCount1 == 1 && rowCount2 == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean insertAndBoo(LikelistVO likelistVO, Integer newLikeCount, Integer postId) {
		int rowCount1 = 0;
		int rowCount2 = 0;
		final String INSERT = "insert into likelist (post_id , member_id) values (?, ?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JNDIConnection.getRDSConnection();

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, likelistVO.getPostId());
			pstmt.setInt(2, likelistVO.getMemberId());
			rowCount1 = pstmt.executeUpdate();
			System.out.println(rowCount1 + "row(s) Post updateOnePostLikeCount!");

			// 再同時更新POST表
			PostJDBCDAO dao = new PostJDBCDAO();
			rowCount2 = dao.updateOnePostLikeCount(newLikeCount, postId, con);

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			System.out.println(rowCount2 + "row(s) LikelistVO insertAndBoo!");
			con.setAutoCommit(true);
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		if (rowCount1 == 1 && rowCount2 == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public LikelistVO getOneLikelistVOForCheck(Integer memberId, Integer postId) {
		LikelistVO likelistVO = new LikelistVO();
		final String INSERT_STMT = "SELECT  member_id, post_id "
								+ "FROM  cga_02.likelist " 
								+ "WHERE  member_id = ? "
								+ "AND  post_id = ? ";
		try (Connection con = JNDIConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, memberId);
			pstmt.setInt(2, postId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				likelistVO.setMemberId(rs.getInt("member_id"));
				likelistVO.setPostId(rs.getInt("post_id"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return likelistVO;
	}
}
