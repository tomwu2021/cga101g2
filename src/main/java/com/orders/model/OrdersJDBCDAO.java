package com.orders.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.members.model.MembersVO;

import connection.JDBCConnection;

public class OrdersJDBCDAO implements OrdersDAO_Interface{
		
	@Override
	
	public OrdersVO insert(OrdersVO ordersVO) {
		// TODO Auto-generated method stub		
		Connection con = JDBCConnection.getRDSConnection();
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
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getOneByIdSql="select order_id, member_id, recipient, phone, address, "
				+ "sum_price, bonus, discount, pay_price, status, create_time from orders "
				+ "where order_id=?;";	
		OrdersVO ordersVO=null;
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(getOneByIdSql, Statement.RETURN_GENERATED_KEYS,ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				ordersVO=new OrdersVO();
				ordersVO.setOrderId(rs.getInt("order_id"));
				ordersVO.setMemberId(rs.getInt("member_id"));
				ordersVO.setRecipient(rs.getString("recipient"));
				ordersVO.setPhone(rs.getString("phone"));
				ordersVO.setAddress(rs.getString("address"));
				ordersVO.setSumPrice(rs.getInt("sum_price"));
				ordersVO.setBonus(rs.getInt("bonus"));
				ordersVO.setDiscount(rs.getInt("discount"));
				ordersVO.setPayPrice(rs.getInt("pay_price"));
				ordersVO.setStatus(rs.getInt("status"));
				ordersVO.setCreateTime(rs.getTimestamp("create_time"));	
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ordersVO;
	}

	@Override
	public List<OrdersVO> getAll() {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getAllSql = "select order_id, member_id, recipient, phone, address, "
				+ "sum_price, bonus, discount, pay_price, status, create_time from orders;";
		List<OrdersVO> ordersList=new ArrayList<OrdersVO>();
		OrdersVO ordersVO=null;
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(getAllSql, Statement.RETURN_GENERATED_KEYS,ResultSet.CONCUR_READ_ONLY);
			rs=ps.executeQuery();
			while(rs.next()) {
				ordersVO=new OrdersVO();
				ordersVO.setOrderId(rs.getInt("order_id"));
				ordersVO.setMemberId(rs.getInt("member_id"));
				ordersVO.setRecipient(rs.getString("recipient"));
				ordersVO.setPhone(rs.getString("phone"));
				ordersVO.setAddress(rs.getString("address"));
				ordersVO.setSumPrice(rs.getInt("sum_price"));
				ordersVO.setBonus(rs.getInt("bonus"));
				ordersVO.setDiscount(rs.getInt("discount"));
				ordersVO.setPayPrice(rs.getInt("pay_price"));
				ordersVO.setStatus(rs.getInt("status"));
				ordersVO.setCreateTime(rs.getTimestamp("create_time"));
				ordersList.add(ordersVO);			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ordersList;
	}

	@Override
	public boolean delete(OrdersVO t) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<OrdersVO> getOneByMemberId(Integer id) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getOneByMemberIdSql = "select order_id, member_id, recipient, phone, address, "
				+ "sum_price, bonus, discount, pay_price, status, create_time from orders "
				+ "where member_id=?;";
		List<OrdersVO> ordersList=new ArrayList<OrdersVO>();
		OrdersVO ordersVO=null;
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(getOneByMemberIdSql, Statement.RETURN_GENERATED_KEYS,ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				ordersVO=new OrdersVO();
				ordersVO.setOrderId(rs.getInt("order_id"));
				ordersVO.setMemberId(rs.getInt("member_id"));
				ordersVO.setRecipient(rs.getString("recipient"));
				ordersVO.setPhone(rs.getString("phone"));
				ordersVO.setAddress(rs.getString("address"));
				ordersVO.setSumPrice(rs.getInt("sum_price"));
				ordersVO.setBonus(rs.getInt("bonus"));
				ordersVO.setDiscount(rs.getInt("discount"));
				ordersVO.setPayPrice(rs.getInt("pay_price"));
				ordersVO.setStatus(rs.getInt("status"));
				ordersVO.setCreateTime(rs.getTimestamp("create_time"));
				ordersList.add(ordersVO);			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ordersList;
	}


	@Override
	public Integer updateStatusByOrderId(Integer id,Integer status) {
		PreparedStatement ps=null;
		String updateStatusByOrderIdSql="UPDATE orders SET status=? WHERE order_id=?;";	
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(updateStatusByOrderIdSql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
		
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return id;
	}




}
