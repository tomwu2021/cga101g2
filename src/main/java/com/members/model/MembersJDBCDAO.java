package com.members.model;

import java.sql.*;
import java.util.*;

import com.chargeRecord.model.ChargeRecordDAO;
import com.chargeRecord.model.ChargeRecordVO;
import com.ranks.model.RanksVO;

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
		final String INSERT_STMT = "INSERT INTO members(account,password,name,rank_id,ewallet_amount,bonus_amount,status) values(?,?,?,1,0,0,1);";
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

		String password = membersVO.getPassword();
		if (password != null && !password.isEmpty()) {
			UPDATE.append("password = ?,");
		}
		String name = membersVO.getName();
		if (name != null && !name.isEmpty()) {
			UPDATE.append("name = ?,");
		}
		String address = membersVO.getAddress();
		if (address != null && !address.isEmpty()) {
			UPDATE.append("address = ?,");
		}
		String phone = membersVO.getPhone();
		if (phone != null && !phone.isEmpty()) {
			UPDATE.append("phone = ?,");
		}
		String eWalletPassword = membersVO.geteWalletPassword();
		if (eWalletPassword != null && !eWalletPassword.isEmpty()) {
			UPDATE.append("ewallet_password = ?,");
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
				if (eWalletPassword != null && !eWalletPassword.isEmpty()) {
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

// update 情境四：更新紅利 ---------------------------------------------------------------------------------
	@Override
	public boolean changeBonus(MembersVO membersVO) {

		con = JDBCConnection.getRDSConnection();
		int currentBonusAmount = selectBonusAmount(membersVO, con) + membersVO.getBonusAmount(); // 現在的紅利 = 取得資料庫原本的紅利 +
																									// 管理員給的紅利
		Boolean b = changeBonus(membersVO, currentBonusAmount, con); // 變更紅利的金額
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean changeBonus(MembersVO membersVO, Integer bouns, Connection con) {

		final String CHANGE_BONUS = "UPDATE members set bonus_amount=? where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(CHANGE_BONUS);
				pstmt.setInt(1, bouns);
				pstmt.setInt(2, membersVO.getMemberId());
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

// update 情境五：會員忘記密碼，將一組密碼亂數取代原本資料庫的密碼( password ) ----------------------------------------------
	@Override
	public String forgotPassword(MembersVO membersVO) {

		con = JDBCConnection.getRDSConnection();
		String genAuthCode = forgotPassword(membersVO, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genAuthCode;
	}

	public String forgotPassword(MembersVO membersVO, Connection con) {

		final String FORGET_PASSWORD = "UPDATE members set password=? where member_id = ?;";

		String genAuthCode = genAuthCode();

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(FORGET_PASSWORD);
				pstmt.setString(1, genAuthCode);
				pstmt.setInt(2, membersVO.getMemberId());
				pstmt.execute();
				return genAuthCode;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
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

// select 情境七：會員查詢錢包餘額 ( EWALLET_AMOUNT ) --------------------------------------------------------------
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

		final String SELECT_EWALLET_AMOUNT = "SELECT ewallet_amount from members where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_EWALLET_AMOUNT);
				pstmt.setInt(1, membersVO.getMemberId());

				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					MembersVO newMember = new MembersVO();
					newMember.seteWalletAmount(rs.getInt("ewallet_amount"));
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

		final String SELECT_ONE_BYID = "SELECT member_id,account,name,address,phone,rank_id,ewallet_amount,bonus_amount,status,create_time "
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
					newMember.seteWalletAmount(rs.getInt("ewallet_amount"));
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

		final String GETALL = "SELECT member_id,account,name,address,phone,rank_id,ewallet_amount,bonus_amount,status,create_time,DATE_FORMAT(create_time,'%Y-%m-%d %H:%i') createTimeString FROM members;";

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
				newMember.seteWalletAmount(rs.getInt("ewallet_amount"));
				newMember.setBonusAmount(rs.getInt("bonus_amount"));
				newMember.setStatus(rs.getInt("status"));
				newMember.setCreateTime(rs.getTimestamp("create_time"));
				newMember.setCreateTimeString(rs.getString("createTimeString"));
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

		final String GETALL_STATUS = "SELECT member_id,account,name,address,phone,rank_id,ewallet_amount,bonus_amount,status,create_time FROM members where status = ?;";

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
				newMember.seteWalletAmount(rs.getInt("ewallet_amount"));
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
		final String GETONE_BY_NAME = "SELECT member_id,account,name,address,phone,rank_id,ewallet_amount,bonus_amount,status,create_time FROM members where name like ?;";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETONE_BY_NAME)) {

			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				MembersVO newMember = new MembersVO();
				newMember.setMemberId(rs.getInt("member_id"));
				newMember.setAccount(rs.getString("account"));
				newMember.setName(rs.getString("name"));
				newMember.setAddress(rs.getString("address"));
				newMember.setPhone(rs.getString("phone"));
				newMember.setRankId(rs.getInt("rank_id"));
				newMember.seteWalletAmount(rs.getInt("ewallet_amount"));
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

		final String SELECT_FOR_LOGIN = "SELECT member_id, account, password, name, address, phone, rank_id, ewallet_amount, ewallet_password, bonus_amount, status, create_time, DATE_FORMAT(create_time,'%Y-%m-%d %H:%i') createTimeString from members\r\n"
				+ "				where account = ? and password = ?;";
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
					newMember.setPassword(rs.getString("password"));
					newMember.setName(rs.getString("name"));
					newMember.setAddress(rs.getString("address"));
					newMember.setPhone(rs.getString("phone"));
					newMember.setRankId(rs.getInt("rank_id"));
					newMember.seteWalletAmount(rs.getInt("ewallet_amount"));
					newMember.seteWalletPassword(rs.getString("ewallet_password"));
					newMember.setBonusAmount(rs.getInt("bonus_amount"));
					newMember.setStatus(rs.getInt("status"));
					newMember.setCreateTime(rs.getTimestamp("create_time"));
					newMember.setCreateTimeString(rs.getString("createTimeString"));
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

		final String UPDATEWALLETAMOUNT = "UPDATE members SET ewallet_amount = ? where member_id = ?";
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
	public boolean deleteOneById(Integer memberId) {
		con = JDBCConnection.getRDSConnection();
		Boolean b = deleteOneById(memberId, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean deleteOneById(Integer memberId, Connection con) {
		final String DELETE_ONE_MEMBER = "delete from members where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE_ONE_MEMBER);
				pstmt.setInt(1, memberId);
				pstmt.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(MembersVO membersVO) {
		// TODO Auto-generated method stub
		return false;
	}

	// select 情境九：用 account 查詢某一筆會員資料是否存在
	// ---------------------------------------------------------------
	@Override
	public boolean getOneByAccount(String account) {

		con = JDBCConnection.getRDSConnection();
		Boolean boo = getOneByAccount(account, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}

	public Boolean getOneByAccount(String account, Connection con) {

		final String SELECT_ONE_BYACCOUNT = "SELECT member_id,account,name,address,phone,rank_id,ewallet_amount,bonus_amount,status,create_time "
				+ "FROM members where account = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_ONE_BYACCOUNT);
				pstmt.setString(1, account);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					MembersVO newMember = new MembersVO();
					newMember.setMemberId(rs.getInt("member_id"));
					newMember.setAccount(rs.getString("account"));
					newMember.setName(rs.getString("name"));
					newMember.setAddress(rs.getString("address"));
					newMember.setPhone(rs.getString("phone"));
					newMember.setRankId(rs.getInt("rank_id"));
					newMember.seteWalletAmount(rs.getInt("ewallet_amount"));
					newMember.setBonusAmount(rs.getInt("bonus_amount"));
					newMember.setStatus(rs.getInt("status"));
					newMember.setCreateTime(rs.getTimestamp("create_time"));
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public MembersVO selectMemberIdByAccount(String account) {
		con = JDBCConnection.getRDSConnection();
		MembersVO membersVO = selectMemberIdByAccount(account, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersVO;
	}

	public MembersVO selectMemberIdByAccount(String account, Connection con) {
		final String SELECT_ONE_INFO_BYACCOUNT = "SELECT member_id,account,name,address,phone,rank_id,ewallet_amount,bonus_amount,status,create_time "
				+ "FROM members where account = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_ONE_INFO_BYACCOUNT);
				pstmt.setString(1, account);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					MembersVO newMember = new MembersVO();
					newMember.setMemberId(rs.getInt("member_id"));
					newMember.setAccount(rs.getString("account"));
					newMember.setName(rs.getString("name"));
					newMember.setAddress(rs.getString("address"));
					newMember.setPhone(rs.getString("phone"));
					newMember.setRankId(rs.getInt("rank_id"));
					newMember.seteWalletAmount(rs.getInt("ewallet_amount"));
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
	public RanksVO selectRankInfo(Integer memberId) {
		con = JDBCConnection.getRDSConnection();
		RanksVO ranksVO = selectRankInfo(memberId, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ranksVO;
	}

	public RanksVO selectRankInfo(Integer memberId, Connection con) {
		final String SELECT_ONE_RANK_INFO_BY_MEMBERID = "SELECT r.rank_id, r.rank_name, r.charge_amount, r.discount,r.bonus FROM members m join ranks r on m.rank_id = r.rank_id where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_ONE_RANK_INFO_BY_MEMBERID);
				pstmt.setInt(1, memberId);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					RanksVO newRankVO = new RanksVO();
					newRankVO.setRankId(rs.getInt("r.rank_id"));
					newRankVO.setRankName(rs.getString("r.rank_name"));
					newRankVO.setChargeAmount(rs.getInt("r.charge_amount"));
					newRankVO.setDiscount(rs.getBigDecimal("r.discount"));
					newRankVO.setBonus(rs.getInt("r.bonus"));
					return newRankVO;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean walletPaymentAddMoney(Integer memberId, Integer money) {

		con = JDBCConnection.getRDSConnection();
		Boolean boo = walletPaymentAddMoney(memberId, money, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;

	}

	// ewallet_amount 會員錢包 消費/儲值
	public boolean walletPaymentAddMoney(Integer memberId, Integer money, Connection con) {

		if (con != null) {
			MembersVO originalMembersVO = new MembersVO();
			MembersVO currentMembersVO = new MembersVO();
			ChargeRecordVO chargeRecordVO = new ChargeRecordVO();

			MembersDAO membersDAO = new MembersDAO();
			ChargeRecordDAO chargeRecordDAO = new ChargeRecordDAO();

			// 用 memberId 取得 會員的錢包的餘額
			originalMembersVO = membersDAO.getOneById(memberId);
			Integer originalMoney = originalMembersVO.geteWalletAmount();

			// 取得會員本身的餘額後，增加 或 減少 金額
			Integer currentMoney = originalMoney + money;

			// 更新會員錢包的值
			currentMembersVO.seteWalletAmount(currentMoney);
			currentMembersVO.setMemberId(memberId);
			membersDAO.changeEWalletAmount(currentMembersVO, con);
//			System.out.println(currentMembersVO);

			// 呼叫 ChargeRecordDAO 新增一筆交易紀錄
			chargeRecordVO.setMemberId(memberId);
			chargeRecordVO.setChargeAmount(money);
			chargeRecordDAO.insert(chargeRecordVO, con);

			return true;
		}

		return false;
	}

	// 紅利 消費/發送
	@Override
	public boolean bonusPaymentAddValue(Integer memberId, Integer bonus) {

		con = JDBCConnection.getRDSConnection();
		Boolean boo = bonusPaymentAddValue(memberId, bonus, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}

	public boolean bonusPaymentAddValue(Integer memberId, Integer bonus, Connection con) {
		if (con != null) {
			MembersDAO membersDAO = new MembersDAO();

			MembersVO originalMembersVO = new MembersVO();
			MembersVO currentMembersVO = new MembersVO();

			// 用 memberId 取得 會員的紅利的餘額
			originalMembersVO = membersDAO.getOneById(memberId);
			Integer originalBonus = originalMembersVO.getBonusAmount();

			// 增加 或 減少
			Integer currentMoney = originalBonus + bonus;

			// 更新會員錢包的值
			currentMembersVO.setBonusAmount(originalBonus);
			currentMembersVO.setMemberId(memberId);
			membersDAO.changeBonus(currentMembersVO, currentMoney, con);
			System.out.println(currentMembersVO);

			return true;
		}

		return false;
	}

	// 用 memberId 取得 eWalletPassword
	@Override
	public String geteWalletPassword(Integer memberId) {
		con = JDBCConnection.getRDSConnection();
		String eWalletPassword = geteWalletPassword(memberId, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eWalletPassword;
	}

	public String geteWalletPassword(Integer memberId, Connection con) {
		final String SELECT_EWALLETPASSWORD_BY_MEMBERID = "SELECT ewallet_password FROM members where member_id = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_EWALLETPASSWORD_BY_MEMBERID);
				pstmt.setInt(1, memberId);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					return rs.getString("ewallet_password");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "查無此密碼";
	}

	// 修改會員等級
	@Override
	public Boolean updateRank(Integer memberId, Integer rankId) {
		con = JDBCConnection.getRDSConnection();
		Boolean boo = updateRank(memberId, rankId, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}

	public Boolean updateRank(Integer memberId, Integer rankId, Connection con) {
		final String UPDATE_RANK = "UPDATE members SET rank_id = ? where member_id = ?;";
		if (con != null) {
			try {

				PreparedStatement pstmt = con.prepareStatement(UPDATE_RANK);
				pstmt.setInt(1, rankId);
				pstmt.setInt(2, memberId);
				pstmt.executeUpdate();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;

	}
}
