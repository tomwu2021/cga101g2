package com.notification.model;

import java.sql.*;
import java.util.*;
import connection.JNDIConnection;

public class NotificationDAO implements NotificationDAO_interface {

	Connection con;

// 情境一 insert：新增一筆通知
	@Override
	public NotificationVO insert(NotificationVO notificationVO) {
		con = JNDIConnection.getRDSConnection();
		NotificationVO notificationVO2 = insert(notificationVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notificationVO2;
	}

	public NotificationVO insert(NotificationVO notificationVO, Connection con) {
		final String INSERT_STMT = "INSERT INTO notification(member_id, context, status, url) values(?,?,0,?);";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, notificationVO.getMemberId());
				pstmt.setString(2, notificationVO.getContext());
				pstmt.setString(3, notificationVO.getUrl());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					notificationVO.setNotificationId(rs.getInt(1));
				}
				return notificationVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

// 情境二 select：查詢某會員 id 所有通知
	@Override
	public List<NotificationVO> getAllById(Integer id) {
		con = JNDIConnection.getRDSConnection();
		List<NotificationVO> list = getAllById(id, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<NotificationVO> getAllById(Integer id, Connection con) {

		final String SELECT_ONE_BYID = "select notification_id, member_id, context, time, status, url from notification where member_id = ? order by time DESC;";

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_ONE_BYID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				List<NotificationVO> list = new ArrayList<>();
				while (rs.next()) {
					NotificationVO newNotification = new NotificationVO();
					newNotification.setNotificationId(rs.getInt("notification_id"));
					newNotification.setMemberId(rs.getInt("member_id"));
					newNotification.setContext(rs.getString("context"));
					newNotification.setTime(rs.getTimestamp("time"));
					newNotification.setStatus(rs.getInt("status"));
					newNotification.setUrl(rs.getString("url"));
					list.add(newNotification);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

// 情境三 update：會員查看通知後，status 變成已讀 1
	@Override
	public Boolean updateStatus(NotificationVO notificationVO) {
		con = JNDIConnection.getRDSConnection();
		Boolean b = updateStatus(notificationVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public Boolean updateStatus(NotificationVO notificationVO, Connection con) {
		final String UPDATE = "UPDATE notification SET status = 1 where notification_id = ?;";

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE);
				pstmt.setInt(1, notificationVO.getNotificationId());
				pstmt.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<NotificationVO> getAll() {
		return null;
	}

	@Override
	public NotificationVO update(NotificationVO notificationVO) {
		return null;
	}

	@Override
	public boolean delete(NotificationVO notificationVO) {
		return false;
	}

	@Override
	public NotificationVO getOneById(Integer id) {
		return null;
	}

}
