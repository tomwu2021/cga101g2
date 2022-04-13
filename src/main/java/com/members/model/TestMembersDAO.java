package com.members.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class TestMembersDAO {

	public static void main(String[] args) {
		MembersJDBCDAO dao = new MembersJDBCDAO();

		// insert 情境一：會員辦帳號時，輸入資料庫的內容
		/*MembersVO membersVO1 = new MembersVO();
		membersVO1.setAccount("Program@pet.com"); // 動態
		membersVO1.setPassword("!QAZ2wsx"); // 動態
		dao.insert(membersVO1);
		System.out.println(membersVO1);*/
		

		// update 情境二：會員可修改的資料　PASSWORD	NAME　ADDRESS　PHONE　E_WALLET_PASSWORD	
		/*MembersVO membersVO2 = new MembersVO();
		membersVO2.setMemberId(23);
		membersVO2.setPassword("!QAZ2wsx");
		membersVO2.setName("張政勳");
		membersVO2.setAddress("臺北市文山區景福街23號");
		membersVO2.setPhone("0988425512");
		membersVO2.seteWalletPassword("337581");
		dao.update(membersVO2);*/

		// update 情境三：管理員可以修改此會員的狀態(停權/正常)
		/*MembersVO membersVO3 = new MembersVO();
		membersVO3.setMemberId(19);
		membersVO3.setStatus(0);
		dao.changeStatus(membersVO3);*/
		
		// update 情境四：管理員可以發送紅利
		/*MembersVO membersVO4 = new MembersVO();
		membersVO4.setMemberId(3);
		membersVO4.setBonusAmount(200);
		dao.changeBonus(membersVO4);*/
		
		// update 情境五：會員忘記密碼 ( password )
		/*MembersVO membersVO5 = new MembersVO();
		membersVO5.setMemberId(22);
		dao.forgotPassword(membersVO5);*/
		
		// select 情境六：會員查詢會員等級 ( RANK_ID )
		/*MembersVO membersVO6 = new MembersVO();
		membersVO6.setMemberId(8);
		System.out.println(dao.selectRankId(membersVO6));*/
		
		// select 情境七：會員查詢錢包餘額 ( E_WALLET_AMOUNT )
		/*MembersVO membersVO7 = new MembersVO();
		membersVO7.setMemberId(17);
		System.out.println(dao.selectEWalletAmount(membersVO7));*/
		
		// select 情境八：會員查詢紅利帳戶 ( BONUS_AMOUNT	 )
		/*MembersVO membersVO8 = new MembersVO();
		membersVO8.setMemberId(20);
		System.out.println(dao.selectBonusAmount(membersVO8));*/
		
		// select 情境九：管理員使用 member_id 查詢某一筆會員資料
		/*MembersVO membersVO9 = new MembersVO();
		System.out.println(dao.getOneById(3));*/

		
		// select 情境十：管理員查詢所有會員資料
		/*for(MembersVO m1:dao.getAll()) {
			System.out.println(m);
		}*/
		
		// select 情境十一：管理員查詢所有被停權的所有會員 ( status )
		/*for(MembersVO m2:dao.getAllStatus()) {
			System.out.println(m2);
		}*/
		
		// select 情境十二：管理員使用 name 查詢某一筆會員資料
		/*MembersVO membersVO10 = new MembersVO();
		System.out.println(dao.getOneByName("閔皓"));*/
		
		// select 情境十三：查詢登入時帳號和密碼
		/*MembersVO membersVO10 = new MembersVO();
		System.out.println(dao.selectForLogin("閔皓","ooxxqaws"));*/
	
	}

}
