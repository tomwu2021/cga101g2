package com.post.model;

import java.io.IOException;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.album.model.AlbumJDBCDAO;
import com.common.model.MappingJDBCDAO;
import com.common.model.MappingTableDto;
import com.picture.model.PictureDAO_Interface;
import com.picture.model.PictureJDBCDAO;
import com.picture.model.PictureVO;
import com.picture.service.PictureService;

import connection.JDBCConnection;

public class PostService {
	
	
	private PostDAO_interface dao;

	public PostService() {
		
		dao = new PostJDBCDAO();
	}
	

	
	MappingJDBCDAO mappingDAO = new MappingJDBCDAO();
	
	//新增貼文圖片	
	public PostVO uploadPost(Integer memberId,String content, Collection<Part> parts) {
		//1.插入貼文
		PostJDBCDAO dao = new PostJDBCDAO();
		PostVO postvo = new PostVO();
		postvo.setMemberId(memberId);
		postvo.setContent(content);
		Connection con = JDBCConnection.getRDSConnection();
		
		AlbumJDBCDAO album = new AlbumJDBCDAO();
		Integer albumId = album.selectDefaultAlbumByMemberId(memberId);
		
		
		
		//搜集你的貼文材料
		dao.insert(postvo);
		
		//return postVO取得ID1
		
		
		//2.插入圖片
		
		//搜集你的圖片材料
		
		PictureVO pvo =new PictureVO();
		PictureService pserv= new PictureService();
	
		try {
			pserv.uploadImage(parts,albumId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return PictureVO取得ID2
		
		
		//3.插入mapping表
		
//		mappingDAO.insertOneMapping(postPicMapping(1, 1));
				//mappingDAO.deleteOneMapping(postPicMapping(ID1,ID2));
		
        
		return postvo;
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
    
    
	
	//查詢全部個人貼文
	public List<PostVO> selectPost(Integer memberid){
		
		return dao.selectPost(memberid);
	};
	
	//查看熱門貼文
	public List<PostVO> selectHotPost(){
		
		return dao.selectHotPost();
	};
	
	//修改貼文內容
	public PostVO update(PostVO postVO, Connection con) {
		
		return dao.update(postVO);
	};
	
	
}
