package com.sort2.model;

import java.util.List;

public class Sort2Service implements Sort2Service_interface{

//	為什麼不直接寫 Sort1MybatisDAO dao  = new Sort1MybatisDAO();?
//	防止別隻CLASS檔經由NEW間接呼叫到DAO,必須要經過商業邏輯層Service的判斷才可以執行DAO?
	
//	private:僅類別內有效
	private Sort2JNDIDAO dao;

//	改寫預設建構子
	public Sort2Service() {
		dao = new Sort2JNDIDAO(); 
	}
	
	@Override
	public boolean save(Sort2VO sort2vo) {
		return false;
	}

	@Override
	public List<Sort2VO> getAll() {
		return dao.getAll();
	}

	@Override
	public boolean updateB(Sort2VO sort2vo) {
		return false;
	}

	@Override
	public Sort2VO getOneById(Integer sort2Id) {
		return dao.getOneById(sort2Id);
	}
	
	public boolean delete(Sort2VO sort2VO) {
		return dao.delete(sort2VO);
	}
	public Sort2VO selectBySort2Name(String sort2Name) {
		return dao.selectBySort2Name(sort2Name);
	}

	public Sort2VO insert(Sort2VO sort2VO) {
		return dao.insert(sort2VO);
	}
}
