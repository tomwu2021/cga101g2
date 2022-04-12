package com.remind.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static connection.JDBCConnection.URL;
import static connection.JDBCConnection.USER;
import static connection.JDBCConnection.PASSWORD;

public class RemindJDBCDAO implements RemindDAO_interface {

	private static final String INSERT = "INSERT INTO remind (member_id, content, time) VALUES (?, ?, ?);";
	/* ��@������ */
	private static final String UPDATE = "UPDATE remind SET content = ?, time = ? WHERE remind_id = ?;";
	private static final String DELETE = "DELETE FROM remind WHERE remind_id = ?;";
	/* �d�@������ */
	private static final String GET_ONE = "SELECT remind_id, member_id, content, time FROM remind WHERE remind_id = ?;";
	/* �d�@�|���Ҧ������A�H�����ɶ��Ƨ� */
	private static final String GET_ALL = "SELECT remind_id, member_id, content, time FROM remind WHERE member_id = ? ORDER BY time;";

	public static void main(String[] args) {

		RemindJDBCDAO dao = new RemindJDBCDAO();
		// �s�W
//		RemindVO rVO1 = new RemindVO();
//		rVO1.setMemberID(1);
//		rVO1.setContent("pclub�K�B");
//		rVO1.setTime(Timestamp.valueOf("2022-04-09 19:00:00"));
//		dao.insert(rVO1);

		// �ק�
//		RemindVO rVO2 = new RemindVO();
//		rVO2.setContent("�쳽�����R�}��");
//		rVO2.setTime(Timestamp.valueOf("2022-04-09 17:00:00"));
//		rVO2.setRemindID(3);
//		dao.update(rVO2);

		// �R��
//		dao.delete(4);

		// �d�@��
//		RemindVO rVO3 = dao.findByPrimaryKey(5);
//		System.out.println(rVO3);

		// �d�@�|��
		List<RemindVO> rlist = dao.getAll(1);
		
		for(RemindVO rVO:rlist) {
			System.out.println(rVO);
		}
	}

	@Override
	public void insert(RemindVO remindVO) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT);) {

			pstmt.setInt(1, remindVO.getMemberID());
			pstmt.setString(2, remindVO.getContent());
			pstmt.setTimestamp(3, remindVO.getTime());

			pstmt.executeUpdate();
			System.out.println("�s�W���\�I");
		} catch (SQLException e) {
			System.err.println("��Ʈw�o�Ϳ��~\n" + e.getMessage());
		}

	}

	@Override
	public void update(RemindVO remindVO) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(UPDATE);) {

			pstmt.setString(1, remindVO.getContent());
			pstmt.setTimestamp(2, remindVO.getTime());
			pstmt.setInt(3, remindVO.getRemindID());

			pstmt.executeUpdate();
			System.out.println("�ק令�\�I");
		} catch (SQLException e) {
			System.err.println("��Ʈw�o�Ϳ��~\n" + e.getMessage());
		}

	}

	@Override
	public void delete(Integer recordID) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(DELETE);) {

			pstmt.setInt(1, recordID);

			pstmt.executeUpdate();
			System.out.println("�R�����\�I");
		} catch (SQLException e) {
			System.err.println("��Ʈw�o�Ϳ��~\n" + e.getMessage());
		}

	}

	@Override
	public RemindVO findByPrimaryKey(Integer recordID) {

		RemindVO rVO = new RemindVO();

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ONE);) {

			pstmt.setInt(1, recordID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				rVO.setRemindID(rs.getInt(1));
				rVO.setMemberID(rs.getInt(2));
				rVO.setContent(rs.getString(3));
				rVO.setTime(rs.getTimestamp(4));
			}

		} catch (SQLException e) {
			System.err.println("��Ʈw�o�Ϳ��~\n" + e.getMessage());
		}
		return rVO;

	}

	@Override
	public List<RemindVO> getAll(Integer memberID) {

		List<RemindVO> list = new ArrayList<RemindVO>();
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL);) {

			pstmt.setInt(1, memberID);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				RemindVO rVO = new RemindVO();
				rVO.setRemindID(rs.getInt(1));
				rVO.setMemberID(rs.getInt(2));
				rVO.setContent(rs.getString(3));
				rVO.setTime(rs.getTimestamp(4));
				list.add(rVO);
			}

		} catch (SQLException e) {
			System.err.println("��Ʈw�o�Ϳ��~\n" + e.getMessage());
		}
		return list;
	}

	@Override
	public List<RemindVO> getAll(Map<String, Object[]> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
