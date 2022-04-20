package com.sort1.model;

import java.util.List;
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
		
	
		List<Sort1VO> list = dao.getAll();
		for (Sort1VO aSort1 : list) {
			System.out.print(aSort1.getSort1Id() + ",");
			System.out.print(aSort1.getSort1Name());
			System.out.println();
		}

	}
}
