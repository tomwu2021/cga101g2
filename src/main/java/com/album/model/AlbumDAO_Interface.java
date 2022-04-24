package
com.album.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.common.model.JDBCDAO_Interface;

public interface AlbumDAO_Interface extends JDBCDAO_Interface<AlbumVO> {
	/**
	 * 找出會員預設相簿ID
	 * @param mid 會員ID
	 * @return
	 */
	public Integer selectDefaultAlbumByMemberId(Integer mid);

	String getNameById(Integer id);

	public List<AlbumVO> getPersonalAlbum(Integer memberId);
	public AlbumVO getOneById(Integer id);
}
