package com.ranks.model;

public class TestRanksDAO {

	public static void main(String[] args) {
		RanksJDBCDAO dao = new RanksJDBCDAO();

		// 情境一 update：修改 Ranks 的 rank_name、charge_amount、discount、bonus
//		RanksVO ranksVO1 = new RanksVO();
//		ranksVO1.setRankId(1);
//		ranksVO1.setRankName("一般會員");
//		System.out.println(dao.update(ranksVO1));

		// 情境二 select：查詢所有會員等級
//		for (RanksVO rank : dao.getAll()) {
//			System.out.println(rank);
//		}
	}

}
