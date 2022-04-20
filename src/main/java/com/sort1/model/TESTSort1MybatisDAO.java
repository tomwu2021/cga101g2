package com.sort1.model;

import java.util.List;

public class TESTSort1MybatisDAO {
	public static void main(String[] args) {
		Sort1MybatisDAO dao = new Sort1MybatisDAO();

//新增
//		Sort1VO sort1VO = new Sort1VO();
//		sort1VO.setSort1Name("11331i");
//		dao.insert(sort1VO);

//更新
//		Sort1VO sort1VO = new Sort1VO();
//		sort1VO.setSort1Id(77);
//		sort1VO.setSort1Name("11331i");
//		dao.updateById(sort1VO);
		
//查詢所有
		List<Sort1VO> list = dao.selectAll();
		System.out.println(list);
		
//查詢此Sort1Name是否已有資料,UQ
//		Sort1VO sort1VO1 = new Sort1VO();
//		sort1VO1.setSort1Name("KI666KI");
//		sort1VO1 = dao.selectBySort1Name(sort1VO1.getSort1Name());
//		if(sort1VO1 !=null) {
//			System.out.println("欄位重複");
//		}
	}
	
//	public void test()  {
//		Sort1VO sort1VO1 = new Sort1VO();
//		sort1VO1.setSort1Name("KI666KI");
//	}
	
}
