package com.members.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.picture.model.PictureVO;

import connection.JDBCConnection;

public class MembersJDBCDAO implements MembersDAO_interface {

	Connection con;

	@Override
	public MembersVO insert(MembersVO membersVO) {
		con = JDBCConnection.getRDSConnection();
		MembersVO membersVO2 = insert(membersVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersVO2;
	}

	public MembersVO insert(MembersVO membersVO, Connection con) {
		// 情境一 insert：會員辦帳號時，輸入資料庫的內容
		final String INSERT_STMT = "INSERT INTO members(account,password,name,rank_id,e_wallet_amount,bonus_amount,status) values(?,?,?,1,0,0,1);";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, membersVO.getAccount());
				pstmt.setString(2, membersVO.getPassword());
				pstmt.setString(3, membersVO.getAccount());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					membersVO.setMemberId(rs.getInt(1));
				}
				return membersVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public MembersVO update(MembersVO membersVO) {
		con = JDBCConnection.getRDSConnection();
		MembersVO membersVO2 = update(membersVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersVO2;
	}

	public MembersVO update(MembersVO membersVO, Connection con) {
		// 情境二 update：會員可修改的資料 PASSWORD NAME ADDRESS PHONE E_WALLET_PASSWORD
		final StringBuilder UPDATE = new StringBuilder().append("UPDATE members SET ");

		final String password = membersVO.getPassword();
		if (password != null && !password.isEmpty()) {
			UPDATE.append("password = ?,");
		}
		final String name = membersVO.getName();
		if (name != null && !name.isEmpty()) {
			UPDATE.append("name = ?,");
		}
		final String address = membersVO.getAddress();
		if (address != null && !address.isEmpty()) {
			UPDATE.append("address = ?,");
		}
		final String phone = membersVO.getPhone();
		if (phone != null && !phone.isEmpty()) {
			UPDATE.append("phone = ?,");
		}
		final String e_wallet_password = membersVO.geteWalletPassword();
		if (e_wallet_password != null && !e_wallet_password.isEmpty()) {
			UPDATE.append("e_wallet_password = ?,");
		}
		UPDATE.append("status = 1 ").append("where member_id = ?");

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE.toString());
				int offset = 1;

				if (password != null && !password.isEmpty()) {
					pstmt.setString(offset, membersVO.getPassword());
					offset += 1;
				}
				if (name != null && !name.isEmpty()) {
					pstmt.setString(offset, membersVO.getName());
					offset += 1;
				}
				if (address != null && !address.isEmpty()) {
					pstmt.setString(offset, membersVO.getAddress());
					offset += 1;
				}
				if (phone != null && !phone.isEmpty()) {
					pstmt.setString(offset, membersVO.getPhone());
					offset += 1;
				}
				if (e_wallet_password != null && !e_wallet_password.isEmpty()) {
					pstmt.setString(offset, membersVO.geteWalletPassword());
					offset += 1;
				}
				pstmt.setInt(offset, membersVO.getMemberId());

				pstmt.executeUpdate();
				return membersVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean changeStatus(MembersVO membersVO) {
		con = JDBCConnection.getRDSConnection();
		Boolean b = changeStatus(membersVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean changeStatus(MembersVO membersVO, Connection con) {
		// update 情境二：管理員可以修改此會員的狀態(停權/正常)
		final String CHANGESTATUS = "UPDATE members set status=? where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(CHANGESTATUS);
				pstmt.setInt(1,membersVO.getStatus());
				pstmt.setInt(2,membersVO.getMemberId());
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean changeBonus(MembersVO membersVO) {
		con = JDBCConnection.getRDSConnection();
		Boolean b = changeBonus(membersVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean changeBonus(MembersVO membersVO,Connection con) {
		// update 情境三：管理員可以發送紅利
		final String CHANGEBONUS = "UPDATE members set bonus_amount=? where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(CHANGEBONUS);
				pstmt.setInt(1,membersVO.getBonusAmount());
				pstmt.setInt(2,membersVO.getMemberId());
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
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

	@Override
	public boolean delete(MembersVO t) {
		// TODO Auto-generated method stub
		return false;
	}



}
