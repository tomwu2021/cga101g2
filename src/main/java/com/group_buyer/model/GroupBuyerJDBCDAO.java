package com.group_buyer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group_order.model.GroupOrderVO;
import com.product.model.ProductVO;

import connection.JDBCConnection;

public class GroupBuyerJDBCDAO implements GroupBuyerDAO_Interface {

	@Override
	public GroupBuyerVO insert(GroupBuyerVO groupBuyerVO) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		String insertSql = "insert into group_buyer(group_order_id, member_id, product_amount, recipient, phone, address) "
				+ "values(?,?,?,?,?,?);";
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, groupBuyerVO.getGroupOrderId());
			ps.setInt(2, groupBuyerVO.getMemberId());
			ps.setInt(3, groupBuyerVO.getProductAmount());
			ps.setString(4, groupBuyerVO.getRecipients());
			ps.setString(5, groupBuyerVO.getPhone());
			ps.setString(6, groupBuyerVO.getAddress());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				groupBuyerVO.setGroupOrderId(rs.getInt(1));
			}
			return groupBuyerVO;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean delete(GroupBuyerVO groupBuyerVO) {

		return false;
	}

	@Override
	public GroupBuyerVO update(GroupBuyerVO groupBuyerVO) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		StringBuilder updateSql = new StringBuilder().append("update group_buyer set ");
		String recipient = groupBuyerVO.getRecipients();
		if (recipient != null && !recipient.isEmpty()) {
			updateSql.append("recipient = ?,");
		}
		String phone = groupBuyerVO.getPhone();
		if (phone != null && !phone.isEmpty()) {
			updateSql.append("phone = ?,");
		}
		String address = groupBuyerVO.getAddress();
		if (address != null && !address.isEmpty()) {
			updateSql.append("address = ?,");
		}
		updateSql.append("group_order_id=? ").append("where group_order_id=?  and member_id=?");
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(updateSql.toString());
			int offset = 1;

			if (recipient != null && !recipient.isEmpty()) {
				ps.setString(offset, groupBuyerVO.getRecipients());
				offset++;
			}

			if (phone != null && !phone.isEmpty()) {
				ps.setString(offset, groupBuyerVO.getPhone());
				offset++;
			}

			if (address != null && !address.isEmpty()) {
				ps.setString(offset, groupBuyerVO.getAddress());
				offset++;
			}
			ps.setInt(offset, groupBuyerVO.getGroupOrderId());
			offset++;
			ps.setInt(offset, groupBuyerVO.getGroupOrderId());
			offset++;
			ps.setInt(offset, groupBuyerVO.getMemberId());
			ps.executeUpdate();
			return groupBuyerVO;
		} catch (SQLException e) {
			// TODO: handle exception
			System.err.println("error");
		} catch (Exception e) {
			// TODO: handle exception

		}

		return groupBuyerVO;
	}

	@Override
	public GroupBuyerVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupBuyerVO> getAll() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllSql = "select group_order_id, member_id, product_amount, recipient, phone, address from group_buyer;";
		List<GroupBuyerVO> groupBuyerList = new ArrayList<GroupBuyerVO>();
		GroupBuyerVO groupBuyerVO = null;
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(getAllSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupBuyerVO = new GroupBuyerVO();
				groupBuyerVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupBuyerVO.setMemberId(rs.getInt("member_id"));
				groupBuyerVO.setAddress(rs.getString("product_amount"));
				groupBuyerVO.setPhone(rs.getString("recipient"));
				groupBuyerVO.setProductAmount(rs.getInt("phone"));
				groupBuyerVO.setRecipients(rs.getString("address"));
				groupBuyerList.add(groupBuyerVO);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupBuyerList;
	}

