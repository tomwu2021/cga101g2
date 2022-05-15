package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductService;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/back/shop/productInsert")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//當數據量大於fileSizeThreshold值時，內容將被寫入磁碟
//上傳過程中無論是單個文件超過maxFileSize值，或者上傳的總量大於maxRequestSize 值都會拋出IllegalStateException 異常
public class ProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");// client 端向 Servlet 請求的編碼
		res.setCharacterEncoding("UTF-8");// response，設定回應的格式及編碼
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			列舉client送來的所有請求參數名稱
			try{
				String name; 
				Enumeration<?>  pNames=req.getParameterNames(); 
				 while(pNames.hasMoreElements()){ 
				  name=(String)pNames.nextElement();
				  System.out.println(name+"="+req.getParameter(name));
				  }
				}catch(Exception e){
				System.out.println(e.toString());
				}

//			try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String productName = req.getParameter("productName");
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.put("productName", "商品名稱勿空白");
			} else if ((productName.trim().length() <= 5)) { 
				errorMsgs.put("productName", "商品名稱至少五個字以上");
			}
		
			//****自助式轉碼  存入資料庫****//
			productName.replace("%25","%").replace("%2F","/").replace("%3F","?")
						.replace("%3A",":").replace("%40","@").replace("%3D","=")
						.replace("%24","$").replace("%23","#").replace("%20"," ");
			//****自助式轉碼****//
			
//			Integer price = null;
//			try {
//				
//				if (price < 0) {
//					errorMsgs.put("price", "價格不得低於0");
//				}
//			} catch (NumberFormatException e) {
//				errorMsgs.put("price", "價格請填數字");
//			}

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
					System.out.println("sort1Id陣列"+"["+i+"]"+"數值"+sort1Id[i]);
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
		
			//****自助式轉碼  存入資料庫****//
			description.replace("%25","%").replace("%2F","/").replace("%3F","?")
						.replace("%3A",":").replace("%40","@").replace("%3D","=")
						.replace("%24","$").replace("%23","#").replace("%20"," ");
			//****自助式轉碼****//
			
			if (req.getPart("img") == null) {
				errorMsgs.put("img", "至少上傳一張照片");
			}
			
			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
			
			ArrayList<Part> partsList = new ArrayList<Part>();
			for (Part part : parts) {
		       if(part.getName().equals("img")) {
					partsList.add(part);
		       }
			}
			 
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = 
						req.getRequestDispatcher("/back/shop/addProduct.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			ProductService pdSvc = new ProductService();
			pdSvc.insertProduct(productName, price, amount, sort2Id, groupPrice1, groupAmount1, groupAmount2,
					groupAmount3, description, sort1Id,partsList);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			res.sendRedirect(req.getContextPath()+"/back/shop?action=listProducts_Byfind");
//			RequestDispatcher successView = req.getRequestDispatcher(url); 
//			successView.forward(req, res);
//			不能用以上兩行,因為req參數會被帶過去
			
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.put("Exception","新資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/addEmp.jsp");
//				failureView.forward(req, res);
//			}
		}
	}

//	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
//	public String getFileNameFromPart(Part part) {
//		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
//		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
//		if (filename.length() == 0) {
//			return null;
//		}
//		return filename;
//	}
}
