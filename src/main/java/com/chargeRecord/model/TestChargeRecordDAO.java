package com.chargeRecord.model;

public class TestChargeRecordDAO {

	public static void main(String[] args) {

		ChargeRecordJDBCDAO dao = new ChargeRecordJDBCDAO();

		// 情境一 insert：會員儲值成功後，新增一筆儲值紀錄，並將金額加入到 member 表格中的 E_WALLET_AMOUNT
		ChargeRecordVO chargeRecordVO1 = new ChargeRecordVO();
		chargeRecordVO1.setMemberId(3);
		chargeRecordVO1.setChargeAmount(3000);
		System.out.println(dao.insert(chargeRecordVO1));

	}

}
