package com.sort_mix.model;

import java.util.List;

import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2VO;

public class TESTSortMixDAO {
	public static void main(String[] args) {
		
		SortMixJDBCDAO dao = new SortMixJDBCDAO();
		
		
		
		Sort1VO sort1 = dao.findAllBySort1Id(1);
		System.out.println(sort1.getSort1Name());
		List<Sort2VO> sort2List = sort1.getSort2VOList();
		for (Sort2VO sort2VO :sort2List) {
			System.out.println(sort2VO.getSort2Name());
		}
//		
//		
//		Sort2VO sort2 = dao.findAllBySort2Id(12);
//		System.out.println(sort2.getSort2Name());
//		List<Sort1VO> sort1List = sort2.getSort1VOList();
//		for (Sort1VO sort1VO :sort1List) {
//			System.out.println(sort1VO.getSort1Name());
//		}
		
		// 新增分類組合
//		SortMixVO sortMixVO1 = new SortMixVO();
//		sortMixVO1.setSort1Id(2);
//		sortMixVO1.setSort2Id(14);
//		dao.insert(sortMixVO1);

		// 刪除分類組合
//		SortMixVO sortMixVO2 = new SortMixVO();
//		sortMixVO2.setSort1Id(2);
//		sortMixVO2.setSort2Id(8);
//		dao.delete(sortMixVO2);

//		 查詢主分類與子分類所有組合
//		List<SortMixVO> list = dao.getAll();
//		for (SortMixVO smvs : list) {
//			System.out.print(smvs.getSort1Id() + ",");
//			System.out.print(smvs.getSort1Name()+ ",");
//			System.out.print(smvs.getSort2Id() + ",");
//			System.out.print(smvs.getSort2Name()+ ",");
//			System.out.println();
//		}

	}

}
