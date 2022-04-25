package com.picture.mapper;

import com.common.model.PageQuery;
import com.picture.model.PictureVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;



public interface PictureMapper {

	@Select(value="SELECT * FROM picture")
	List<PictureVO> selectAll();

	@Select(value = "SELECT * FROM picture p JOIN photos ph ON ph.picture_id = p.picture_id ${ whereSQL }")
	List<PictureVO> selectByPageQuery(PageQuery pq);

	@Select(value = "SELECT COUNT(*) FROM picture p JOIN photos ph ON ph.picture_id = p.picture_id ${ whereSQL }")
	int selectCountByPageQuery(PageQuery pq);
}
