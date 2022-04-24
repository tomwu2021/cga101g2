package com.ranks.model;

import java.sql.*;
import java.util.*;
import connection.JDBCConnection;

public class RanksJDBCDAO implements RanksDAO_interface {

	Connection con;

// 情境一 update：修改 Ranks 的 rank_name
	@Override
	public RanksVO update(RanksVO ranksVO) {
		con = JDBCConnection.getRDSConnection();
		RanksVO ranksVO2 = update(ranksVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ranksVO2;
	}

	public RanksVO update(RanksVO ranksVO, Connection con) {
		
		final String UPDATE = "UPDATE ranks SET rank_name = ? where rank_id = ?;";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE);
				pstmt.setString(1, ranksVO.getRankName());
				pstmt.setInt(2, ranksVO.getRankId());
				pstmt.executeUpdate();
				return ranksVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 情境二 select：查詢所有會員等級
	@Override
	public List<RanksVO> getAll() {
		final String GETALL = "SELECT rank_id, rank_name, charge_amount, discount, bonus FROM ranks;";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETALL)) {
		
			ResultSet rs = pstmt.executeQuery();
			List<RanksVO> list = new ArrayList<>();
			while (rs.next()) {
				RanksVO newRank = new RanksVO();
				newRank.setRankId(rs.getInt("rank_id"));
				newRank.setRankName(rs.getString("rank_name"));
				newRank.setChargeAmount(rs.getInt("charge_amount"));;
				newRank.setDiscount(rs.getBigDecimal("discount"));;
				newRank.setBonus(rs.getInt("bonus"));;
				list.add(newRank);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RanksVO getOneById(Integer id) {
		return null;
	}

	@Override
	public RanksVO insert(RanksVO t) {
		return null;
	}

	@Override
	public boolean delete(RanksVO t) {
		return false;
	}

}
