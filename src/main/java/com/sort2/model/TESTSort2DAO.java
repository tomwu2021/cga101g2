package com.sort2.model;

import java.util.List;

public class TESTSort2DAO {
	public static void main(String[] args) {

		Sort2JDBCDAO dao = new Sort2JDBCDAO();

		// 新增
//		Sort2VO sort2VO1= new Sort2VO();
//		sort2VO1.setSort2Name("12=22=13");
//		dao.insert(sort2VO1);

//		//修改
//		Sort2VO sort2VO2 = new Sort2VO();
//		sort2VO2.setSort2Id(63);
//		sort2VO2.setSort2Name("663366");
//		dao.update(sort2VO2);
////		查詢所有
		List<Sort2VO> list = dao.getAll();
		for (Sort2VO aSort2 : list) {
			System.out.print(aSort2.getSort2Id() + ",");
			System.out.print(aSort2.getSort2Name());
			System.out.println();
		}

	}
}
