package com.members.model;

public class MembersTestDAO {

	public static void main(String[] args) {
		MembersJDBCDAO dao = new MembersJDBCDAO();

		// 情境一 insert：會員辦帳號時，輸入資料庫的內容
		MembersVO membersVO1 = new MembersVO();
		membersVO1.setAccount("Android@pet.com"); // 動態
		membersVO1.setPassword("!QAZ2wsx"); // 動態
		dao.insert(membersVO1);
		System.out.println(membersVO1);

		// 情境二 update：會員可修改的資料　PASSWORD	NAME　ADDRESS　PHONE　E_WALLET_PASSWORD	
//		MembersVO membersVO2 = new MembersVO();
//		membersVO2.setPassword("!QAZ2wsx");
//		membersVO2.setName("許崇列");
//		membersVO2.setAddress("基隆市仁愛區仁四路196號");
//		membersVO2.setPhone("0955524761");
//		membersVO2.seteWalletPassword("344562");
//		dao.update(membersVO2);

	}

}
