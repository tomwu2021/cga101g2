package com.post.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Part;

import com.album.model.AlbumJDBCDAO;
import com.alibaba.druid.sql.dialect.mysql.ast.MySqlIndexHintImpl;
import com.common.model.MappingJDBCDAO;
import com.common.model.MappingTableDto;
import com.members.model.MembersVO;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;
import com.product_img.model.ProductImgService;
import com.product_img.model.ProductImgVO;

import connection.JDBCConnection;
import connection.JNDIConnection;

public class PostService {

	private PostDAO_interface dao;

	public PostService() {

		dao = new PostJDBCDAO();
	}

	/**
	 * 新增貼文圖片與內容
	 * 
	 */

	MappingJDBCDAO mappingDAO = new MappingJDBCDAO();

	public PostVO uploadPost(Integer memberId, String content, Collection<Part> parts) {
		// 1.插入貼文
		PostJDBCDAO dao = new PostJDBCDAO();
		PostVO postAll = new PostVO();
		postAll.setMemberId(memberId);
		postAll.setContent(content);
		Connection con = JDBCConnection.getRDSConnection();

		AlbumJDBCDAO album = new AlbumJDBCDAO();
		Integer albumId = album.selectDefaultAlbumByMemberId(memberId);

		// 搜集你的貼文材料
//		dao.insert(postAll);

		// return postVO取得ID1
		PostVO postVO2 = new PostVO();
		postVO2 = dao.insert(postAll);

		int id1 = postVO2.getPostId();

		// 2.插入圖片

		// 搜集你的圖片材料

		PictureService pserv = new PictureService();

		List<PictureVO> picvolist = new ArrayList<>();

		try {
			picvolist = pserv.uploadImage(parts, albumId);

		} catch (Exception e) {
			throw new RuntimeException("A database error occured." + e.getMessage());
		}

		List<MappingTableDto> dtolist = new ArrayList<MappingTableDto>();

		// return PictureVO取得ID2
		for (PictureVO pictureVO : picvolist) {
			MappingTableDto dto = postPicMapping(id1, pictureVO.getPictureId());
			dtolist.add(dto);
			System.out.println("asdff");
		}

		try {
			Connection connection = JDBCConnection.getRDSConnection();
			mappingDAO.insertMultiMapping(dtolist, connection);
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 3.插入mapping表
//		postPicMapping(ID1);
//		mappingDAO.insertOneMapping(postPicMapping(, ));
		// mappingDAO.deleteOneMapping(postPicMapping(ID1,ID2));

		return postAll;

	}

	public MappingTableDto postPicMapping(Integer id1) {
		MappingTableDto mappingTableDto = new MappingTableDto();
		mappingTableDto.setTableName1("post_pic");
		mappingTableDto.setColumn1("post_id");
		mappingTableDto.setId1(id1);
		return mappingTableDto;
	}

	public MappingTableDto postPicMapping(Integer id1, Integer id2) {
		MappingTableDto mappingTableDto = new MappingTableDto();
		mappingTableDto.setTableName1("post_pic");
		mappingTableDto.setColumn1("post_id");
		mappingTableDto.setColumn2("picture_id");
		mappingTableDto.setId1(id1);
		mappingTableDto.setId2(id2);
		return mappingTableDto;
	}

	/**
	 * 讀取會員頭貼跟姓名
	 */
	public MembersVO selectmember(Integer memberId) {
		return dao.selectmember(memberId);
	}

	/**
	 * 查詢全部個人貼文
	 * 
	 */

	public List<PostVO> selectPost(Integer memberid) {

//		Connection con = JDBCConnection.getRDSConnection();
		List<PostVO> postList = dao.selectPost(memberid);

//		PictureJDBCDAO picdao = new PictureJDBCDAO();

		// postList包含貼文全部貼文欄位(無圖)
		// 透過Post表格、postPicMapping的postId找對應的picture_id
		// queryPicturesByMapping用來 join 「picture跟mapping表（post_pic)」，共同皆有picture_id欄位
		// 所以postList有原本全部貼文欄位再加上picture表格內圖的URL等欄位
//		for(PostVO postVO: postList) {
//		
//		MappingTableDto dto = postPicMapping(postVO.getPostId()); 
//				
//		List<PictureVO> piclist = picdao.queryPicturesByMapping(dto);
//		
//		postVO.setPictureList(piclist);  //piclist 某貼文圖片集合
//		
//		}

		return postList;
	}

	/**
	 * 查看熱門貼文
	 *
	 */
	public List<PostVO> selectHotPost() {

		return dao.selectHotPost();

//		List< PostVO> hotPostList = dao.selectHotPost();
//		
//		PictureJDBCDAO picdao = new PictureJDBCDAO();
//		
//		for(PostVO postVO: hotPostList) {
//			MappingTableDto dto = postPicMapping(postVO.getPostId());
//			
//			List<PictureVO> piclist = picdao.queryPicturesByMapping(dto);
//			
//			postVO.setPictureList(piclist);
//			
//		}
//		
//		return hotPostList;
	};

	/**
	 * 查詢status狀態為0的貼文
	 * 
	 */
	public List<PostVO> selectChangePost() {
		return dao.selectChangePost();
	}

	/**
	 * 查看單篇詳細貼文
	 * 
	 */

	public PostVO getOneById(Integer postId, Integer memberId) {

		PictureJDBCDAO picdao = new PictureJDBCDAO();
		PostVO postVO = dao.getOneById(postId, memberId);
		MappingTableDto dto = postPicMapping(postId);
		List<PictureVO> piclist = picdao.queryPicturesByMapping(dto);

		postVO.setPictureList(piclist);

		return postVO;
	}

	/**
	 * 修改貼文內容
	 * 
	 */

	public PostVO update(PostVO postupdateVO, ArrayList<Part> partsList, String deleteImg[]) {
		System.out.println("PostVO update開始更新");
		// 1.postVO打包好了
		dao.update(postupdateVO);
		// 2.如果有刪除照片 刪除照片中間表
		// 連線
		PictureService picSvc = new PictureService();
		if (deleteImg != null) {
			for (int i = 0; i < deleteImg.length; i++) {
				System.out.println("從updateProduct.jsp獲得deleteImg(pictureId)" + deleteImg[i]);
				mappingDAO.deleteOneMapping(postPicMapping(postupdateVO.getPostId(), Integer.valueOf(deleteImg[i])));
				System.out.println("要刪除的照片,pdImgSvc刪除中間表成功");
				// 刪除照片庫
				picSvc.deletePicture(Integer.valueOf(deleteImg[i]));
				System.out.println("要刪除的照片編號:" + deleteImg[i] + "pserv刪除相片庫成功");
			}
		}

		// 3.新增圖片庫(呼叫PictureService)
		// 判斷該陣列是否為空值 client端的prats是否為空
		boolean empty = partsList.isEmpty();
		List<PictureVO> pvs = new ArrayList<>();
		if (!empty) {
			try {
				System.out.println("新增該篇貼文照片");
				pvs = picSvc.uploadImage(partsList);

			} catch (Exception e) {
				throw new RuntimeException("A database error occured." + e.getMessage());
			}
			// 新增product_img中間表
			Connection con = JDBCConnection.getRDSConnection();
			for (PictureVO pictureVO : pvs) {
				MappingTableDto mtd = new MappingTableDto();
				mtd.setTableName1("post_pic");
				mtd.setColumn1("picture_id");
				mtd.setColumn2("post_id");
				mtd.setId1(pictureVO.getPictureId());
				mtd.setId2(postupdateVO.getPostId());
				mappingDAO.insertOneMapping(mtd, con);
			}
		}

		return postupdateVO;
	}

	/**
	 * 刪除貼文 把貼文狀態從status=0(正常)變成status=2（刪除）
	 */

	public boolean updatedelete(Integer postId) {
		return dao.updatedelete(postId);
	}

	/**
	 * 搜尋貼文
	 */
	public List<PostVO> selectkeyword(String content) {
		return dao.selectkeyword(content);
	}

	/**
	 * 貼文的讚
	 * 
	 */
	public int selectOnePostLikeCount(Integer postId) {
		return dao.selectOnePostLikeCount(postId);
	}

}
