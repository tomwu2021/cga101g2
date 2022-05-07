package com.article.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.article.model.ArticleDAO_interface;
import com.article.model.ArticleJDBCDAO;
import com.article.model.ArticleVO;
import com.common.model.*;
import com.picture.model.PictureDAO_Interface;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;

import connection.JDBCConnection;

public class ArticleService {
	
	private ArticleDAO_interface artiDAO;
	private PictureService picSvc;
	private MappingJDBCDAO mappingDAO;
	
	public ArticleService() {
		artiDAO = new ArticleJDBCDAO();
		picSvc= new PictureService();
		mappingDAO = new MappingJDBCDAO();
	}
	
		/* ==========新增一篇最新消息與圖片們=============*/
	public ArticleVO addArticle(Integer type, String title, String content, Integer empNo, Collection<Part> parts) {
		// 步驟一 建立article資料
			ArticleVO artiVO = new ArticleVO();
			artiVO.setType(type);
			artiVO.setTitle(title);
			artiVO.setContent(content);
			artiVO.setEmpNo(empNo);
			artiDAO.insert(artiVO);
				List<PictureVO> picVOs = null;
		// 步驟二 上傳圖片們
				try {
					picVOs = picSvc.uploadImage(parts);
				} catch (IOException e) {
					e.printStackTrace();
				}
		// 步驟三 所有mapping傳入article_picture
				Integer artiId = artiVO.getArticleId();
				List<Integer> picIds = new ArrayList<Integer>();
				for(PictureVO picVO:picVOs) {
					Integer picId = picVO.getPictureId();
					picIds.add(picId);
				}
				List<MappingTableDto> mappingDtos = artiPicMappingBatch(artiId,picIds);
				try(Connection con = JDBCConnection.getRDSConnection();){ 
				mappingDAO.insertMultiMapping(mappingDtos, con);
				} catch(SQLException e){
					e.printStackTrace();
					// transaction用
				};
		return artiVO;
	}
	
	
	/* ==========刪除一篇最新消息與圖片們=============*/
	public Boolean deleteArticle(Integer id) {
		// 步驟一 取得圖片list
		List<PictureVO> picList = getOneArticlePic(id);
		// 步驟二 刪除mapping
			                    // transaction用
		Connection con = JDBCConnection.getRDSConnection(); 
		try {
			MappingTableDto mappingDto = artiPicMapping(id);
			mappingDAO.deleteMultiMapping(mappingDto, con);
		// 步驟三 刪除圖片
			for(PictureVO pic:picList) {
				picSvc.deletePicture(pic.getPictureId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 步驟四 刪除article資料
			ArticleVO artiVO = new ArticleVO();
			artiVO.setArticleId(id);
			Boolean bool = artiDAO.delete(artiVO);
			return bool;

	}
	
	
	/* ==========修改一篇最新消息與圖片們============*/
	public ArticleVO updateArticle(Integer type, String title, String content, Integer empNo, Integer artiId, List<Integer> picIds, Collection<Part> parts) {
		// 步驟一 刪除圖片與mapping
		try {
		deletePicMapping(artiId,picIds);
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 步驟二 新增圖片與mapping
		try {
			insertPicMapping(artiId, parts);
			}catch(Exception e) {
				e.printStackTrace();
			}
		// 步驟三 修改article資料
		ArticleVO artiVO = new ArticleVO();
		artiVO.setType(type);
		artiVO.setTitle(title);
		artiVO.setContent(content);
		artiVO.setEmpNo(empNo);
		artiVO.setArticleId(artiId);
		artiDAO.update(artiVO);
		
		return artiVO;
	}
	
	
		// 查一篇最新消息
	public ArticleVO getOneArticleInfo(Integer articleId) {
		ArticleVO artiVO = artiDAO.getOneById(articleId);
		
		return artiVO;
	}
	// 查一篇最新消息的圖片
	public List<PictureVO> getOneArticlePic(Integer articleId) {
		PictureDAO_Interface picDAO = new PictureJDBCDAO();
		MappingTableDto mappingDto = artiPicMapping(articleId);
		List<PictureVO> picList = picDAO.queryPicturesByMapping(mappingDto);
		
		return picList;
	}

		// 查全部
	public List<ArticleVO> getAllArticle(){
		List<ArticleVO> artiList = artiDAO.getAll();
		return artiList;
	}
		
		// 查分類篩選
	public List<ArticleVO> getByArtiType(Integer type){
		List<ArticleVO> artiList = artiDAO.getAllByType(type);
		return artiList;
	}
	
	
	
	
	
	// 拿到多筆mapping，以提供新增刪除
	public List<MappingTableDto> artiPicMappingBatch(Integer artiId, List<Integer> picIds){
		List<MappingTableDto> mappingList = new ArrayList<MappingTableDto>();
		for(Integer picId:picIds) {
			MappingTableDto mappingDto = artiPicMapping(artiId,picId);
			mappingList.add(mappingDto);
		}
		return mappingList;
	}
	// 拿到一筆mapping，以供存到mapping的list
	public MappingTableDto artiPicMapping(Integer articleId, Integer pictureId) {
		MappingTableDto mappingDto = new MappingTableDto();
		mappingDto.setTableName1("article_picture");
		mappingDto.setColumn1("article_id");
    	mappingDto.setColumn2("picture_id");
    	mappingDto.setId1(articleId);
    	mappingDto.setId2(pictureId);
    	return mappingDto;
	}
	
	
	// 拿到一篇文章mapping，以提供查詢
	public MappingTableDto artiPicMapping(Integer articleId) {
		MappingTableDto mappingDto = new MappingTableDto();
		mappingDto.setTableName1("article_picture");
		mappingDto.setColumn1("article_id");
    	mappingDto.setId1(articleId);
    	return mappingDto;
	}
	
		
	// 刪除mapping與圖片
	public void deletePicMapping(Integer artiId,List<Integer> pictureIds) {
		for(Integer pictureId:pictureIds) {
			MappingTableDto mappingDto = artiPicMapping(artiId,pictureId);
			mappingDAO.deleteOneMapping(mappingDto);
			picSvc.deletePicture(pictureId);
		}
	}
		// 新增mapping與圖片
	public void insertPicMapping(Integer artiId, Collection<Part> parts) {
		List<PictureVO> picVOs = null;
		try {
			picVOs = picSvc.uploadImage(parts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Integer> picIds = new ArrayList<Integer>();
		for(PictureVO picVO:picVOs) {
			Integer picId = picVO.getPictureId();
			picIds.add(picId);
		}
		List<MappingTableDto> mappingDtos = artiPicMappingBatch(artiId,picIds);
		try(Connection con = JDBCConnection.getRDSConnection();){ 
		mappingDAO.insertMultiMapping(mappingDtos, con);
		} catch(SQLException e){
			e.printStackTrace();
			// transaction用
		};
	}
	
}