package com.p_sort1.model;

import java.util.List;

import com.sort1.model.Sort1VO;

public class PSort1Service implements PSort1DAO_interface {

//	private:僅類別內有效
	private PSort1JDBCDAO dao;

//	改寫預設建構子
	public PSort1Service() {
		dao = new PSort1JDBCDAO(); 
	}
	
	@Override
	public PSort1VO insert(PSort1VO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(PSort1VO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PSort1VO update(PSort1VO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PSort1VO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PSort1VO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sort1VO> findSort1VOByproductId(Integer productId) {
		return dao.findSort1VOByproductId(productId);
	}

}