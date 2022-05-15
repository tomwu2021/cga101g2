package com.wishlist.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MembersVO;
import com.picture.model.PictureVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_img.model.ProductImgService;
import com.wishlist.model.WishlistService;
import com.wishlist.model.WishlistVO;

/**
 * Servlet implementation class WishlistInsertServlet
 */
@WebServlet("/shop/wishlist")
public class GoWishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("GoWishlistServlet 執行成功");
		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		res.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼

//		列舉client送來的所有請求參數名稱
		try {
			String name;
			Enumeration<?> pNames = req.getParameterNames();
			while (pNames.hasMoreElements()) {
				name = (String) pNames.nextElement();
				System.out.println(name + "=" + req.getParameter(name));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/**************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************************/
			// 獲取req的路徑
			String userUrl = req.getHeader("Referer");
			System.out.println("req.getHeader(\"Referer\"); :" + userUrl);
			// 判斷有沒有登入 Session
			HttpSession session = req.getSession();
			Integer memberId = null;
			if ((MembersVO) session.getAttribute("membersVO") != null) {
				MembersVO memberVO = (MembersVO) session.getAttribute("membersVO");
				memberId = memberVO.getMemberId();
			} else {
				errorMsgs.put("MemberId", "請先登入");
			}
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/front/login.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始複合查詢 ***************************************/
			long totalSeconds1 = (System.currentTimeMillis());

			WishlistService wishSvc = new WishlistService();
			List<WishlistVO> wishList = wishSvc.getAllByMemberId(memberId);
			ProductImgService piSvc = new ProductImgService();

			for (WishlistVO vo : wishList) {
				List<PictureVO> pictureVOList = piSvc.getPicVOsByProductId(vo.getProductId());
				vo.setPictureVOList(pictureVOList);
			}
			System.out.println("本次查詢wishList筆數" + wishList.size());

			/// ********推薦商品資料 開始*********///
			ProductService pdSvc = new ProductService();
			Map<String, String[]> topMap = new TreeMap<String, String[]>();
			topMap.put("top_status", new String[] { "1" });
			List<ProductVO> topProdcutList = pdSvc.getForShopFront(topMap, 99999, 0);
			for (ProductVO vo : topProdcutList) {
				List<PictureVO> pictureVOList = piSvc.getPicVOsByProductId(vo.getProductId());
				vo.setPictureVOList(pictureVOList);
				///********查詢ProductRedis 開始*********///
				//添增try / catch防止沒開啟Redis 而死機
				try  {
					vo.setTotalView(pdSvc.getProductIdTotalView(vo.getProductId()));
				} catch (Exception e) {
			    }
				///********查詢ProductRedis 結束*********///
			}
			/// ********推薦商品資料 結束*********///

			long totalSeconds2 = (System.currentTimeMillis());
			System.out.println(totalSeconds2 - totalSeconds1);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("wishList", wishList); // 資料庫取出的list物件,存入request
			req.setAttribute("topProdcutList", topProdcutList);
			// **********************0506要轉交給shop2改成轉交給shop***********************//

			RequestDispatcher successView = req.getRequestDispatcher("/front/shop/wishlist.jsp"); // 成功轉交shop.jsp
			successView.forward(req, res);
	}
}
