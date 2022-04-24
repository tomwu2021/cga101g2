package com.chargeRecord.model;

import java.sql.*;
import java.util.*;
import com.members.model.*;
import connection.JDBCConnection;

public class ChargeRecordJDBCDAO implements ChargeRecordDAO_interface {

	Connection con;

// 情境一 insert：會員儲值或消費成功後，新增一筆儲值紀錄，並將金額加入到 member 表格中的 E_WALLET_AMOUNT
	@Override
	public ChargeRecordVO insert(ChargeRecordVO chargeRecordVO) {
		con = JDBCConnection.getRDSConnection();

		// 新增一筆儲值或消費紀錄
		ChargeRecordVO chargeRecordVO2 = insert(chargeRecordVO, con);

		// 儲值或消費成功後，將金額加入 member 表格中的 E_WALLET_AMOUNT
		MembersJDBCDAO memberDao = new MembersJDBCDAO();
		MembersVO membersVO1 = new MembersVO();

		membersVO1.setMemberId(chargeRecordVO.getMemberId());
		// 呼叫 MemberJDBCDAO.selectEWalletAmount 取得 member 原本在資料庫的錢包金額
		membersVO1 = memberDao.selectEWalletAmount(membersVO1, con);

		// 當前金額 = 原本金額 + 儲值的金額
		int currentMoney = membersVO1.geteWalletAmount() + chargeRecordVO.getChargeAmount();
		membersVO1.seteWalletAmount(currentMoney);

		// 呼叫 MemberJDBCDAO.changeEWalletAmount 設定錢包金額
		memberDao.changeEWalletAmount(membersVO1, con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chargeRecordVO2;
	}

	public ChargeRecordVO insert(ChargeRecordVO chargeRecordVO, Connection con) {
		final String INSERT_STMT = "INSERT INTO charge_record(member_id, charge_amount) values(?,?);";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, chargeRecordVO.getMemberId());
				pstmt.setInt(2, chargeRecordVO.getChargeAmount());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					chargeRecordVO.setRecordId(rs.getInt(1));
					;
				}
				return chargeRecordVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

// 情境二 select：管理員查詢所有儲值與消費紀錄
	@Override
	public List<ChargeRecordVO> getAll() {

		final String GETALL = "SELECT record_id, member_id, charge_amount, record_time FROM charge_record;";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETALL)) {

			ResultSet rs = pstmt.executeQuery();
			List<ChargeRecordVO> list = new ArrayList<>();
			while (rs.next()) {
				ChargeRecordVO newchargeRecord = new ChargeRecordVO();
				newchargeRecord.setRecordId(rs.getInt("record_id"));
				newchargeRecord.setMemberId(rs.getInt("member_id"));
				newchargeRecord.setChargeAmount(rs.getInt("charge_amount"));
				newchargeRecord.setRecordTime(rs.getTimestamp("record_time"));
				list.add(newchargeRecord);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

// 情境三　select：管理員查詢某會員所有儲值與消費紀錄 
	@Override
	public List<ChargeRecordVO> getAll(Integer id) {

		final String GETALL = "SELECT record_id, member_id, charge_amount, record_time FROM charge_record WHERE member_id =?;";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETALL)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			List<ChargeRecordVO> list = new ArrayList<>();
			while (rs.next()) {
				ChargeRecordVO newchargeRecord = new ChargeRecordVO();
				newchargeRecord.setRecordId(rs.getInt("record_id"));
				newchargeRecord.setMemberId(rs.getInt("member_id"));
				newchargeRecord.setChargeAmount(rs.getInt("charge_amount"));
				newchargeRecord.setRecordTime(rs.getTimestamp("record_time"));
				list.add(newchargeRecord);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ChargeRecordVO getOneById(Integer id) {
		return null;
	}

	@Override
	public boolean delete(ChargeRecordVO chargeRecordVO) {
		return false;
	}

	@Override
	public ChargeRecordVO update(ChargeRecordVO chargeRecordVO) {
		return null;
	}

}
