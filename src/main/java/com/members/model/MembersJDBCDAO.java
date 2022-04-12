package com.members.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connection.JDBCConnection;

public class MembersJDBCDAO implements MembersDAO_interface {

	public MembersVO insert(MembersVO membersVO) {
		// 情境一 insert：會員辦帳號時，輸入資料庫的內容
		final String INSERT_STMT = "INSERT INTO members(account,password,name,rank_id,e_wallet_amount,bonus_amount,status) values(?,?,?,1,0,0,1);";

		try (Connection connection = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT)) {
			
			pstmt.setString(1, membersVO.getAccount());
			pstmt.setString(2, membersVO.getPassword());
			pstmt.setString(3, membersVO.getAccount());
			pstmt.executeUpdate();
			return membersVO;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete() {
	}

	@Override
	public MembersVO update(MembersVO membersVO) {
		// 情境二 update：會員可修改的資料 PASSWORD NAME ADDRESS PHONE E_WALLET_PASSWORD
		final StringBuilder UPDATE = new StringBuilder().append("UPDATE members SET ");

		int offset = 0;
		final String password = membersVO.getPassword();
		if (password != null && !password.isEmpty()) {
			UPDATE.append("password = ?,");
			offset = 1;
		}
		final String name = membersVO.getName();
		if (name != null && !name.isEmpty()) {
			UPDATE.append("name = ?,");
			offset = 2;
		}
		final String address = membersVO.getAddress();
		if (address != null && !address.isEmpty()) {
			UPDATE.append("address = ?,");
			offset = 3;
		}
		final String phone = membersVO.getPhone();
		if (phone != null && !phone.isEmpty()) {
			UPDATE.append("phone = ?,");
			offset = 4;
		}
		final String e_wallet_password = membersVO.geteWalletPassword();
		if (e_wallet_password != null && !e_wallet_password.isEmpty()) {
			UPDATE.append("e_wallet_password = ?,");
			offset = 5;
		}
		

		try (Connection connection = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE.toString())) {
			if (password != null && !password.isEmpty()) {
				pstmt.setString(1, membersVO.getPassword());
			}
			pstmt.setString(1 + offset, membersVO.getName());
			pstmt.setString(2 + offset, membersVO.getAddress());
			pstmt.setString(3 + offset, membersVO.getPhone());
			pstmt.setString(4 + offset, membersVO.geteWalletPassword());
			pstmt.setInt(5 + offset, membersVO.getMemberId());
			pstmt.executeUpdate();
			return membersVO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MembersVO getOneById(Integer id) {
		String GET_ONE_STMT = "SELECT " + "member_id,account,password,name,address,phone,"
				+ "rank_id,e_wallet_amount,e_wallet_password,bonus_amount,status,create_time "
				+ "FROM members where member_id = ?";

		return null;
	}

	@Override
	public List<MembersVO> getAll() {
		String GET_ALL_STMT = "SELECT " + "member_id,account,password,name,address,phone,"
				+ "rank_id,e_wallet_amount,e_wallet_password,bonus_amount,status,create_time "
				+ "FROM members order by member_id";

		return null;
	}

}
