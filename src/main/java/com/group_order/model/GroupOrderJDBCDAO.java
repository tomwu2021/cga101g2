package com.group_order.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.common.exception.JDBCException;
import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.group_buyer.model.GroupBuyerVO;
import com.picture.model.PictureVO;
import com.product.model.ProductVO;

import connection.JDBCConnection;

public class GroupOrderJDBCDAO implements GroupOrderDAO_Interface {

	@Override
	public GroupOrderVO insert(GroupOrderVO groupOrderVO) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 建立時訂單狀態為進行中
		String insertSql = "insert into group_order(product_id, end_time, end_type,final_price,status,min_amount) "
				+ "values(?,date_add(now(), interval 7 day),?,?,0,?);";
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, groupOrderVO.getProductId());
			ps.setInt(2, groupOrderVO.getEndType());
			ps.setInt(3, groupOrderVO.getFinalPrice());
			ps.setInt(4, groupOrderVO.getMinAmount());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllByProductIdSql = "select * from group_order o " + " join product p on(p.product_id=o.product_id) "
				+ " where o.group_order_id=? ";
		GroupOrderVO groupOrderVO = new GroupOrderVO();
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(getAllByProductIdSql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupOrderVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupOrderVO.setProductId(rs.getInt("o.product_id"));
				groupOrderVO.setStatus(rs.getInt("o.status"));
				groupOrderVO.setEndTime(rs.getTimestamp("end_time"));
				groupOrderVO.setEndType(rs.getInt("end_type"));
				groupOrderVO.setMinAmount(rs.getInt("min_amount"));
				ProductVO productVO = new ProductVO();
				productVO.setPrice(rs.getInt("price"));
				productVO.setProductName(rs.getString("product_name"));
				productVO.setGroupPrice1(rs.getInt("group_price1"));
				productVO.setGroupAmount1(rs.getInt("group_amount1"));
				productVO.setGroupAmount2(rs.getInt("group_amount2"));
				productVO.setGroupAmount3(rs.getInt("group_amount3"));
				productVO.setDescription(rs.getString("description"));
				productVO.setStatus(rs.getInt("p.status"));
				groupOrderVO.setProductVO(productVO);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return groupOrderVO;
	}

	@Override
	public List<GroupOrderVO> getAll() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllSql = "select group_order_id, product_id, create_time, end_time, end_type, final_price, status from group_order;";
		List<GroupOrderVO> groupOrderList = new ArrayList<GroupOrderVO>();
		GroupOrderVO groupOrderVO = null;
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(getAllSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupOrderVO = new GroupOrderVO();
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllByProductIdSql = "select group_order_id, product_id, create_time, end_time, end_type, final_price, status from group_order "
				+ "where product_id=?";
		GroupOrderVO groupOrderVO = null;
		List<GroupOrderVO> groupOrderList = new ArrayList<GroupOrderVO>();
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(getAllByProductIdSql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupOrderVO = new GroupOrderVO();
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
		PreparedStatement ps = null;
		String updateStatusByGroupOrderIdSqlSql = "UPDATE group_order SET end_time=now() WHERE group_order_id=?;";
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(updateStatusByGroupOrderIdSqlSql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	}

	@Override
	public Integer updateStatusByGroupOrderId(Integer id, Integer status) {
		PreparedStatement ps = null;
		String updateStatusByGroupOrderIdSql = "UPDATE group_order SET status=? WHERE group_order_id=?;";
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(updateStatusByGroupOrderIdSql);
			ps.setInt(1, status);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	}

	@Override
	public Integer updateFinalPriceByGroupOrderId(Integer id, Integer finalPrice) {
		PreparedStatement ps = null;
		String updateStatusByGroupOrderIdSql = "UPDATE group_order SET final_price=? WHERE group_order_id=?;";
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(updateStatusByGroupOrderIdSql);
			ps.setInt(1, finalPrice);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return id;
	}

	@Override
	public List<GroupOrderVO> getAllInProgressByProductId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllByProductIdSql = "select group_order_id, product_id, create_time, end_time, end_type, final_price, status from group_order "
				+ "where product_id=? and status=0";
		GroupOrderVO groupOrderVO = null;
		List<GroupOrderVO> groupOrderList = new ArrayList<GroupOrderVO>();
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(getAllByProductIdSql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupOrderVO = new GroupOrderVO();
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

	public List<GroupOrderVO> getAllInProgress(Connection con) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllByProductIdSql = "select * from group_order o " + "join product p on(p.product_id=o.product_id) "
				+ "join product_img i on(i.product_id=p.product_id) "
				+ "join picture pt on(i.product_img_id=pt.picture_id) " + "where o.status=0 group by o.group_order_id";
		GroupOrderVO groupOrderVO = null;
		List<GroupOrderVO> groupOrderList = new ArrayList<GroupOrderVO>();
		try {
			ps = con.prepareStatement(getAllByProductIdSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupOrderVO = new GroupOrderVO();
				groupOrderVO.setProductId(rs.getInt("product_id"));
				groupOrderVO.setStatus(rs.getInt("status"));
				ProductVO productVO = new ProductVO();
				productVO.setProductName(rs.getString("product_name"));
				productVO.setGroupPrice1(rs.getInt("group_price1"));
				productVO.setGroupAmount1(rs.getInt("group_amount1"));
				productVO.setGroupAmount2(rs.getInt("group_amount2"));
				productVO.setGroupAmount3(rs.getInt("group_amount3"));
				productVO.setDescription(rs.getString("description"));
				productVO.setStatus(rs.getInt("status"));
				PictureVO pictureVO = new PictureVO();
				pictureVO.setPreviewUrl(rs.getString("preview_url"));
				groupOrderVO.setProductVO(productVO);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupOrderList;
	}

	@Override
	public List<GroupOrderVO> getAllInProgress() throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		List<GroupOrderVO> groupOrderVOList = getAllInProgress(con);
		con.close();
		return groupOrderVOList;
	}

	public List<GroupOrderVO> getAllInProgress2(Connection con) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllByProductIdSql = "select * from group_order o " + " join product p on(p.product_id=o.product_id) "
				+ " where o.status=0 ";
		List<GroupOrderVO> groupOrderList = new ArrayList<GroupOrderVO>();
		try {
			ps = con.prepareStatement(getAllByProductIdSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				GroupOrderVO groupOrderVO = new GroupOrderVO();
				groupOrderVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupOrderVO.setProductId(rs.getInt("o.product_id"));
				groupOrderVO.setStatus(rs.getInt("o.status"));
				ProductVO productVO = new ProductVO();
				productVO.setPrice(rs.getInt("price"));
				productVO.setProductName(rs.getString("product_name"));
				productVO.setGroupPrice1(rs.getInt("group_price1"));
				productVO.setGroupAmount1(rs.getInt("group_amount1"));
				productVO.setGroupAmount2(rs.getInt("group_amount2"));
				productVO.setGroupAmount3(rs.getInt("group_amount3"));
				productVO.setDescription(rs.getString("description"));
				productVO.setStatus(rs.getInt("p.status"));
				groupOrderVO.setProductVO(productVO);
				groupOrderList.add(groupOrderVO);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return groupOrderList;
	}

	public List<GroupOrderVO> getAllInProgress2() throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		List<GroupOrderVO> groupOrderList = getAllInProgress2(con);
		con.close();
		return groupOrderList;
	}

	@Override
	public List<GroupOrderVO> check() {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getAllSql = "select * from group_order o join product p on(p.product_id=o.product_id) where o.status=0 and end_time<now();";
		List<GroupOrderVO> groupOrderList = new ArrayList<GroupOrderVO>();
		GroupOrderVO groupOrderVO = null;
		try (Connection con = JDBCConnection.getRDSConnection()) {
			ps = con.prepareStatement(getAllSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				groupOrderVO = new GroupOrderVO();
				groupOrderVO.setGroupOrderId(rs.getInt("group_order_id"));
				groupOrderVO.setProductId(rs.getInt("product_id"));
				groupOrderVO.setCreateTime(rs.getTimestamp("create_time"));
				groupOrderVO.setEndTime(rs.getTimestamp("end_time"));
				groupOrderVO.setEndType(rs.getInt("end_type"));
				groupOrderVO.setFinalPrice(rs.getInt("final_price"));
				groupOrderVO.setStatus(rs.getInt("status"));
				groupOrderVO.setMinAmount(rs.getInt("min_amount"));
				ProductVO productVO = new ProductVO();
				productVO.setGroupPrice1(rs.getInt("group_price1"));
				productVO.setGroupAmount1(rs.getInt("group_amount1"));
				productVO.setGroupAmount2(rs.getInt("group_amount2"));
				productVO.setGroupAmount3(rs.getInt("group_amount3"));
				groupOrderVO.setProductVO(productVO);
				groupOrderList.add(groupOrderVO);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return groupOrderList;
	}
	public PageResult<GroupOrderVO> getPageResult(PageQuery pageQuery) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		PageResult<GroupOrderVO> groupPRs = getPageResult(pageQuery,con);
		con.close();
		return groupPRs;
	}


	public PageResult<GroupOrderVO> getPageResult(PageQuery pageQuery,Connection con) {
		String baseSQL = " SELECT * FROM group_order o " +
							" JOIN product p ON(p.product_id=o.product_id) ";
		int total = 0;
		List<GroupOrderVO> groupOrderVOs = new ArrayList<>();
		try {
			if (con != null) {
				PreparedStatement stmt = con.prepareStatement(pageQuery.getTotalCountSQL(baseSQL));
				ResultSet rs = stmt.executeQuery();
				rs.next();
				total = rs.getInt(1);
				rs.close();
				stmt.close();


				stmt = con.prepareStatement(pageQuery.getQuerySQL(baseSQL));
				rs = stmt.executeQuery();
				while (rs.next()) {
					GroupOrderVO groupOrderVO = buildGroupOrderVO(rs);
					groupOrderVOs.add(groupOrderVO);
				}
				rs.close();
				stmt.close();
			} else {
				throw new JDBCException("getPageResult() ::: Connection con is NULL !!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new PageResult<GroupOrderVO>(pageQuery, groupOrderVOs, total);
	}

	public GroupOrderVO buildGroupOrderVO(ResultSet rs) throws SQLException {
		GroupOrderVO groupOrderVO = new GroupOrderVO();
		groupOrderVO.setGroupOrderId(rs.getInt("group_order_id"));
		groupOrderVO.setProductId(rs.getInt("product_id"));
		groupOrderVO.setCreateTime(rs.getTimestamp("create_time"));
		groupOrderVO.setEndTime(rs.getTimestamp("end_time"));
		groupOrderVO.setEndType(rs.getInt("end_type"));
		groupOrderVO.setFinalPrice(rs.getInt("final_price"));
		groupOrderVO.setStatus(rs.getInt("status"));
		groupOrderVO.setMinAmount(rs.getInt("min_amount"));
		ProductVO productVO = new ProductVO();
		productVO.setProductName(rs.getString("product_name"));
		productVO.setPrice(rs.getInt("price"));
		productVO.setGroupPrice1(rs.getInt("group_price1"));
		productVO.setGroupAmount1(rs.getInt("group_amount1"));
		productVO.setGroupAmount2(rs.getInt("group_amount2"));
		productVO.setGroupAmount3(rs.getInt("group_amount3"));
		groupOrderVO.setProductVO(productVO);
		return groupOrderVO;
	}

}
