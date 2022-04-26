package com.sort1.model;

import java.util.List;

public class Sort1Service implements Sort1Service_interface{
//	為什麼不直接寫 Sort1MybatisDAO dao  = new Sort1MybatisDAO();?
//	防止別隻CLASS檔經由NEW間接呼叫到DAO,必須要經過商業邏輯層Service的判斷才可以執行DAO?
	
//	private:僅類別內有效
	private Sort1MybatisDAO dao;

//	改寫預設建構子
	public Sort1Service() {
		dao = new Sort1MybatisDAO(); 
	}

	@Override
	public boolean save(Sort1VO sort1vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Sort1VO> findAll() {
		return dao.selectAll();
	}

	@Override
	public boolean updateB(Sort1VO sort1vo) {
		// TODO Auto-generated method stub
		return false;
	}

}
