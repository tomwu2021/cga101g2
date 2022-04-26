package com.sort1.model;

import java.util.List;

public class TESTSort1MybatisDAO {
	public static void main(String[] args) {
		Sort1MybatisDAO dao = new Sort1MybatisDAO();

//新增
//		Sort1VO sort1VO = new Sort1VO();
//		sort1VO.setSort1Name("114449i");
//		dao.insert(sort1VO);

//修改
//		Sort1VO sort1VO = new Sort1VO();
//		sort1VO.setSort1Id(2);
//		sort1VO.setSort1Name("1131i");
//		dao.updateById(sort1VO);
		
//查詢所有
		List<Sort1VO> list = dao.selectAll();
		System.out.println(list);
		
//查詢是否重複
//		Sort1VO sort1VO1 = new Sort1VO();
//		sort1VO1.setSort1Name("KIKI");
//		sort1VO1 = dao.selectBySort1Name(sort1VO1.getSort1Name());
//		if(sort1VO1 !=null) {
//			System.out.println("分類重複");
//		}
		
	}
	

	
}
