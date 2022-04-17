package com.customer.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;


public interface CustomerDAO_interface extends JDBCDAO_Interface<CustomerVO>{

/** 客戶提出問題：
 * (必)mailAddress, nickname, content */
	public CustomerVO insert(CustomerVO customerVO);
/** @return false */
	public boolean delete(CustomerVO customerVO);
/** 回覆客戶問題：
 * (必)caseId,empNo */
    public CustomerVO update(CustomerVO customerVO);
/** 查詢單筆客戶問題：
 * (必)caseId */
    public CustomerVO getOneById(Integer id);
/** 依email查詢一客戶所有問題：
 * (必)mailAddress */
    public List<CustomerVO> getOneByEmail(String mailAddress);
/** 查詢所有客戶問題 */
    public List<CustomerVO> getAll();
/** 依狀態查詢所有問題：
 * (必)replyStatus[0-未回覆,1-已回覆] */
    public List<CustomerVO> getAllByStatus(Integer replyStatus);
/** 依內容查詢所有問題：
 * (必)[關鍵字詞] */
    public List<CustomerVO> getAllByKeyword(String keyword);
}
