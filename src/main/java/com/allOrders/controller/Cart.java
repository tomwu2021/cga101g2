package com.allOrders.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.picture.model.PictureVO;
import com.product.model.ProductVO;

@WebServlet("/member/cart.do")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Vector<ProductVO> buylist = (Vector<ProductVO>) session.getAttribute("shopingCart");

		String action = req.getParameter("action");
		System.out.println(action);
		// 新增書到購物車
		if ("ADD".equals(action)) {
			boolean match = false;
			// 取得後來新增的商品
			ProductVO newProductVO = getProduct(req);
			System.out.println(newProductVO.getProductName());
			// 第一個商品加入時，創建購物車
			if (buylist == null) {
				buylist = new Vector<ProductVO>();
				buylist.add(newProductVO);
			} else {
				for (int i = 0; i < buylist.size(); i++) {
					ProductVO productVO = buylist.get(i);

					// 新增書籍和購物車內商品相同時
					if (productVO.getProductId().equals(newProductVO.getProductId())) {
						productVO.setCartAmount(productVO.getCartAmount() + newProductVO.getCartAmount());
						buylist.setElementAt(productVO, i);
						match = true;
					}
				} // for迴圈結束

				if (!match) {
					buylist.add(newProductVO);
				}
			}
			session.setAttribute("shopingCart", buylist);

			System.out.println(session.getAttribute("shopingCart"));
			String url = "/front/shop/shop.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
		if ("ADDONE".equals(action)) {
			boolean match = false;
			// 取得後來新增的商品
			ProductVO newProductVO = getProduct(req);
			System.out.println(newProductVO.getProductName());
			// 第一個商品加入時，創建購物車
			if (buylist == null) {
				buylist = new Vector<ProductVO>();
				buylist.add(newProductVO);
			} else {
				for (int i = 0; i < buylist.size(); i++) {
					ProductVO productVO = buylist.get(i);

					// 新增書籍和購物車內商品相同時
					if (productVO.getProductId().equals(newProductVO.getProductId())) {
						productVO.setCartAmount(productVO.getCartAmount() + newProductVO.getCartAmount());
						buylist.setElementAt(productVO, i);
						match = true;
					}
				} // for迴圈結束

				if (!match) {
					buylist.add(newProductVO);
				}
			}
			session.setAttribute("shopingCart", buylist);

			System.out.println(session.getAttribute("shopingCart"));
			String url = "/front/shop/productDetails.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 刪除購物車中的商品
		if ("DELETE".equals(action)) {
			String del = req.getParameter("del");
			int d = Integer.parseInt(del);
			buylist.removeElementAt(d);

			session.setAttribute("shopingCart", buylist);
			System.out.println(session.getAttribute("shopingCart"));
			String url = "/front/shop/shoppingCart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
		
		if ("TOCHECKOUT".equals(action)) {
			String url = "/front/order/checkout.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
		if ("MEMBERDATA".equals(action)) {
			MembersVO membersVO=(MembersVO) session.getAttribute("membersVO");
		}
		
			
		if ("CHECKOUT".equals(action)) {
			//先算總金額
			int total=0;
			for(int i=0;i<buylist.size();i++) {
				ProductVO productVO=buylist.get(i);
				int price=productVO.getPrice();
				int quantity=productVO.getCartAmount();
				total+=(price*quantity);
			}
			MembersVO membersVO=(MembersVO) session.getAttribute("membersVO");
			
			Integer memberId=membersVO.getMemberId();
//			Integer memberId=Integer.valueOf(req.getParameter("memberName"));	
			String memberName = req.getParameter("memberName");	
			String memberPhone = req.getParameter("memberPhone");
			String password = req.getParameter("password");
			String address = req.getParameter("county")+req.getParameter("district")+" "+req.getParameter("address");
			
			OrdersService ordersService=new OrdersService();
			OrdersVO ordersVO=ordersService.addOneOrder(memberId, memberName, memberPhone, address, total, memberId, memberId, memberId);
			int orderId=ordersVO.getOrderId();
			
			//插商品進商品詳情
			for(int i=0;i<buylist.size();i++) {
				ProductVO productVO=buylist.get(i);				
				int productId=productVO.getProductId();
				int quantity=productVO.getCartAmount();
				int price=productVO.getCartAmount()*productVO.getPrice();
				ordersService.addProduct2Order(orderId, productId, quantity,price);
			}
			//導到商品詳情
			//先查商品ID，拿到單筆訂單所有商品id
			List<OrdersVO> productList=ordersService.getAllProductInOrder(orderId);
			//再用商品ID找圖片
			List<OrdersVO> productDetail=new ArrayList<OrdersVO>();
			
			for(OrdersVO o:productList) {
				productDetail.add(ordersService.getAllProductPicture(orderId, o.getProductVO().getProductId()));
			}
			System.out.println(productList.get(0).getSumPrice());
			req.setAttribute("orderDetail", productDetail.get(0));
			req.setAttribute("productDetail", productDetail);
			
			String url = "/front/order/orderDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
		}
	}

	// 把資料塞進VO
	private ProductVO getProduct(HttpServletRequest req) {
		String productId = req.getParameter("productId");
		String productName = req.getParameter("productName");
		String productPrice = req.getParameter("productPrice");
		String productPictureUrl = req.getParameter("productPictureUrl");
		String productAmout = req.getParameter("productAmout");
		ProductVO productVO = new ProductVO();
		PictureVO pictureVO = new PictureVO();
		pictureVO.setPreviewUrl(productPictureUrl);
		productVO.setProductId((new Integer(productId)).intValue());
		productVO.setProductName(productName);
		productVO.setPrice((new Integer(productPrice)).intValue());
		productVO.setCartAmount((new Integer(productAmout)).intValue());
		productVO.setPictureVO(pictureVO);
		return productVO;
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
