package com.chargeRecord.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.members.model.MembersJDBCDAO;
import com.members.model.MembersVO;

import connection.JDBCConnection;

public class ChargeRecordJDBCDAO implements ChargeRecordDAO_interface {

	Connection con;

// 情境一 insert：會員儲值成功後，新增一筆儲值紀錄，並將金額加入到 member 表格中的 E_WALLET_AMOUNT
	@Override
	public ChargeRecordVO insert(ChargeRecordVO chargeRecordVO) {
		con = JDBCConnection.getRDSConnection();

		// 新增一筆儲值紀錄
		ChargeRecordVO chargeRecordVO2 = insert(chargeRecordVO, con);

		// 儲值成功後，將金額加入 member 表格中的 E_WALLET_AMOUNT
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

	@Override
	public boolean delete(ChargeRecordVO chargeRecordVO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChargeRecordVO update(ChargeRecordVO chargeRecordVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChargeRecordVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChargeRecordVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
