package com.p_sort1.model;

import java.util.List;

import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2VO;

public class TESTPSort1 {

	
	public static void main(String[] args) {
		PSort1JDBCDAO dao = new PSort1JDBCDAO();

		// 查詢單種的商品的主分類"集合"要有名字的 (productId > sort1VOlist)
		List<Sort1VO> Sort1VOlist = dao.findSort1VOByproductId(70);		
		for (Sort1VO smvs : Sort1VOlist) {
			System.out.print(smvs.getSort1Id() + ",");
			System.out.print(smvs.getSort1Name()+ ",");
			System.out.println();
		}
	}

}
