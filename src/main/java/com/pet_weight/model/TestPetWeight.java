package com.pet_weight.model;

import java.math.BigDecimal;
import java.sql.Date;


public class TestPetWeight {

	public static void main(String[] args) {

		PetWeightJDBCDAO dao = new PetWeightJDBCDAO();
		
		// 新增
		PetWeightVO pwVO1 = new PetWeightVO();
		pwVO1.setPetId(1);
		pwVO1.setWeightRecord(new BigDecimal(2));
		pwVO1.setRecordTime(Date.valueOf("2022-01-03"));
		System.out.println(dao.insert(pwVO1));
		
		// 刪除
//		PetWeightVO paVO2 = new PetWeightVO();
//		paVO2.setRecordId(5);
//		System.out.println(dao.delete(paVO2));
		
		// 修改
//		PetWeightVO paVO3 = new PetWeightVO();
//		paVO3.setWeightRecord(new BigDecimal(5));
//		paVO3.setRecordTime(Date.valueOf("2022-04-06"));
//		paVO3.setRecordId(4);
//		System.out.println(dao.update(paVO3));

		// 查一筆
//		System.out.println(dao.getOneById(3));
		
		// 查一寵物
//		System.out.println(dao.getOneByPetId(1));
		
		// 查一寵物最近一筆
//		System.out.println(dao.getRecentWeight(2));	
		
		// 查一寵物體重紀錄(季平均)
		System.out.println(dao.getAverageWeight(1));
		
		// 查全部
//		System.out.println(dao.getAll());

	}
	
}
