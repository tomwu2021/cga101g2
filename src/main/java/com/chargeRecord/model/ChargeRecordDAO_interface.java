package com.chargeRecord.model;

import java.util.List;
import com.common.model.JDBCDAO_Interface;

public interface ChargeRecordDAO_interface extends JDBCDAO_Interface<ChargeRecordVO> {

	// 情境三　select：管理員查詢某會員所有儲值與消費紀錄 
	public List<ChargeRecordVO> getAll(Integer id);
	
	// 用 memberId 查詢某會員累積儲值金額
	public Integer SumChargeAmount(Integer id);
}
