package
com.album.model;

import com.common.model.JDBC_DAO_Interface;

public interface Album_DAO_Interface extends JDBC_DAO_Interface<AlbumVO> {
	/**
	 * 找出會員預設相簿ID
	 * @param mid 會員ID
	 * @return
	 */
	public Integer selectDefaultAlbumByMemberId(Integer mid);
}
