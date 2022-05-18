package com.allOrders.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.common.model.ResponseMessageDto;
import com.google.gson.Gson;
import com.members.model.MembersService;
import com.members.model.MembersVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.picture.model.PictureVO;
import com.product.model.ProductVO;
import com.ranks.model.RanksVO;

@WebServlet("/member/cart.do")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson = new Gson();

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
			String msg = "成功加入購物車";
			PrintWriter out = res.getWriter();
			out.print(gson.toJson(new ResponseMessageDto(200, msg)));

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
			String msg = "成功加入購物車";
			req.setAttribute("msg", msg);
			System.out.println(session.getAttribute("shopingCart"));
			String url = "/shop/ProductGetOneServlet?productId="+newProductVO.getProductId()+"&action=getOne_For_Shop";
//			String url = "/front/shop/productDetails.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 刪除購物車中的商品
		if ("DELETE".equals(action)) {
//			String del = req.getParameter("del");
//			int d = Integer.parseInt(del);
//			buylist.removeElementAt(d);
//
//			session.setAttribute("shopingCart", buylist);
//			System.out.println("刪除第"+del+"筆");

			String del = req.getParameter("del");
			int d = Integer.parseInt(del);

			for (int i = 0; i < buylist.size(); i++) {
				ProductVO productVO = buylist.get(i);

				// 找相同id的刪除
				if (productVO.getProductId().equals(d)) {
					buylist.removeElementAt(i);
				}
			} // for迴圈結束

			session.setAttribute("shopingCart", buylist);
			System.out.println("刪除第" + del + "筆");
			if (buylist.size()==0) {
				PrintWriter out = res.getWriter();
				out.write("空");
			}

		}

		// 在購物車中更改數量
		if ("ADDINCART".equals(action)) {
//			String sum = req.getParameter("sum");
//			int s = Integer.parseInt(sum);
//			Integer count = Integer.valueOf(req.getParameter("count").trim());
//			ProductVO productVO = buylist.get(s);			
//			productVO.setCartAmount(count);
//			buylist.setElementAt(productVO, s);			
//			session.setAttribute("shopingCart", buylist);
//			System.out.println("第"+s+"筆更改為"+count+"份");
//			
//			Integer price=count*(buylist.get(s).getPrice());
//			PrintWriter out=res.getWriter();
//			out.print(price);

			String sum = req.getParameter("sum");
			int s = Integer.parseInt(sum);
			Integer count = Integer.valueOf(req.getParameter("count").trim());

			for (int i = 0; i < buylist.size(); i++) {
				ProductVO productVO = buylist.get(i);

				// 新增書籍和購物車內商品相同時
				if (productVO.getProductId().equals(s)) {
					productVO.setCartAmount(count);
					buylist.setElementAt(productVO, i);
					Integer price = count * (buylist.get(i).getPrice());
					PrintWriter out = res.getWriter();
					out.print(price);
				}
			} // for迴圈結束

			session.setAttribute("shopingCart", buylist);
			System.out.println("商品編號" + s + "更改為" + count + "份");

		}

		if ("TOCHECKOUT".equals(action)) {
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO productVO = buylist.get(i);
				int price = productVO.getPrice();
				int quantity = productVO.getCartAmount();
				total += (price * quantity);
			}
			// 取得會員等級折扣
			MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
			MembersService membersService = new MembersService();
			RanksVO ranksVO = membersService.selectRankInfo(membersVO.getMemberId());
			// 回傳折扣金額
			req.setAttribute("discount", total - (int) (Math.round(ranksVO.getDiscount().doubleValue() * total)));
			String url = "/front/order/checkout.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("CHECK".equals(action)) {
			// 驗證錢包密碼用
			System.out.println("有進密碼驗證");
