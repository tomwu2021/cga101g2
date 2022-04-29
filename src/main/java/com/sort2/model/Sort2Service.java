package com.sort2.model;

import java.util.List;

public class Sort2Service implements Sort2Service_interface{

//	為什麼不直接寫 Sort1MybatisDAO dao  = new Sort1MybatisDAO();?
//	防止別隻CLASS檔經由NEW間接呼叫到DAO,必須要經過商業邏輯層Service的判斷才可以執行DAO?
	
//	private:僅類別內有效
	private Sort2JDBCDAO dao;

//	改寫預設建構子
	public Sort2Service() {
		dao = new Sort2JDBCDAO(); 
	}
	
	@Override
	public boolean save(Sort2VO sort2vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Sort2VO> findAll() {
		return dao.getAll();
	}

	@Override
	public boolean updateB(Sort2VO sort2vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sort2VO getOneById(Integer sort2Id) {
		return dao.getOneById(sort2Id);
	}

}
