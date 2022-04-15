package com.members.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import connection.JDBCConnection;

public class MembersJDBCDAO implements MembersDAO_interface {

	Connection con;

// 情境一 insert：會員辦帳號時，輸入資料庫的內容 -----------------------------------------------------------------------
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

//  update 情境二：會員可修改的資料 ---------------------------------------------------------------------------------
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

// update 情境三：管理員可以修改此會員的狀態( 0停權/1正常 ) -------------------------------------------------------------
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

// update 情境四：管理員可以發送紅利 ---------------------------------------------------------------------------------
	@Override
	public boolean changeBonus(MembersVO membersVO) {

		con = JDBCConnection.getRDSConnection();
		int currentBonusAmount = selectBonusAmount(membersVO, con) + membersVO.getBonusAmount(); // 現在的紅利 = 取得資料庫原本的紅利 +
																									// 管理員給的紅利
		Boolean b = changeBonus(membersVO, con, currentBonusAmount); // 變更紅利的金額
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean changeBonus(MembersVO membersVO, Connection con, int bouns) {

		final String CHANGE_BONUS = "UPDATE members set bonus_amount=? where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(CHANGE_BONUS);
				pstmt.setInt(1, bouns);
				pstmt.setInt(2, membersVO.getMemberId());
				pstmt.execute();
//				System.out.println("3：" + con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

// update 情境五：會員忘記密碼，將一組密碼亂數取代原本資料庫的密碼( password ) ----------------------------------------------
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

// select 情境六：會員查詢會員等級 ( RANK_ID ) -----------------------------------------------------------------------
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

// select 情境七：會員查詢錢包餘額 ( E_WALLET_AMOUNT ) --------------------------------------------------------------
	@Override
	public int selectEWalletAmount(MembersVO membersVO) {

		con = JDBCConnection.getRDSConnection();
		MembersVO membersVO2 = selectEWalletAmount(membersVO, con);
		int queryAccount = membersVO2.geteWalletAmount();

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return queryAccount;
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
					newMember.setMemberId(membersVO.getMemberId());
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
		int currentBonusAmount = selectBonusAmount(membersVO, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currentBonusAmount;
	}

	public int selectBonusAmount(MembersVO membersVO, Connection con) {

		final String SELECT_BONUSAMOUNT = "SELECT bonus_amount from members where member_id = ?";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_BONUSAMOUNT);
				pstmt.setInt(1, membersVO.getMemberId());
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					MembersVO newMember = new MembersVO();
					newMember.setBonusAmount(rs.getInt("bonus_amount"));
					return newMember.getBonusAmount();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

// select 情境九：管理員使用 member_id 查詢某一筆會員資料 ---------------------------------------------------------------
	@Override
	public MembersVO getOneById(Integer id) {

		con = JDBCConnection.getRDSConnection();
		MembersVO membersVO2 = getOneById(id, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersVO2;
	}

	public MembersVO getOneById(Integer id, Connection con) {

		final String SELECT_ONE_BYID = "SELECT member_id,account,name,address,phone,rank_id,e_wallet_amount,bonus_amount,status,create_time "
				+ "FROM members where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_ONE_BYID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					MembersVO newMember = new MembersVO();
					newMember.setMemberId(rs.getInt("member_id"));
					newMember.setAccount(rs.getString("account"));
					newMember.setName(rs.getString("name"));
					newMember.setAddress(rs.getString("address"));
					newMember.setPhone(rs.getString("phone"));
					newMember.setRankId(rs.getInt("rank_id"));
					newMember.seteWalletAmount(rs.getInt("e_wallet_amount"));
					newMember.setBonusAmount(rs.getInt("bonus_amount"));
					newMember.setStatus(rs.getInt("status"));
					newMember.setCreateTime(rs.getTimestamp("create_time"));
					return newMember;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

// select 情境十：管理員查詢所有會員資料 ------------------------------------------------------------------------------
	@Override
	public List<MembersVO> getAll() {

		final String GETALL = "SELECT member_id,account,name,address,phone,rank_id,e_wallet_amount,bonus_amount,status,create_time FROM members;";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETALL)) {
			ResultSet rs = pstmt.executeQuery();
			List<MembersVO> list = new ArrayList<>();
			while (rs.next()) {
				MembersVO newMember = new MembersVO();
				newMember.setMemberId(rs.getInt("member_id"));
				newMember.setAccount(rs.getString("account"));
				newMember.setName(rs.getString("name"));
				newMember.setAddress(rs.getString("address"));
				newMember.setPhone(rs.getString("phone"));
				newMember.setRankId(rs.getInt("rank_id"));
				newMember.seteWalletAmount(rs.getInt("e_wallet_amount"));
				newMember.setBonusAmount(rs.getInt("bonus_amount"));
				newMember.setStatus(rs.getInt("status"));
				newMember.setCreateTime(rs.getTimestamp("create_time"));
				list.add(newMember);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

// select 情境十一：管理員查詢所有被停權的所有會員 ( status )------------------------------------------------------------
	public List<MembersVO> getAllStatus() {

		final String GETALL_STATUS = "SELECT member_id,account,name,address,phone,rank_id,e_wallet_amount,bonus_amount,status,create_time FROM members where status = ?;";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETALL_STATUS)) {

			pstmt.setInt(1, 0);
			ResultSet rs = pstmt.executeQuery();
			List<MembersVO> list = new ArrayList<>();
			while (rs.next()) {
				MembersVO newMember = new MembersVO();
				newMember.setMemberId(rs.getInt("member_id"));
				newMember.setAccount(rs.getString("account"));
				newMember.setName(rs.getString("name"));
				newMember.setAddress(rs.getString("address"));
				newMember.setPhone(rs.getString("phone"));
				newMember.setRankId(rs.getInt("rank_id"));
				newMember.seteWalletAmount(rs.getInt("e_wallet_amount"));
				newMember.setBonusAmount(rs.getInt("bonus_amount"));
				newMember.setStatus(rs.getInt("status"));
				newMember.setCreateTime(rs.getTimestamp("create_time"));
				list.add(newMember);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

// select 情境十二：管理員使用 name 查詢某一筆會員資料 ------------------------------------------------------------------
	@Override
	public MembersVO getOneByName(String name) {
		final String GETONE_BY_NAME = "SELECT member_id,account,name,address,phone,rank_id,e_wallet_amount,bonus_amount,status,create_time FROM members where name = ?;";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETONE_BY_NAME)) {

			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				MembersVO newMember = new MembersVO();
				newMember.setMemberId(rs.getInt("member_id"));
				newMember.setAccount(rs.getString("account"));
				newMember.setName(rs.getString("name"));
				newMember.setAddress(rs.getString("address"));
				newMember.setPhone(rs.getString("phone"));
				newMember.setRankId(rs.getInt("rank_id"));
				newMember.seteWalletAmount(rs.getInt("e_wallet_amount"));
				newMember.setBonusAmount(rs.getInt("bonus_amount"));
				newMember.setStatus(rs.getInt("status"));
				newMember.setCreateTime(rs.getTimestamp("create_time"));
				return newMember;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MembersVO selectForLogin(String name, String password) {

		con = JDBCConnection.getRDSConnection();
		MembersVO membersVO2 = selectForLogin(name, password, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersVO2;
	}

// select 情境十三：查詢登入時帳號和密碼 ------------------------------------------------------------------------------
	public MembersVO selectForLogin(String name, String password, Connection con) {

		final String SELECT_FOR_LOGIN = "select member_id,account,name,address,phone,rank_id,e_wallet_amount,bonus_amount,status,create_time from members where name = ? and PASSWORD = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_FOR_LOGIN);
				pstmt.setString(1, name);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					MembersVO newMember = new MembersVO();
					newMember.setMemberId(rs.getInt("member_id"));
					newMember.setAccount(rs.getString("account"));
					newMember.setName(rs.getString("name"));
					newMember.setAddress(rs.getString("address"));
					newMember.setPhone(rs.getString("phone"));
					newMember.setRankId(rs.getInt("rank_id"));
					newMember.seteWalletAmount(rs.getInt("e_wallet_amount"));
					newMember.setBonusAmount(rs.getInt("bonus_amount"));
					newMember.setStatus(rs.getInt("status"));
					newMember.setCreateTime(rs.getTimestamp("create_time"));
					return newMember;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean changeEWalletAmount(MembersVO membersVO) {

		con = JDBCConnection.getRDSConnection();
		Boolean b = changeEWalletAmount(membersVO, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean changeEWalletAmount(MembersVO membersVO, Connection con) {

		final String UPDATEWALLETAMOUNT = "UPDATE members SET e_wallet_amount = ? where member_id = ?";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATEWALLETAMOUNT);
				pstmt.setInt(1, membersVO.geteWalletAmount());
				pstmt.setInt(2, membersVO.getMemberId());
				pstmt.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(MembersVO t) {
		return false;
	}
}
