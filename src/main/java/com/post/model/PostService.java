package com.post.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Part;

import com.album.model.AlbumJDBCDAO;
import com.common.model.MappingJDBCDAO;
import com.common.model.MappingTableDto;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;

import connection.JDBCConnection;

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
	 *查詢全部個人貼文
	 * 
	 */
	
	public List<PostVO> selectPost(Integer memberid){
		List<PostVO> postList= dao.selectPost(memberid);
		
		PictureJDBCDAO picdao = new PictureJDBCDAO();
		
		
		//postList包含貼文全部貼文欄位(無圖)
		//透過Post表格、postPicMapping的postId找對應的picture_id
		//queryPicturesByMapping用來 join 「picture跟mapping表（post_pic)」，共同皆有picture_id欄位
		//所以postList有原本全部貼文欄位再加上picture表格內圖的URL等欄位
		for(PostVO postVO: postList) {
		
		
		MappingTableDto dto = postPicMapping(postVO.getPostId()); 
		
		
		List<PictureVO> piclist = picdao.queryPicturesByMapping(dto);
		
		postVO.setPictureList(piclist);  //piclist 某貼文圖片集合
		
		}
		
		return postList;         //貼文內容包含圖片集合
		}

	/**
	 *
	 *  三、查看熱門貼文
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
	public List<PostVO> selectChangePost(Integer memberid) {
		return dao.selectChangePost(memberid);
	}
	
	
	/**
	 * 修改貼文內容
	 * 
	 */
	
	public PostVO update(PostVO postVO, Connection con) {
		
		return dao.update(postVO);
	};
	
	// 五、修改
	
	
	// 六、搜尋貼文 
	

}
