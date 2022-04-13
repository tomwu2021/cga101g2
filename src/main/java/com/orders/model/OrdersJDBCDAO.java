package com.orders.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.members.model.MembersVO;

import connection.JDBCConnection;

public class OrdersJDBCDAO implements OrdersDAO_Interface{
	
	Connection con;
	
	@Override
	
	public OrdersVO insert(OrdersVO ordersVO) {
		// TODO Auto-generated method stub		
		con = JDBCConnection.getRDSConnection();
		OrdersVO ordersVO2=insert(ordersVO,con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordersVO2;
	}
	
	
	public OrdersVO insert(OrdersVO ordersVO,Connection con) {
		// TODO Auto-generated method stub		
		String insertSql = "insert into orders(member_id, recipient, phone, address, sum_price, bonus, discount, pay_price, status) "
				+ "values(?,?,?,?,?,?,?,?,0);";
		if(con!=null) {
			try{
				PreparedStatement ps=con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, ordersVO.getMemberId());
				ps.setString(2, ordersVO.getRecipient());
				ps.setString(3, ordersVO.getPhone());
				ps.setString(4, ordersVO.getAddress());
				ps.setInt(5, ordersVO.getSumPrice());
				ps.setInt(6, ordersVO.getBonus());
				ps.setInt(7, ordersVO.getDiscount());
				ps.setInt(8, ordersVO.getPayPrice());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					ordersVO.setOrderId(rs.getInt(1));
				}
				return ordersVO;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}		
		return null;
	}


	@Override
	public OrdersVO update(OrdersVO ordersVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdersVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdersVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(OrdersVO t) {
		// TODO Auto-generated method stub
		return false;
	}

}