//			res.setContentType("application/json; charset=UTF-8");
			String password = req.getParameter("password");
			MembersService membersService=new MembersService();
			MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
			String dbPassword = membersService.geteWalletPassword(membersVO.getMemberId());
			if (password.equals(dbPassword)) {
				System.out.println("正確");
				PrintWriter out = res.getWriter();
				out.write("對");
			}else {
				System.out.println("錯誤");
				PrintWriter out = res.getWriter();
				out.write("錯");
			}
			

		}

		if ("CHECKOUT".equals(action)) {
			
			// 結帳
			// 先算總金額
			int total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProductVO productVO = buylist.get(i);
				int price = productVO.getPrice();
				int quantity = productVO.getCartAmount();
				total += (price * quantity);
			}
			MembersVO membersVO = (MembersVO) session.getAttribute("membersVO");
			// 取得會員等級折扣
			MembersService membersService = new MembersService();
			RanksVO ranksVO = membersService.selectRankInfo(membersVO.getMemberId());
			Integer discount = (int) (total - Math.round(ranksVO.getDiscount().doubleValue() * total));
			// 取得其餘資訊
			Integer memberId = membersVO.getMemberId();
			Integer bonus = Integer.valueOf(req.getParameter("bonus").trim());
			String memberName, memberPhone, address;
			Integer payPrice = total - discount - bonus;

			if (membersVO.geteWalletAmount() > payPrice) {
				if (req.getParameter("same") == null) {
					memberName = req.getParameter("memberName");
					memberPhone = req.getParameter("memberPhone");
					address = req.getParameter("county") + req.getParameter("district") + " "
							+ req.getParameter("address");
				} else {
					memberName = membersVO.getName();
					memberPhone = membersVO.getPhone();
					address = membersVO.getAddress();
				}
				// 錢包&紅利扣款
				membersService.walletPaymentAddMoney(memberId, -payPrice);
				membersService.bonusPaymentAddValue(memberId, -bonus);

				// 產生訂單
				OrdersService ordersService = new OrdersService();
				OrdersVO ordersVO = ordersService.addOneOrder(memberId, memberName, memberPhone, address, total, bonus,
						discount, payPrice);
				int orderId = ordersVO.getOrderId();

				// 插商品進商品詳情
				for (int i = 0; i < buylist.size(); i++) {
					ProductVO productVO = buylist.get(i);
					int productId = productVO.getProductId();
					int quantity = productVO.getCartAmount();
					int price = productVO.getCartAmount() * productVO.getPrice();
					ordersService.addProduct2Order(orderId, productId, quantity, price);
				}
				// 清空購物車
				session.removeAttribute("shopingCart");
				// 導到商品詳情
				// 先查商品ID，拿到單筆訂單所有商品id
				List<OrdersVO> productList = ordersService.getAllProductInOrder(orderId);
				// 再用商品ID找圖片
				List<OrdersVO> productDetail = new ArrayList<OrdersVO>();

				for (OrdersVO o : productList) {
					productDetail.add(ordersService.getAllProductPicture(orderId, o.getProductVO().getProductId()));
				}				
				req.setAttribute("orderDetail", productDetail.get(0));
				req.setAttribute("productDetail", productDetail);
				String msg = "已成功結帳";
				//更新session
				membersVO.seteWalletAmount(membersVO.geteWalletAmount()-payPrice);
				membersVO.setBonusAmount(membersVO.getBonusAmount()-bonus);
				req.setAttribute("msg", msg);
				String url = "/front/order/orderDetail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} else {
				String msg = "錢包餘額不足，請儲值";
				req.setAttribute("msg", msg);
				String url = "/front/member/memberWallet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}

		}
	}

	// 把資料塞進VO
	private ProductVO getProduct(HttpServletRequest req) {
		String productId = req.getParameter("productId");
		String productName = req.getParameter("productName");
		String productPrice = req.getParameter("productPrice");
		String productPictureUrl = req.getParameter("productPictureUrl");
		String productAmout = req.getParameter("productAmout");
		System.out.println("pid" + productId + "pname" + productName + "pprice" + productPrice);
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
