package com.members.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.picture.model.PictureVO;

import connection.JDBCConnection;

public class MembersJDBCDAO implements MembersDAO_interface {

	Connection con;

// 情境一 insert：會員辦帳號時，輸入資料庫的內容 -------------------------------------------------------------------
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

//  update 情境二：會員可修改的資料 ------------------------------------------------------------------------------
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
		final StringBuilder UPDATE = new StringBuilder().append("UPDATE members SET ");
		final String password = membersVO.getPassword();
		if (password != null && !password.isEmpty()) {
			// "UPDATE members SET password = ?, phone = ?, "
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

// update 情境三：管理員可以修改此會員的狀態( 0停權/1正常 ) ---------------------------------------------------------
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
		final String CHANGE_STATUS = "UPDATE members set status=? where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(CHANGE_STATUS);
				pstmt.setInt(1, membersVO.getStatus());
				pstmt.setInt(2, membersVO.getMemberId());
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

// update 情境四：管理員可以發送紅利 ----------------------------------------------------------------------------
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

	public boolean changeBonus(MembersVO membersVO, Connection con) {
		final String CHANGE_BONUS = "UPDATE members set bonus_amount=? where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(CHANGE_BONUS);
				pstmt.setInt(1, membersVO.getBonusAmount());
				pstmt.setInt(2, membersVO.getMemberId());
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

// update 情境五：會員忘記密碼，將一組密碼亂數取代原本資料庫的密碼( password ) ----------------------------------------------------------------
	@Override
	public boolean forgotPassword(MembersVO membersVO) {
		con = JDBCConnection.getRDSConnection();
		Boolean b = forgotPassword(membersVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean forgotPassword(MembersVO membersVO, Connection con) {
		final String FORGET_PASSWORD = "UPDATE members set password=? where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(FORGET_PASSWORD);
				pstmt.setString(1, genAuthCode());
				pstmt.setInt(2, membersVO.getMemberId());
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public String genAuthCode() {
		String strGenAuthCode = "";
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Set set = new HashSet();
		int number01 = (int) (Math.random() * (25 - 0 + 1) + 0); // 0 ~ 25 一定有英文小寫
		set.add(number01);
		int number02 = (int) (Math.random() * (51 - 26 + 1)) + 26; // 26 ~ 51 一定有英文大寫
		set.add(number02);
		int number03 = (int) (Math.random() * (61 - 52 + 1)) + 52; // 52 ~ 61 一定有數字
		set.add(number03);
		while (set.size() != 8) {
			int number = (int) (Math.random() * 61);
			set.add(number);
		}
		Iterator objs = set.iterator();
		while (objs.hasNext()) {
			char ch = str.charAt((int) objs.next());
			strGenAuthCode += ch;
		}
		return strGenAuthCode;
	}

// select 情境另：會員查詢會員等級 ( RANK_ID ) ---------------------------------------------------------
	@Override
	public int selectRankId(MembersVO membersVO) {
		con = JDBCConnection.getRDSConnection();
		MembersVO membersVO2 = selectRankId(membersVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersVO2.getRankId();
	}

	public MembersVO selectRankId(MembersVO membersVO, Connection con) {
		final String SELECT_RANKID = "SELECT rank_id from members where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_RANKID);
				pstmt.setInt(1, membersVO.getMemberId());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					MembersVO newMember = new MembersVO();
					newMember.setRankId(rs.getInt("rank_id"));
					return newMember;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

// select 情境七：會員查詢錢包餘額 ( E_WALLET_AMOUNT ) ------------------------------------------
	@Override
	public int selectEWalletAmount(MembersVO membersVO) {
		con = JDBCConnection.getRDSConnection();
		MembersVO membersVO2 = selectEWalletAmount(membersVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersVO2.geteWalletAmount();
	}

	public MembersVO selectEWalletAmount(MembersVO membersVO, Connection con) {
		final String SELECT_E_WALLET_AMOUNT = "SELECT e_wallet_amount from members where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_E_WALLET_AMOUNT);
				pstmt.setInt(1, membersVO.getMemberId());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					MembersVO newMember = new MembersVO();
					newMember.seteWalletAmount(rs.getInt("e_wallet_amount"));
					return newMember;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

// select 情境八：會員查詢紅利帳戶 ( BONUS_AMOUNT	 )
	@Override
	public int selectBonusAmount(MembersVO membersVO) {
		con = JDBCConnection.getRDSConnection();
		MembersVO membersVO2 = selectBonusAmount(membersVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersVO2.getBonusAmount();
	}

	public MembersVO selectBonusAmount(MembersVO membersVO, Connection con) {
		final String SELECT_BONUSAMOUNT = "SELECT bonus_amount from members where member_id = ?";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_BONUSAMOUNT);
				pstmt.setInt(1, membersVO.getMemberId());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					MembersVO newMember = new MembersVO();
					newMember.setBonusAmount(rs.getInt("bonus_amount"));
					return newMember;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	@Override
	public boolean delete(MembersVO t) {
		// TODO Auto-generated method stub
		return false;
	}

}
