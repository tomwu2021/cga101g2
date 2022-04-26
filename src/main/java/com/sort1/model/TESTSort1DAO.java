package com.sort1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.sort_mix.model.SortMixJDBCDAO;

public class TESTSort1DAO {

	public static void main(String[] args) {

		Sort1JDBCDAO dao = new Sort1JDBCDAO();

		// 新增
//		Sort1VO sort1VO1 = new Sort1VO();
//		sort1VO1.setSort1Name("SA1331LA");
//		dao.insert(sort1VO1);

		// 修改
//		Sort1VO sort1VO2 = new Sort1VO();
//		sort1VO2.setSort1Name("33443");
//		sort1VO2.setSort1Id(2);
//		dao.update(sort1VO2);
		
	
//		List<Sort1VO> list = dao.getAll();
//		for (Sort1VO aSort1 : list) {
//			System.out.print(aSort1.getSort1Id() + ",");
//			System.out.print(aSort1.getSort1Name());
//			System.out.println();
//		}
		
		//查詢是否重複
//		Sort1VO sort1VO1 = new Sort1VO();
//		sort1VO1.setSort1Name("KIKI");
//		sort1VO1 = dao.selectBySort1Name(sort1VO1.getSort1Name());
//		if(sort1VO1 !=null) {
//			System.out.println("分類重複");
//		}
		
//		計算筆數	
		int i = dao.getSort1Count();
			System.out.println(i);
			
		SortMixJDBCDAO adao = new SortMixJDBCDAO();
		ArrayList<Sort1VO> SortMaxAllList = new ArrayList<Sort1VO>();
		for(int a =1; a<=i; a++) {
			Sort1VO sort1vo = adao.findAllBySort1Id(a);
			SortMaxAllList.add(sort1vo);
		}
		System.out.println(SortMaxAllList);
		 Gson gson = new Gson();
	     String json = gson.toJson(SortMaxAllList);
	     System.out.println(json);

	}
}
