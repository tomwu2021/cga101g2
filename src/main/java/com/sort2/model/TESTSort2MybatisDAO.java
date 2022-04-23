package com.sort2.model;

public class TESTSort2MybatisDAO {
	public static void main(String[] args) {
		Sort2MybatisDAO dao = new Sort2MybatisDAO();

//新增
//		Sort2VO Sort2VO = new Sort2VO();
//		Sort2VO.setSort2Name("NULL");
//		dao.insert(Sort2VO);

//修改
//		Sort2VO Sort2VO = new Sort2VO();
//		Sort2VO.setSort2Id(18);
//		Sort2VO.setSort2Name("5555");
//		dao.updateById(Sort2VO);
//		
//查詢所有
//		List<Sort2VO> list = dao.selectAll();
//		System.out.println(list);

//查詢是否重複
		Sort2VO Sort2VO1 = new Sort2VO();
		Sort2VO1.setSort2Name("222");
		Sort2VO1 = dao.selectBySort2Name(Sort2VO1.getSort2Name());
		if (Sort2VO1 != null) {
			System.out.println("分類重複");
		}
	}
}
