package com.orders.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.common.exception.JDBCException;
import com.common.model.PageQuery;
import com.common.model.PageResult;
import com.members.model.MembersVO;
import com.picture.model.PictureResult;
import com.picture.model.PictureVO;
import com.product.model.ProductVO;

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
				ordersList.add(buildOrdersVO(rs));
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


	@Override
	public List<OrdersVO> getAllProductByOrderId(Integer orderId) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getOneByMemberIdSql = "select product_id,product_amount,order_price from order_product where order_id=?;";
		List<OrdersVO> ordersList=new ArrayList<OrdersVO>();
		OrdersVO ordersVO=null;
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(getOneByMemberIdSql, Statement.RETURN_GENERATED_KEYS,ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, orderId);
			rs=ps.executeQuery();
			while(rs.next()) {
				ordersVO=new OrdersVO();
				ordersVO.setProductAmount(rs.getInt("product_amount"));
				ordersVO.setOrderPrice(rs.getInt("order_price"));
				ProductVO productVO=new ProductVO();
				productVO.setProductId(rs.getInt("product_id"));
				ordersVO.setProductVO(productVO);
				ordersList.add(ordersVO);			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ordersList;
	}


	@Override
	public OrdersVO getOrderDetail(Integer orderId, Integer productId) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		String getOneByMemberIdSql = "select * "
				+ "from order_product op "			
				+ "join orders o on (o.order_id=op.order_id) "
				+ "join product_img i on(i.product_id=op.product_id) "
				+ "join picture p on(i.product_img_id=p.picture_id) "
				+ "join product pd on(pd.product_id=op.product_id)"
				+ "where op.order_id=? and op.product_id=? limit 1;";
//		List<OrdersVO> ordersList=new ArrayList<OrdersVO>();
		OrdersVO ordersVO=null;
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(getOneByMemberIdSql, Statement.RETURN_GENERATED_KEYS,ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, orderId);
			ps.setInt(2, productId);
			rs=ps.executeQuery();
			while(rs.next()) {
				ordersVO=new OrdersVO();
				ordersVO.setProductAmount(rs.getInt("product_amount"));
				ordersVO.setOrderPrice(rs.getInt("order_price"));				
				ordersVO.setRecipient(rs.getString("recipient"));
				ordersVO.setPhone(rs.getString("phone"));
				ordersVO.setAddress(rs.getString("address"));				
				ordersVO.setSumPrice(rs.getInt("sum_price"));
				ordersVO.setBonus(rs.getInt("bonus"));
				ordersVO.setDiscount(rs.getInt("discount"));
				ordersVO.setPayPrice(rs.getInt("pay_price"));				
				ProductVO productVO=new ProductVO();
				productVO.setProductId(rs.getInt("product_id"));
				productVO.setProductName(rs.getString("product_name"));
				productVO.setPrice(rs.getInt("price"));
				PictureVO pictureVO=new PictureVO();
				pictureVO.setPreviewUrl(rs.getString("preview_url"));
				ordersVO.setProductVO(productVO);
				ordersVO.setPictureVO(pictureVO);			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ordersVO;
	}


	@Override
	public void addProduct2Order(Integer orderId, Integer productId, Integer quantity, Integer price) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		ResultSet rs=null;
		//建立時訂單狀態為進行中
		String insertSql="insert into order_product(order_id, product_id, product_amount, order_price) "
				+ "values(?,?,?,?);";
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, orderId);
			ps.setInt(2, productId);
			ps.setInt(3, quantity);
			ps.setInt(4, price);
			ps.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public PageResult<OrdersVO> getPageResult(PageQuery pq) throws JDBCException {
		PreparedStatement ps=null;
		ResultSet rs=null;
		String baseSQL = "select order_id, member_id, recipient, phone, address, "
				+ "sum_price, bonus, discount, pay_price, status, create_time from orders ";
		int total = 0;
		List<OrdersVO> ordersList = new ArrayList<>();
		try (Connection con=JDBCConnection.getRDSConnection()){
			ps=con.prepareStatement(pq.getTotalCountSQL(baseSQL));
			rs=ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
			ps.close();
			ps=con.prepareStatement(pq.getQuerySQL(baseSQL));
			rs=ps.executeQuery();
			while(rs.next()) {
				ordersList.add(buildOrdersVO(rs));
			}
			System.out.println(ordersList);
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new JDBCException("getPageResult() ::: Connection con is NULL !!");
		}
		return new PageResult<OrdersVO>(pq, ordersList, total);
	}


	public OrdersVO buildOrdersVO(ResultSet rs) throws SQLException {
		OrdersVO ordersVO=new OrdersVO();
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
		return ordersVO;
	}




}
