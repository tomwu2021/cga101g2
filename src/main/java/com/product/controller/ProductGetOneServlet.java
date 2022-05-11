package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.picture.model.PictureVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_img.model.ProductImgService;
import com.product_img.model.ProductImgVO;
import com.sort2.model.Sort2Service;
import com.sort2.model.Sort2VO;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/shop/ProductGetOneServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class ProductGetOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		res.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼
		
		String action = req.getParameter("action");
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

		if ("getOne_For_Update".equals(action)) { // 來自listAllProduct.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 ****************************************/
			Integer productId = Integer.valueOf(req.getParameter("productId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ProductService pdSvc = new ProductService();
			ProductVO pdVO = pdSvc.getOneProductByid(productId);
			String param = "?productId=" + pdVO.getProductId() 
							+ "&productName=" + pdVO.getProductName() 
							+ "&price="+ pdVO.getPrice() 
							+ "&amount=" + pdVO.getAmount()
							+ "&sort2Id=" + pdVO.getSort2Id() 
							+ "&updateTime="+ pdVO.getUpdateTime() 
							+ "&groupPrice1=" + pdVO.getGroupPrice1() 
							+ "&groupAmount1="+ pdVO.getGroupAmount1()
							+ "&groupAmount2=" + pdVO.getGroupAmount2() 
							+ "&groupAmount3="+ pdVO.getGroupAmount3()
							+ "&description=" + pdVO.getDescription();
			String url = "/back/shop/updateProduct.jsp" + param;

			ProductImgService pImgSvc = new ProductImgService();
			List<PictureVO> pictureVOList = pImgSvc.getPicVOsByProductId(productId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

			req.setAttribute("pictureVOList", pictureVOList); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put("無法取得資料",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("listAllPtoduct.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("update".equals(action)) { // 來自updateProduct.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer productId = Integer.valueOf(req.getParameter("productId"));

			String productName = req.getParameter("productName");
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.put("productName", "商品名稱勿空白");
			} else if ((productName.trim().length() <= 5)) {
				errorMsgs.put("productName", "商品名稱至少五個字以上");
			}

			Integer amount = null;
			try {
				amount = Integer.valueOf(req.getParameter("amount").trim());
				if (amount < 0) {
					errorMsgs.put("amount", "數量不得低於0");
				}
			} catch (NumberFormatException e) {
				errorMsgs.put("amount", "數量請填數字");
			}

			// select子分類單選
			Integer sort2Id = Integer.valueOf(req.getParameter("sort2Id".trim()));

			// CheckBox主分類多選
			String sort1Id[] = req.getParameterValues("sort1Id");
			if (sort1Id == null) {
				errorMsgs.put("sort1Id", "必須勾選至少一種");
			}
			if (sort1Id != null) {
				for (int i = 0; i < sort1Id.length; i++) {
					System.out.println("sort1Id陣列" + "[" + i + "] : " + "sort1Id:" + sort1Id[i]);
				}
			}

//數字大比拚,避免NumberFormatException 先針對個別數字確定可以後再比較		

			Integer price = null;
			Integer groupPrice1 = null;
			Integer groupAmount1 = null;
			Integer groupAmount2 = null;
			Integer groupAmount3 = null;
			try {
				price = Integer.valueOf(req.getParameter("price").trim());
				groupPrice1 = Integer.valueOf(req.getParameter("groupPrice1").trim());
				groupAmount1 = Integer.valueOf(req.getParameter("groupAmount1").trim());
				groupAmount2 = Integer.valueOf(req.getParameter("groupAmount2").trim());
				groupAmount3 = Integer.valueOf(req.getParameter("groupAmount3").trim());

				if (price < 0) {
					errorMsgs.put("price", "價格不得低於0");
				}
				if (groupAmount1 < 0) {
					errorMsgs.put("groupAmount1", "數量不得低於0");
				}
				if (groupAmount2 < 0) {
					errorMsgs.put("groupAmount2", "數量不得低於0");
				}
				if (groupPrice1 < 0) {
					errorMsgs.put("groupPrice1", "數量不得低於0");
				}
				if (groupAmount3 < 0) {
					errorMsgs.put("groupAmount3", "數量不得低於0");
				}

//針對團購價格一驗證
				if (groupPrice1 > price) {
					errorMsgs.put("groupPrice1", "團購價不得高於商品價格");
				}
				if (groupPrice1 == price) {
					errorMsgs.put("groupPrice1", "團購價不得等同於商品價格");
				}
// 針對團購級距一的驗證			
				if (groupAmount1 >= groupAmount2) {
					errorMsgs.put("groupAmount1", "團購級距一不得高於或等於級距二");
				}
				if (groupAmount1 >= groupAmount3) {
					errorMsgs.put("groupAmount1", "團購級距一不得高於或等於級距三");
				}
// 針對團購級距二的驗證
				if (groupAmount2 <= groupAmount1) {
					errorMsgs.put("groupAmount2", "團購級距二不得低於或等於級距一");
				}
				if (groupAmount2 >= groupAmount3) {
					errorMsgs.put("groupAmount2", "團購級距二不得高於或等於級距三");
				}
// 針對團購級距三的驗證
				if (groupAmount3 <= groupAmount2) {
					errorMsgs.put("groupAmount3", "團購級距三不得低於或等於級距二");
				}
				if (groupAmount3 <= groupAmount1) {
					errorMsgs.put("groupAmount3", "團購級距三不得低於或等於級距一");
				}

			} catch (NumberFormatException e) {
				errorMsgs.put("price", "價格請填數字");
				errorMsgs.put("groupPrice1", "數量請填數字");
				errorMsgs.put("groupAmount1", "數量請填數字");
				errorMsgs.put("groupAmount2", "數量請填數字");
				errorMsgs.put("groupAmount3", "數量請填數字");
			}

			String description = req.getParameter("description");
			if (description == null || description.trim().length() == 0) {
				errorMsgs.put("description", "商品內容勿空白");
			} else if ((description.trim().length() <= 20)) {
				errorMsgs.put("description", "商品敘述至少20字以上");
			} else if ((description.trim().length() >= 300)) {
				errorMsgs.put("description", "商品敘述至多300字以內");
			}

//要被刪除的照片
			String deleteImg[] = req.getParameterValues("deleteImg");
			if (deleteImg != null) {
				for (int i = 0; i < deleteImg.length; i++) {
					System.out.println("deleteImg陣列" + "[" + i + "] :" + "picture_id : " + deleteImg[i]);
				}
			}

//要新增的照片			

			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理

			ArrayList<Part> partsList = new ArrayList<Part>();
			for (Part part : parts) {
				if (part.getName().equals("img")) {
					partsList.add(part);
				}
			}

//至少要有一張照片
			//所以要被刪除的照片總數不可以等於現有的照片總數 >> 刪除照片總數-上傳照片總數不可以等於現有的照片總數
			// 1.如果沒有照片要刪除,就不用進入此判斷(Cannot read the array length because "deleteImg" is null)
			//判斷該陣列是否為空值
//			計算該productId的pictureVOList數量
			ProductImgService pImgSvc = new ProductImgService();
			List<PictureVO> pictureVOList =null;
			pictureVOList = pImgSvc.getPicVOsByProductId(productId);
			if(deleteImg !=null) {
				Integer nowPdimgCounts = pictureVOList.size();
				System.out.println("商品"+productId+"的照片總數 : "+nowPdimgCounts);
				if ((deleteImg.length - partsList.size()) == nowPdimgCounts) {
					errorMsgs.put("img", "不可全部刪除，商品至少要有一張照片");
				}
			}

			
			ProductService pdSvc = new ProductService();
			//查詢時間用的
			ProductVO pdVO = pdSvc.getOneProductByid(productId);
			String param = "?updateTime=" + pdVO.getUpdateTime(); //為了有時間
			
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("pictureVOList", pictureVOList); //照片會消失,所以要丟回去
				String url = "/back/shop/updateProduct.jsp" + param ;
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return;
			}
			
			
			pdSvc.updateProduct(productId, productName, price, amount, sort2Id,
					groupPrice1, groupAmount1, groupAmount2, groupAmount3, 
					description, sort1Id, partsList, deleteImg);
			/*************************** 3.更新完成,準備轉交(Send the Success view) ***********/
			Map<String, String> msg = new LinkedHashMap<String, String>();
			msg.put("msg", "商品更新成功");
			req.setAttribute("msg", msg);
			
			
			String url = "/back/shop/updateProduct.jsp" + param ;
			pictureVOList = pImgSvc.getPicVOsByProductId(productId); //照片會消失,所以要查詢新的丟回去
			req.setAttribute("pictureVOList", pictureVOList);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.put("Exception","新資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("getOne_For_Shop".equals(action)) { // 來自front allProduct.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 ****************************************/
			Integer productId = Integer.valueOf(req.getParameter("productId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ProductService pdSvc = new ProductService();
			ProductVO pdVO = pdSvc.getOneProductByid(productId);

			Sort2Service sort2Svc = new Sort2Service();
			Sort2VO sort2VO = sort2Svc.getOneById(pdVO.getSort2Id());

//			ProductImgService pdImgService = new ProductImgService();
//			List<PictureVO> pictureVOList = pdImgService.getPicVOsByProductId(productId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?productId=" + pdVO.getProductId() 
						+ "&productName=" + pdVO.getProductName() 
						+ "&price=" + pdVO.getPrice() 
						+ "&amount=" + pdVO.getAmount() 
						+ "&sort2Id=" + pdVO.getSort2Id() 
						+ "&updateTime=" + pdVO.getUpdateTime() 
						+ "&groupPrice1=" + pdVO.getGroupPrice1()
						+ "&groupAmount1=" + pdVO.getGroupAmount1() 
						+ "&groupAmount2=" + pdVO.getGroupAmount2()
						+ "&groupAmount3=" + pdVO.getGroupAmount3()
						+ "&description=" + pdVO.getDescription()
					// 子分類的名字
						+ "&sort2Name=" + sort2VO.getSort2Name();
			// 商品照片的集合
//						+ "&pictureVOList=" + pdVO.getPictureVOList();
//			req.setAttribute("pictureVOList", pictureVOList);

			String url = "/front/shop/productDetails.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 /front/shop/productDetails.jsp"
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put("無法取得資料",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("listAllPtoduct.jsp");
//				failureView.forward(req, res);
//			}
		}

		if ("getOne_For_GroupShop".equals(action)) { // 來自front groupsShop.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
			/*************************** 1.接收請求參數 ****************************************/
			Integer productId = Integer.valueOf(req.getParameter("productId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ProductService pdSvc = new ProductService();
			ProductVO pdVO = pdSvc.getOneProductByid(productId);

			Sort2Service sort2Svc = new Sort2Service();
			Sort2VO sort2VO = sort2Svc.getOneById(pdVO.getSort2Id());

//			ProductImgService pdImgService = new ProductImgService();
//			List<PictureVO> pictureVOList = pdImgService.getPicVOsByProductId(productId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?productId=" + pdVO.getProductId()
			   			+ "&productName=" + pdVO.getProductName() 
			   			+ "&price="+ pdVO.getPrice() 
			   			+ "&amount=" + pdVO.getAmount() 
			   			+ "&sort2Id=" + pdVO.getSort2Id() 
			   			+ "&updateTime="+ pdVO.getUpdateTime() 
			   			+ "&groupPrice1=" + pdVO.getGroupPrice1() 
			   			+ "&groupAmount1="+ pdVO.getGroupAmount1()
			   			+ "&groupAmount2=" + pdVO.getGroupAmount2() 
			   			+ "&groupAmount3="+ pdVO.getGroupAmount3() 
			   			+ "&description=" + pdVO.getDescription()
					// 子分類的名字
			   			+ "&sort2Name=" + sort2VO.getSort2Name();
			// 商品照片的集合
//						+ "&pictureVOList=" + pdVO.getPictureVOList();
//			req.setAttribute("pictureVOList", pictureVOList);

			String url = "/front/shop/groupProductDetails.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 /front/shop/groupProductDetails.jsp"
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
//			} catch (Exception e) {
//				errorMsgs.put("無法取得資料",e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("listAllPtoduct.jsp");
//				failureView.forward(req, res);
//			}
		}

	}
}
