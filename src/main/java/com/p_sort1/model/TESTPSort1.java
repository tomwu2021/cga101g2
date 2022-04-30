package com.p_sort1.model;

import java.util.List;

import com.sort1.model.Sort1VO;

public class TESTPSort1 {

	
	public static void main(String[] args) {
		PSort1JDBCDAO dao = new PSort1JDBCDAO();

		//新增產品跟對應的主分類組合
		PSort1VO pSort1vo = new PSort1VO();
		pSort1vo.setProductId(1);
		pSort1vo.setSort1Id(2);
		dao.insert(pSort1vo);
		
		// 查詢單種的商品的主分類"集合"要有名字的 (productId > sort1VOlist)
		List<Sort1VO> Sort1VOlist = dao.findSort1VOByproductId(70);		
		for (Sort1VO smvs : Sort1VOlist) {
			System.out.print(smvs.getSort1Id() + ",");
			System.out.print(smvs.getSort1Name()+ ",");
			System.out.println();
		}
	}

}
