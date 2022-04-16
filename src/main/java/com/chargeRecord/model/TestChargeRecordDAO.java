package com.chargeRecord.model;

public class TestChargeRecordDAO {

	public static void main(String[] args) {

		ChargeRecordJDBCDAO dao = new ChargeRecordJDBCDAO();

		// 情境一 insert：會員儲值或消費成功後，新增一筆儲值紀錄，並將金額加入到 member 表格中的 E_WALLET_AMOUNT
//		ChargeRecordVO chargeRecordVO1 = new ChargeRecordVO();
//		chargeRecordVO1.setMemberId(1);
//		chargeRecordVO1.setChargeAmount(-1500);
//		System.out.println(dao.insert(chargeRecordVO1));
		
		// 情境二 select：查詢所有儲值與消費紀錄 
//		for (ChargeRecordVO cr : dao.getAll()) {
//			System.out.println(cr);
//		}
		
		// 情境三　select：查詢某會員所有儲值與消費紀錄 
//		for (ChargeRecordVO cr : dao.getAll(3)) {
//			System.out.println(cr);
//		}
	
	}

}
