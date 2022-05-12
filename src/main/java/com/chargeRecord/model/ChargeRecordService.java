package com.chargeRecord.model;

import java.util.*;

public class ChargeRecordService {

	private ChargeRecordDAO_interface dao;

	public ChargeRecordService() {
		dao = new ChargeRecordDAO();
	}

	// 情境一 insert：會員儲值或消費成功後，新增一筆儲值紀錄，並將金額加入到 member 表格中的 E_WALLET_AMOUNT
	public ChargeRecordVO insert(ChargeRecordVO chargeRecordVO) {
		return dao.insert(chargeRecordVO);
	}

	// 情境二 select：管理員查詢所有儲值與消費紀錄
	public List<ChargeRecordVO> getAll() {
		return dao.getAll();
	}

	// 情境三 select：管理員查詢某會員所有儲值與消費紀錄
	public List<ChargeRecordVO> getAll(Integer id) {
		return dao.getAll(id);
	}
}
