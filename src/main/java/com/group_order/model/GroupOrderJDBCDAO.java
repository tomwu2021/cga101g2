package com.group_order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.JDBCConnection;

public class GroupOrderJDBCDAO implements GroupOrderDAO_Interface{

	@Override
	public GroupOrderVO insert(GroupOrderVO groupOrderVO) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		ResultSet rs=null;
		//建立時訂單狀態為進行中
		String insertSql="insert into group_order(product_id, end_time, end_type, status) "
				+ "values(?,date_add(now(), interval 7 day),?,0);";
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, groupOrderVO.getProductId());
			ps.setInt(2, groupOrderVO.getEndType());
			ps.executeUpdate();
			rs=ps.getGeneratedKeys();
			if (rs.next()) {
				groupOrderVO.setGroupOrderId(rs.getInt(1));
			}
			return groupOrderVO;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean delete(GroupOrderVO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GroupOrderVO update(GroupOrderVO groupOrderVO) {
		
		// TODO Auto-generated method stub
		return null;

	}

	@Override
	public GroupOrderVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getOneByIdSql="select group_order_id, product_id, create_time, end_time, end_type, final_price, status from group_order "
				+ "where group_order_id=?;";
		GroupOrderVO groupOrderVO=null;
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(getOneByIdSql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				groupOrderVO=new GroupOrderVO();
				groupOrderVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupOrderVO.setProductId(rs.getInt("product_id"));
				groupOrderVO.setCreateTime(rs.getTimestamp("create_time"));
				groupOrderVO.setEndTime(rs.getTimestamp("end_time"));
				groupOrderVO.setEndType(rs.getInt("end_type"));
				groupOrderVO.setFinalPrice(rs.getInt("final_price"));
				groupOrderVO.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return groupOrderVO;
	}

	@Override
	public List<GroupOrderVO> getAll() {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getAllSql="select group_order_id, product_id, create_time, end_time, end_type, final_price, status from group_order;";
		List<GroupOrderVO> groupOrderList=new ArrayList<GroupOrderVO>();
		GroupOrderVO groupOrderVO=null;
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(getAllSql);
			rs=ps.executeQuery();
			while(rs.next()) {
				groupOrderVO=new GroupOrderVO();
				groupOrderVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupOrderVO.setProductId(rs.getInt("product_id"));
				groupOrderVO.setCreateTime(rs.getTimestamp("create_time"));
				groupOrderVO.setEndTime(rs.getTimestamp("end_time"));
				groupOrderVO.setEndType(rs.getInt("end_type"));
				groupOrderVO.setFinalPrice(rs.getInt("final_price"));
				groupOrderVO.setStatus(rs.getInt("status"));
				groupOrderList.add(groupOrderVO);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupOrderList;
	}

	@Override
	public List<GroupOrderVO> getAllByProductId(Integer id) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getAllByProductIdSql="select group_order_id, product_id, create_time, end_time, end_type, final_price, status from group_order "
				+ "where product_id=?";
		GroupOrderVO groupOrderVO=null;
		List<GroupOrderVO> groupOrderList=new ArrayList<GroupOrderVO>();
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(getAllByProductIdSql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				groupOrderVO=new GroupOrderVO();
				groupOrderVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupOrderVO.setProductId(rs.getInt("product_id"));
				groupOrderVO.setCreateTime(rs.getTimestamp("create_time"));
				groupOrderVO.setEndTime(rs.getTimestamp("end_time"));
				groupOrderVO.setEndType(rs.getInt("end_type"));
				groupOrderVO.setFinalPrice(rs.getInt("final_price"));
				groupOrderVO.setStatus(rs.getInt("status"));
				groupOrderList.add(groupOrderVO);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupOrderList;
	}

	@Override
	public Integer updateEndTimeByGroupOrderId(Integer id) {
		PreparedStatement ps=null;
		String updateStatusByGroupOrderIdSqlSql="UPDATE group_order SET end_time=now() WHERE group_order_id=?;";	
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(updateStatusByGroupOrderIdSqlSql);
			ps.setInt(1, id);
			ps.executeUpdate();		
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return id;
	}

	@Override
	public Integer updateStatusByGroupOrderId(Integer id, Integer status) {
		PreparedStatement ps=null;
		String updateStatusByGroupOrderIdSql="UPDATE group_order SET status=? WHERE group_order_id=?;";
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(updateStatusByGroupOrderIdSql);
			ps.setInt(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();	
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return id;
	}

	@Override
	public List<GroupOrderVO> getAllInProgressByProductId(Integer id) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getAllByProductIdSql="select group_order_id, product_id, create_time, end_time, end_type, final_price, status from group_order "
				+ "where product_id=? and status=0";
		GroupOrderVO groupOrderVO=null;
		List<GroupOrderVO> groupOrderList=new ArrayList<GroupOrderVO>();
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(getAllByProductIdSql);
			ps.setInt(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				groupOrderVO=new GroupOrderVO();
				groupOrderVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupOrderVO.setProductId(rs.getInt("product_id"));
				groupOrderVO.setCreateTime(rs.getTimestamp("create_time"));
				groupOrderVO.setEndTime(rs.getTimestamp("end_time"));
				groupOrderVO.setEndType(rs.getInt("end_type"));
				groupOrderVO.setFinalPrice(rs.getInt("final_price"));
				groupOrderVO.setStatus(rs.getInt("status"));
				groupOrderList.add(groupOrderVO);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupOrderList;
	}

	@Override
	public List<GroupOrderVO> getAllInProgress() {
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getAllByProductIdSql="select group_order_id, product_id, create_time, end_time, end_type, final_price, status from group_order "
				+ "where status=0";
		GroupOrderVO groupOrderVO=null;
		List<GroupOrderVO> groupOrderList=new ArrayList<GroupOrderVO>();
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(getAllByProductIdSql);
			rs=ps.executeQuery();
			while(rs.next()) {
				groupOrderVO=new GroupOrderVO();
				groupOrderVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupOrderVO.setProductId(rs.getInt("product_id"));
				groupOrderVO.setCreateTime(rs.getTimestamp("create_time"));
				groupOrderVO.setEndTime(rs.getTimestamp("end_time"));
				groupOrderVO.setEndType(rs.getInt("end_type"));
				groupOrderVO.setFinalPrice(rs.getInt("final_price"));
				groupOrderVO.setStatus(rs.getInt("status"));
				groupOrderList.add(groupOrderVO);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupOrderList;
	}

}
