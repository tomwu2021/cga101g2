package com.sort_mix.service;

import java.util.List;

import com.common.model.MappingTableDto;
import com.sort_mix.model.SortMixJDBCDAO;
import com.sort_mix.model.SortMixMapVO;


public class SortMixService implements SortMixService_interface{
//	為什麼不直接寫 Sort1MybatisDAO dao  = new Sort1MybatisDAO();?
//	防止別隻CLASS檔經由NEW間接呼叫到DAO,必須要經過商業邏輯層Service的判斷才可以執行DAO?
	
//	private:僅類別內有效
	private SortMixJDBCDAO dao;

//	改寫預設建構子
	public SortMixService() {
		dao = new SortMixJDBCDAO(); 
	}

	@Override
	public boolean save(MappingTableDto mtd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<SortMixMapVO> getAll() {
		return dao.findAll();
	}

	@Override
	public boolean update(MappingTableDto mtd) {
		// TODO Auto-generated method stub
		return false;
	}

}
