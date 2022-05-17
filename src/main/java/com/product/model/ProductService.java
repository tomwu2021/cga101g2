package com.product.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import com.p_sort1.model.PSort1Service;
import com.p_sort1.model.PSort1VO;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;
import com.product_img.model.ProductImgService;
import com.product_img.model.ProductImgVO;

public class ProductService {

	private ProductDAO_interface dao;

	private ProductRedis daoRedis;

	public ProductService() {
		dao = new ProductJNDIDAO();
		daoRedis = new ProductRedis();
	}

	public ProductVO updateProduct(Integer productId, String productName, Integer price, Integer amount,
			Integer sort2Id, Integer groupPrice1, Integer groupAmount1, Integer groupAmount2, Integer groupAmount3,
			String description, String sort1Id[], ArrayList<Part> partsList, String deleteImg[]) {
		System.out.println("ProductService updateProduct 開始更新");
//1.通過servlet驗證後製作商品DAO的材料
//2.新增商品
//2-1.打包pdVO
		ProductVO pdVO = new ProductVO();
		pdVO.setProductId(productId);
		pdVO.setProductName(productName);
		pdVO.setPrice(price);
		pdVO.setAmount(amount);
		pdVO.setSort2Id(sort2Id);
		pdVO.setGroupPrice1(groupPrice1);
		pdVO.setGroupAmount1(groupAmount1);
		pdVO.setGroupAmount2(groupAmount2);
		pdVO.setGroupAmount3(groupAmount3);
		pdVO.setDescription(description);
//2-2.DAO更新pdVO
		ProductVO productVO = new ProductVO();
		productVO = dao.update(pdVO);

//如果有刪除照片 刪除照片中間表		
		ProductImgService pdImgSvc = new ProductImgService();
		PictureService picSvc = new PictureService();
		if (deleteImg != null) {
			for (int i = 0; i < deleteImg.length; i++) {
				System.out.println("從updateProduct.jsp獲得deleteImg(pictureId)" + deleteImg[i]);
				ProductImgVO pdImgVO = new ProductImgVO();
				pdImgVO.setProductId(productId);
				pdImgVO.setProductImgId(Integer.valueOf(deleteImg[i]));
				pdImgSvc.delete(pdImgVO);
				System.out.println("要刪除的照片,pdImgSvc刪除中間表成功");
				// 刪除照片庫
				picSvc.deletePicture(Integer.valueOf(deleteImg[i]));
				System.out.println("要刪除的照片編號:" + deleteImg[i] + "pserv刪除相片庫成功");
			}
		}

//3.新增圖片庫(呼叫PictureService) 
		// 判斷該陣列是否為空值 client端的prats是否為空
		boolean empty = partsList.isEmpty();
		List<PictureVO> pvs = new ArrayList<>();
		if (!empty) {
			try {
				pvs = picSvc.uploadImage(partsList);

			} catch (Exception e) {
				throw new RuntimeException("A database error occured." + e.getMessage());
			}
			// 新增product_img中間表
			for (PictureVO pictureVO : pvs) {
				ProductImgVO pdImgVO = new ProductImgVO();
				pdImgVO.setProductId(productId);
				pdImgVO.setProductImgId(pictureVO.getPictureId());
				pdImgSvc.insert(pdImgVO);
			}
		}

//p_sort1中間表呼叫Psort1Service ,先刪除該商品id所有p_sort1後再新增
		PSort1Service pSort1Svc = new PSort1Service();
		boolean boo;
		boo = pSort1Svc.deletePSort1sByProductId(productId);
		// 如果boo=true有成功刪除後再新增
		if (boo == true) {
			for (int i = 0; i < sort1Id.length; i++) {
				System.out.println("從addproduct.jsp獲得sort1id" + sort1Id[i]);
				PSort1VO pSort1VO = new PSort1VO();
				pSort1VO.setProductId(productId);
				pSort1VO.setSort1Id(Integer.valueOf(sort1Id[i]));
				pSort1Svc.insert(pSort1VO);
				System.out.println("pSort1Svc 更新成功" + sort1Id[i]);
			}
		}
		return productVO;
	}

