package com.sort_max.model;

import java.util.List;

import com.sort2.model.Sort2JDBCDAO;
import com.sort2.model.Sort2VO;

public class TEST {

	public static void main(String[] args) {
		
		Sort2JDBCDAO Sort2JDBCDAO = new Sort2JDBCDAO();
		List<Sort2VO> list = Sort2JDBCDAO.getAll();
		for (Sort2VO aSort2 : list) {
			System.out.print(aSort2.getSort2_id() + ",");
			System.out.print(aSort2.getSort2_name());
			System.out.println();
		}
	}
}
