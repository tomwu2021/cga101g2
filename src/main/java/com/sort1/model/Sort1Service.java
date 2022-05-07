package com.sort1.model;

import java.util.List;

public class Sort1Service implements Sort1Service_interface{
//	為什麼不直接寫 Sort1MybatisDAO dao  = new Sort1MybatisDAO();?
//	防止別隻CLASS檔經由NEW間接呼叫到DAO,必須要經過商業邏輯層Service的判斷才可以執行DAO?
	
//	private:僅類別內有效
	private Sort1JNDIDAO dao;

//	改寫預設建構子
	public Sort1Service() {
		dao = new Sort1JNDIDAO(); 
	}

	@Override
	public boolean save(Sort1VO sort1vo) {
		return false;
	}

	@Override
	public List<Sort1VO> getAll() {
		return dao.getAll();
	}

	@Override
	public boolean updateB(Sort1VO sort1vo) {
		return false;
	}

	@Override
	public Sort1VO getOneById(Integer sort1Id) {
		return dao.getOneById(sort1Id);
	}
	

}
