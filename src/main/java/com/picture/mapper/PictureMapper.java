package com.picture.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.picture.model.PictureVO;



public interface PictureMapper {
	
	@Select(value="SELECT * FROM picture")
	List<PictureVO> selectAll();
	
}
