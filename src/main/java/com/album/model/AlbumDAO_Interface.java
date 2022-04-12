package
com.album.model;

import com.common.model.JDBCDAO_Interface;

public interface AlbumDAO_Interface extends JDBCDAO_Interface<AlbumVO> {
	/**
	 * 找出會員預設相簿ID
	 * @param mid 會員ID
	 * @return
	 */
	public Integer selectDefaultAlbumByMemberId(Integer mid);
}
