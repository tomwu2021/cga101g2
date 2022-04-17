package com.sort_mix.model;

import java.util.List;

public class TESTSortMixDAO {
	public static void main(String[] args) {
		SortMixJDBCDAO dao = new SortMixJDBCDAO();

		// 新增分類組合
		SortMixVO sortMixVO1 = new SortMixVO();
		sortMixVO1.setSort1Id(2);
		sortMixVO1.setSort2Id(14);
		dao.insert(sortMixVO1);

		// 刪除分類組合
//		SortMixVO sortMixVO2 = new SortMixVO();
//		sortMixVO2.setSort1Id(2);
//		sortMixVO2.setSort2Id(8);
//		dao.delete(sortMixVO2);
		
		//查詢主分類與子分類所有組合
		List<SortMixVO> list = dao.getAll();
		for (SortMixVO asortMixVO : list) {
			System.out.print(asortMixVO.getSort1Id() + ",");
			System.out.print(asortMixVO.getSort2Id());
			System.out.println();
		}

	}
}
