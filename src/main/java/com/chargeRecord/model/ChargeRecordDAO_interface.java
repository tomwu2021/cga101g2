package com.chargeRecord.model;

import java.util.List;
import com.common.model.JDBCDAO_Interface;

public interface ChargeRecordDAO_interface extends JDBCDAO_Interface<ChargeRecordVO> {

	// 情境三　select：管理員查詢某會員所有儲值與消費紀錄 
	public List<ChargeRecordVO> getAll(Integer id);
}
