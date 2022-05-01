package
com.album.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.common.model.JDBCDAO_Interface;
import com.common.model.PageQuery;
import com.common.model.PageResult;

public interface AlbumDAO_Interface extends JDBCDAO_Interface<AlbumVO> {
	/**
	 * 找出會員預設相簿ID
	 * @param mid 會員ID
	 * @return
	 */
	public Integer selectDefaultAlbumByMemberId(Integer mid);
	PageResult<AlbumVO> getPersonalAlbum(Integer memberId, Integer loginId, PageQuery pg);
	public AlbumVO getOneById(Integer id);
	boolean deleteAlbumById(Integer albumId);
}
