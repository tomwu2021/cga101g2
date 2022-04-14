package com.prodouct.model;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProductJDBCDAO pdao =new ProductJDBCDAO();
		System.out.println(pdao.findByPrimaryKey(1));
	}

}