	public ProductVO insertProduct(String productName, Integer price, Integer amount, Integer sort2Id,
			Integer groupPrice1, Integer groupAmount1, Integer groupAmount2, Integer groupAmount3, String description,
			String sort1Id[], ArrayList<Part> partsList) {
//1.通過servlet驗證後製作商品DAO的材料
//2.新增商品
//2-1.打包pdVO
		ProductVO pdVO = new ProductVO();
		pdVO.setProductName(productName);
		pdVO.setPrice(price);
		pdVO.setAmount(amount);
		pdVO.setSort2Id(sort2Id);
		pdVO.setGroupPrice1(groupPrice1);
		pdVO.setGroupAmount1(groupAmount1);
		pdVO.setGroupAmount2(groupAmount2);
		pdVO.setGroupAmount3(groupAmount3);
		pdVO.setDescription(description);
//2-2.DAO新增pdVO
		ProductVO productVO = new ProductVO();
		productVO = dao.insert(pdVO);
//2-3.拿取DAO回傳的newProductVO的PKID
		int newProductId = productVO.getProductId();
//3.新增圖片庫(呼叫PictureService)
		PictureService pserv = new PictureService();
		List<PictureVO> pvs = new ArrayList<>();
		try {
			pvs = pserv.uploadImage(partsList);

		} catch (Exception e) {
			throw new RuntimeException("A database error occured." + e.getMessage());
		}
//新增product_img中間表
		ProductImgService pdImgSvc = new ProductImgService();
//		return PictureVO取得ID2
		for (PictureVO pictureVO : pvs) {
			ProductImgVO pdImgVO = new ProductImgVO();
			pdImgVO.setProductId(newProductId);
			pdImgVO.setProductImgId(pictureVO.getPictureId());
			pdImgSvc.insert(pdImgVO);
		}

//新增p_sort1中間表呼叫Psort1Service
		PSort1Service pSort1Svc = new PSort1Service();

		for (int i = 0; i < sort1Id.length; i++) {
			System.out.println("從addproduct.jsp獲得sort1id" + sort1Id[i]);
			PSort1VO pSort1VO = new PSort1VO();
			pSort1VO.setProductId(newProductId);
			pSort1VO.setSort1Id(Integer.valueOf(sort1Id[i]));
			pSort1Svc.insert(pSort1VO);
			System.out.println("pSort1Svc 新增成功");
		}

		return productVO;
	}

	public int getProductIdTotalView(Integer productId) {
		return daoRedis.getProductIdTotalView(productId);
	}

	public int addProductIdTotalView(Integer productId) {
		return daoRedis.addProductIdTotalView(productId);
	}

	public ProductVO getOneProductByid(Integer prodouctId) {
		return dao.getOneById(prodouctId);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

	public List<ProductVO> getAll(Map<String, String[]> map, int pageSize, int pageNo) {
		return dao.getAll(map, pageSize, pageNo);
	}

	public int getAllTotalCount(Map<String, String[]> map) {
		return dao.getAllTotalCount(map);
	}

	public boolean productUpdateStatus(ProductVO productVO) {
		return dao.delete(productVO);
	}

	public boolean deleteByTopStatus(Integer topStatus, Integer productId) {
		return dao.deleteByTopStatus(topStatus, productId);
	}

	public List<ProductVO> getForShopFront(Map<String, String[]> map, int pageSize, int pageNo) {
		return dao.getForShopFront(map, pageSize, pageNo);
	}

	public int getForShopFrontTotalCount(Map<String, String[]> map) {
		return dao.getForShopFrontTotalCount(map);
	}

	public List<ProductVO> getForGroupShopFront(Map<String, String[]> map) {
		return dao.getForGroupShopFront(map);
	}

	public ProductVO checkProdcutName(String prodcutName) {
		return dao.checkProdcutName(prodcutName);
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
