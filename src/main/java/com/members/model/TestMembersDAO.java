package com.members.model;

public class TestMembersDAO {

	public static void main(String[] args) {
		MembersJDBCDAO dao = new MembersJDBCDAO();

		// insert 情境一：會員辦帳號時，輸入資料庫的內容
		/*MembersVO membersVO1 = new MembersVO();
		membersVO1.setAccount("Redis@pet.com"); // 動態
		membersVO1.setPassword("!QAZ2wsx"); // 動態
		dao.insert(membersVO1);
		System.out.println(membersVO1);*/
		

		// update 情境二：會員可修改的資料　PASSWORD	NAME　ADDRESS　PHONE　E_WALLET_PASSWORD	
		/*MembersVO membersVO2 = new MembersVO();
		membersVO2.setMemberId(18);
		membersVO2.setPassword("!QAZ2wsx");
		membersVO2.setName("方炯冰");
		membersVO2.setAddress("花蓮縣豐濱鄉大港口20號");
		membersVO2.setPhone("0946825555");
		membersVO2.seteWalletPassword("773516");
		dao.update(membersVO2);*/

		// update 情境三：管理員可以修改此會員的狀態(停權/正常)
		/*MembersVO membersVO3 = new MembersVO();
		membersVO3.setMemberId(20);
		membersVO3.setStatus(0);
		dao.changeStatus(membersVO3);*/
		
		// update 情境四：管理員可以發送紅利
		/*MembersVO membersVO4 = new MembersVO();
		membersVO4.setMemberId(20);
		membersVO4.setBonusAmount(222);
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
		MembersVO membersVO8 = new MembersVO();
		membersVO8.setMemberId(20);
		System.out.println(dao.selectBonusAmount(membersVO8));
		
		// select 情境九：管理員查詢某一筆會員資料 (透過 account)
		
		// select 情境十：管理員查詢所有會員資料
		
		// select 情境十一：管理員查詢所有被停權的所有會員 ( status )
		
		
	}

}
