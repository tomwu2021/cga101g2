package com.p_sort1.model;

import java.util.List;

import com.sort1.model.Sort1VO;

public class PSort1Service implements PSort1DAO_interface {

//	private:僅類別內有效
	private PSort1JNDIDAO dao;

//	改寫預設建構子
	public PSort1Service() { 
		dao = new PSort1JNDIDAO(); 
	}
	
	@Override
	public PSort1VO insert(PSort1VO pSort1VO) {
		return dao.insert(pSort1VO);
	}

	@Override
	public boolean delete(PSort1VO t) {
		return false;
	}

	@Override
	public PSort1VO update(PSort1VO pSort1VO) {
		return null;
	}

	@Override
	public PSort1VO getOneById(Integer id) {
		return null;
	}

	@Override
	public List<PSort1VO> getAll() {
		return null;
	}

	@Override
	public List<Sort1VO> findSort1VOByproductId(Integer productId) {
		return dao.findSort1VOByproductId(productId);
	}

	@Override
	public boolean deletePSort1sByProductId(Integer productId) {
		return dao.deletePSort1sByProductId(productId);
	}

}