//	@Override
//	public List<GroupBuyerVO> getAllByMemberId(Integer id) {
//		// TODO Auto-generated method stub
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		String getAllByMemberIdSql = "select group_order_id, member_id, product_amount, recipient, phone, address from group_buyer "
//				+ "where member_id=?;";
//		List<GroupBuyerVO> groupBuyerList = new ArrayList<GroupBuyerVO>();
//		GroupBuyerVO groupBuyerVO = null;
//		try (Connection con = JDBCConnection.getRDSConnection()) {
//			ps = con.prepareStatement(getAllByMemberIdSql);
//			ps.setInt(1, id);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				groupBuyerVO = new GroupBuyerVO();
//				groupBuyerVO.setGroupOrderId(rs.getInt("group_order_id"));
//				groupBuyerVO.setMemberId(rs.getInt("member_id"));
//				groupBuyerVO.setAddress(rs.getString("address"));
//				groupBuyerVO.setPhone(rs.getString("phone"));
//				groupBuyerVO.setProductAmount(rs.getInt("product_amount"));
//				groupBuyerVO.setRecipients(rs.getString("recipient"));
//				groupBuyerList.add(groupBuyerVO);
//
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return groupBuyerList;
//	}

	@Override
	public List<GroupBuyerVO> getAllByMemberId(Integer id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllByGroupOrderIdSql = "select member_id,b.group_order_id,product_amount,p.product_id,final_price,o.status,p.product_name "
				+ "from group_buyer b " + "join group_order o on (b.group_order_id=o.group_order_id) "
				+ "join product p on(p.product_id=o.product_id) " + "where member_id=?";
		List<GroupBuyerVO> groupBuyerList = new ArrayList<GroupBuyerVO>();
		GroupBuyerVO groupBuyerVO = null;
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(getAllByGroupOrderIdSql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupBuyerVO = new GroupBuyerVO();
				groupBuyerVO.setMemberId(rs.getInt("member_id"));
				groupBuyerVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupBuyerVO.setProductAmount(rs.getInt("product_amount"));
				GroupOrderVO groupOrderVO = new GroupOrderVO();
				groupOrderVO.setProductId(rs.getInt("product_id"));
				groupOrderVO.setFinalPrice(Integer
						.valueOf((int) ((int) (rs.getInt("final_price")) * 0.9 * groupBuyerVO.getProductAmount())));
				groupOrderVO.setStatus(rs.getInt("status"));
				ProductVO productVO = new ProductVO();
				productVO.setProductName(rs.getString("product_name"));
				groupBuyerVO.setGroupOrderVO(groupOrderVO);
				groupBuyerVO.setProductVO(productVO);
				groupBuyerList.add(groupBuyerVO);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupBuyerList;
	}

	@Override
	public List<GroupBuyerVO> getAllByGroupOrderId(Integer id) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllByGroupOrderIdSql = "select group_order_id, member_id, product_amount, recipient, phone, address from group_buyer "
				+ "where group_order_id=?;";
		List<GroupBuyerVO> groupBuyerList = new ArrayList<GroupBuyerVO>();
		GroupBuyerVO groupBuyerVO = null;
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(getAllByGroupOrderIdSql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupBuyerVO = new GroupBuyerVO();
				groupBuyerVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupBuyerVO.setMemberId(rs.getInt("member_id"));
				groupBuyerVO.setAddress(rs.getString("address"));
				groupBuyerVO.setPhone(rs.getString("phone"));
				groupBuyerVO.setProductAmount(rs.getInt("product_amount"));
				groupBuyerVO.setRecipients(rs.getString("recipient"));
				groupBuyerList.add(groupBuyerVO);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupBuyerList;
	}

	@Override
	public void deleteByPK(Integer groupOrderId, Integer memberId) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		String deleteByPKSql = "DELETE FROM group_buyer WHERE group_order_id=? and member_id=?";
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(deleteByPKSql);
			ps.setInt(1, groupOrderId);
			ps.setInt(2, memberId);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public GroupBuyerVO selectByPK(Integer groupOrderId, Integer memberId) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllByGroupOrderIdSql = "select group_order_id, member_id, product_amount, recipient, phone, address from group_buyer "
				+ "where group_order_id=? and member_id=?;";
		GroupBuyerVO groupBuyerVO = null;
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(getAllByGroupOrderIdSql);
			ps.setInt(1, groupOrderId);
			ps.setInt(2, memberId);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupBuyerVO = new GroupBuyerVO();
				groupBuyerVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupBuyerVO.setMemberId(rs.getInt("member_id"));
				groupBuyerVO.setAddress(rs.getString("address"));
				groupBuyerVO.setPhone(rs.getString("phone"));
				groupBuyerVO.setProductAmount(rs.getInt("product_amount"));
				groupBuyerVO.setRecipients(rs.getString("recipient"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupBuyerVO;
	}
}
