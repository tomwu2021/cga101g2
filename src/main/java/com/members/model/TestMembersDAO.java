package com.members.model;

import com.ranks.model.RanksVO;

public class TestMembersDAO {

	public static void main(String[] args) {
		MembersJDBCDAO dao = new MembersJDBCDAO();

		// insert 情境一：會員辦帳號時，輸入資料庫的內容
//		MembersVO membersVO1 = new MembersVO();
//		membersVO1.setAccount("lajuhoru@altmails.com");
//		membersVO1.setPassword("!QAZ2wsx");
//		dao.insert(membersVO1);
//		System.out.println(membersVO1);

		// update 情境二：會員可修改的資料 PASSWORD NAME ADDRESS PHONE E_WALLET_PASSWORD
		MembersVO membersVO2 = new MembersVO();
		membersVO2.setMemberId(4);
		membersVO2.setName("小小吳");
		membersVO2.setPhone("0911223344");
		membersVO2.setAddress("30 雲林縣斗南鎮成功6611號");
		dao.update(membersVO2);

		// update 情境三：管理員可以修改此會員的狀態(停權/正常)
//		MembersVO membersVO3 = new MembersVO();
//		membersVO3.setMemberId(20);
//		membersVO3.setStatus(1);
//		dao.changeStatus(membersVO3);

		// update 情境四：管理員可以發送紅利
//		MembersVO membersVO4 = new MembersVO();
//		membersVO4.setMemberId(3);
//		membersVO4.setBonusAmount(300);
//		dao.changeBonus(membersVO4);

		// update 情境五：會員忘記密碼 ( password )
//		MembersVO membersVO5 = new MembersVO();
//		membersVO5.setMemberId(3);
//		dao.forgotPassword(membersVO5);

		// select 情境六：會員查詢會員等級 ( RANK_ID )
//		MembersVO membersVO6 = new MembersVO();
//		membersVO6.setMemberId(2);
//		System.out.println(dao.selectRankId(membersVO6));

		// select 情境七：會員查詢錢包餘額 ( EWALLET_AMOUNT )
//		MembersVO membersVO7 = new MembersVO();
//		membersVO7.setMemberId(3);
//		System.out.println(dao.selectEWalletAmount(membersVO7));

		// select 情境八：會員查詢紅利帳戶 ( BONUS_AMOUNT )
//		MembersVO membersVO8 = new MembersVO();
//		membersVO8.setMemberId(3);
//		System.out.println(dao.selectBonusAmount(membersVO8));

		// select 情境九：管理員使用 member_id 查詢某一筆會員資料
//		MembersVO membersVO9 = new MembersVO();
//		System.out.println(dao.getOneById(3));

		// select 情境十：管理員查詢所有會員資料
//		for(MembersVO m1:dao.getAll()) {
//			System.out.println(m1);
//		}

		// select 情境十一：管理員查詢所有被停權的所有會員 ( status )
//		for(MembersVO m2:dao.getAllStatus()) {
//			System.out.println(m2);
//		}

		// select 情境十二：管理員使用 name 查詢某一筆會員資料
//		MembersVO membersVO10 = new MembersVO();
//		System.out.println(dao.getOneByName("佩佩"));

		// select 情境十三：查詢登入時帳號和密碼
//		MembersVO membersVO10 = new MembersVO();
//		System.out.println(dao.selectForLogin("閔皓","WH1NhjlC"));

		// update 情境十四：會員儲值時，儲值紀錄改動，需要修改目前錢包餘額
//		MembersVO membersVO11 = new MembersVO(); 
//		membersVO11.setMemberId(3);
//		membersVO11.seteWalletAmount(1111);
//		System.out.println(dao.changeEWalletAmount(membersVO11));

		// 用 memberId 查詢 Rank 資訊
//		RanksVO ranksVO1 = new RanksVO(); 
//		ranksVO1 = dao.selectRankInfo(3);
//		System.out.println(ranksVO1);
	}
}
