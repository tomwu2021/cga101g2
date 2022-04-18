package com.pet_activity.model;

import java.sql.Date;


public class TestPetActivity {

	public static void main(String[] args) {

		PetActivityJDBCDAO dao = new PetActivityJDBCDAO();
		
		// 新增
		PetActivityVO paVO1 = new PetActivityVO();
		paVO1.setPetId(1);
		paVO1.setActivity("去MOMO美容洗香香");
		paVO1.setRecordTime(Date.valueOf("2022-04-01"));
		System.out.println(dao.insert(paVO1));
		
		//
//
		
		// 刪除
//		PetActivityVO paVO2 = new PetActivityVO();
//		paVO2.setRecordId(4);
//		System.out.println(dao.delete(paVO2));
		
		// 修改
//		PetActivityVO paVO3 = new PetActivityVO();
//		paVO3.setActivity("X公園散步");
//		paVO3.setRecordTime(Date.valueOf("2022-04-13"));
//		paVO3.setRecordId(2);
//		System.out.println(dao.update(paVO3));

		// 查一筆
//		System.out.println(dao.getOneById(1));
		
		// 查一寵物
		System.out.println(dao.getOneByPetId(1));
		
		// 查全部
//		System.out.println(dao.getAll());		

	}
	
}
