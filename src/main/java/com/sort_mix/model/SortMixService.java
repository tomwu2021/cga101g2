package com.sort_mix.model;

import java.util.ArrayList;
import java.util.List;

import com.common.model.MappingTableDto;
import com.google.gson.Gson;
import com.sort1.model.Sort1JDBCDAO;
import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2VO;


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
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Sort1VO> getAll() {
//		呼叫Sort1service工頭計算筆數	
		Sort1JDBCDAO sort1Dao = new Sort1JDBCDAO();
		int i = sort1Dao.getSort1Count();
			System.out.println(i);
			
//		呼叫中間實現表SortMix工頭計算一對多(某種主類別有多少子類別)	
		SortMixJDBCDAO sortMixDao = new SortMixJDBCDAO();
//     新增集合
//		SortMaxAllList一個物件裡有兩個主分類"集合",
//		主分類"集合"下有各自的子分類"集合"
		ArrayList<Sort1VO> SortMaxAllList = new ArrayList<Sort1VO>();
		for(int a =1; a<=i; a++) {
			Sort1VO sort1vo = sortMixDao.findAllBySort1Id(a);
			SortMaxAllList.add(sort1vo);
		}

		System.out.println(SortMaxAllList);
		 Gson gson = new Gson();
	     String json = gson.toJson(SortMaxAllList);
	     System.out.println(json);
	     
		return SortMaxAllList;
	}

	
	public boolean update(MappingTableDto mtd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sort1VO findAllBySort1Id(Integer sort1Id) {
		return dao.findAllBySort1Id(sort1Id);
	}

	@Override
	public Sort2VO findAllBySort2Id(Integer sort2Id) {
		return dao.findAllBySort2Id(sort2Id);
	}
}
