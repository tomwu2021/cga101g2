package com.customer.service;

import java.util.List;

import com.customer.model.CustomerDAO_interface;
import com.customer.model.CustomerJDBCDAO;
import com.customer.model.CustomerVO;

public class CustomerService {

	private CustomerDAO_interface dao;

	public CustomerService() {
		dao = new CustomerJDBCDAO(); // TODO 換JNDI
	}

	/** 
	 * 新增一筆客戶問題
	 * @return CustomerVO(caseId, mailAdress, nickname, content)
	 */
	public CustomerVO addCustomer(String mailAdress, String nickname, String content) {
		CustomerVO custVO = new CustomerVO();
		custVO.setMailAddress(mailAdress);
		custVO.setNickname(nickname);
		custVO.setContent(content);
		dao.insert(custVO);
		return custVO;
	}

	/**
	 * 修改回覆狀態為已回覆，並押上改狀態的員工編號
	 * @return CustomerVO(replyStatus, empNo)
	 */
	public CustomerVO updateCustomer(Integer empNo, Integer caseId) {
		CustomerVO custVO = new CustomerVO();
		custVO.setEmpNo(empNo);
		custVO.setCaseId(caseId);
		dao.update(custVO);
		return custVO;
	}

	/**
	 * 查全部
	 * @return CustomerVO的list集合
	 */
	public List<CustomerVO> getAllCustomer(){
		return dao.getAll();
	}
	
	/**
	 * 查一筆
	 * @return CustomerVO的list集合
	 */
	public CustomerVO getByCustId(Integer caseId) {
		CustomerVO custVO = dao.getOneById(caseId);
		return custVO;
	}

	/**
	 * 查一email
	 * @return CustomerVO的list集合
	 */
	public List<CustomerVO> getByCustEmail(String emailAddress) {
		List<CustomerVO> custList = dao.getOneByEmail(emailAddress);
		return custList;
	}

	/**
	 * 以回覆狀態查
	 * @return CustomerVO的list集合
	 */
	public List<CustomerVO> getByReplyStatus(Integer status) {
		List<CustomerVO> custList = dao.getAllByStatus(status);
		return custList;
	}

	/**
	 * 以問題內容查
	 * @return CustomerVO的list集合
	 */
	public List<CustomerVO> getByKeyWord(String keyword) {
		List<CustomerVO> custList = dao.getAllByKeyword(keyword);
		return custList;
	}

}
