package com.album.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.picture.model.PictureJDBCDAO;
import org.apache.http.cookie.params.CookieSpecPNames;

import com.picture.model.PictureVO;

import connection.JDBCConnection;

public class AlbumJDBCDAO implements AlbumDAO_Interface {

	private AlbumVO buildAlbumVOAfter(ResultSet rs) throws SQLException {
		AlbumVO albumVO = new AlbumVO();
		albumVO.setAlbumId(rs.getInt("album_id"));
		albumVO.setMemberId(rs.getInt("member_id"));
		albumVO.setName(rs.getString("name"));
		albumVO.setCreateTime(rs.getTimestamp("create_time"));
		albumVO.setAuthority(rs.getInt("authority"));
		albumVO.setCoverId(rs.getInt("cover_id"));
		return albumVO;
	}

	@Override
	public AlbumVO insert(AlbumVO albumvo) {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = "INSERT INTO album(member_id,name,authority,cover_id) VALUES(?,?,0,?);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int index = 0;
			stmt.setInt(++index, albumvo.getMemberId());
			stmt.setString(++index, albumvo.getName());
			stmt.setInt(++index, albumvo.getCoverId());
			stmt.executeUpdate();
			albumvo.setAuthority(0);
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				albumvo.setAlbumId(rs.getInt(1));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albumvo;
	}

	public AlbumVO makeDefaultAlbum(AlbumVO albumvo, Connection con) {
		String sql = "INSERT INTO album(member_id,name,authority,cover_id) VALUES(?,?,0,999);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int index = 0;
			stmt.setInt(++index, albumvo.getMemberId());
			stmt.setString(++index, "未分類");
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			while (rs.next()) {
				albumvo.setAlbumId(rs.getInt(1));
			}
			albumvo.setName("未分類");
			albumvo.setAuthority(0);
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albumvo;
	}

	@Override
	public AlbumVO update(AlbumVO albumvo) {
		return null;
	}

	@Override
	public AlbumVO getOneById(Integer id) {
		String sql = " SELECT * FROM album WHERE album_id = ? ";
		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			AlbumVO albumVO = buildAlbumVOAfter(rs);
			return albumVO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean updateAlbumName(Integer albumId, String albumName) {
		if (albumId != null) {
			Connection con = JDBCConnection.getRDSConnection();
			String sql = "UPDATE album SET name=? WHERE album_id=? ";
			if (isAlbum(albumId, con)) {
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					int index = 0;
					stmt.setString(++index, albumName);
					stmt.setInt(++index, albumId);
					stmt.executeUpdate();
					stmt.close();
					con.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public boolean updateAlbumAuthority(Integer albumId, Integer authority) {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = "UPDATE album SET authority=? WHERE album_id=? ";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setInt(++index, authority);
			stmt.setInt(++index, albumId);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateCover(Integer albumId, Integer coverId) {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = "UPDATE album SET cover_id=? WHERE album_id=? ";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			int index = 0;
			stmt.setInt(++index, coverId);
			stmt.setInt(++index, albumId);
			stmt.executeUpdate();
			stmt.close();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public List<AlbumVO> getPersonalAlbum(Integer memberId) {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = " SELECT * FROM album al JOIN picture p ON(al.cover_id=p.picture_id) WHERE al.member_id = ?; ";
		List<AlbumVO> avoList = new ArrayList<AlbumVO>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, memberId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PictureVO pictureVO = buildPictureVOAfter(rs);
				AlbumVO albumvo = buildAlbumVOAfter(rs);
				albumvo.setPictureVO(pictureVO);
				avoList.add(albumvo);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return avoList;
	}

	@Override
	public List<AlbumVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 回傳該member預設相簿Id
	 */
	@Override
	public Integer selectDefaultAlbumByMemberId(Integer memberId) {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = "SELECT album_id FROM album WHERE member_id = ? " + "ORDER BY create_time ASC LIMIT 1;";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, memberId);
			ResultSet rs = stmt.executeQuery();
			Integer albumId = null;
			if (rs.next()) {
				albumId = rs.getInt("album_id");
			}
			rs.close();
			stmt.close();
			con.close();
			return albumId;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteAlbumById(Integer albumId) {
		Connection con = JDBCConnection.getRDSConnection();
		if (isAlbum(albumId, con)) {
			String sql = "DELETE FROM album WHERE album_id = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, albumId);
				stmt.executeUpdate();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	public boolean isAlbum(Integer albumId, Connection con) {
		if (albumId != null) {
			String sql = "SELECT album_id FROM album where album_id = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, albumId);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					rs.getInt(1);
				}
				rs.close();
				stmt.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean delete(AlbumVO t) {
		// TODO Auto-generated method stub
		return false;
	}

//	public Map<Integer, PictureVO> getFirstPictureOfAlbums(Integer memberId) {
//		Connection con = JDBCConnection.getRDSConnection();
//		String sql = " SELECT a.album_id, p.* FROM picture p " + " JOIN photos ph ON(p.picture_id=ph.picture_id) "
//				+ " JOIN album a ON(ph.album_id=a.album_id) " + " WHERE ph.album_id "
//				+ "	IN(SELECT album_id FROM album WHERE member_id = ?) " + " GROUP BY a.album_id ";
//		Map<Integer, PictureVO> pictureList = new HashMap<Integer, PictureVO>();
//		try {
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setInt(1, memberId);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				PictureVO pictureVo = new PictureJDBCDAO().buildPictureVO(rs);
//				pictureList.put(rs.getInt("album_id"), pictureVo);
//			}
//			rs.close();
//			stmt.close();
//			con.close();
//			return pictureList;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//
//			e.printStackTrace();
//			return null;
//		}
//	}

	private PictureVO buildPictureVOAfter(ResultSet rs) throws SQLException {
		PictureVO pictureVO = new PictureVO();
		pictureVO.setPictureId(rs.getInt("picture_id"));
		pictureVO.setUrl(rs.getString("url"));
		pictureVO.setCreateTime(rs.getTimestamp("create_time"));
		pictureVO.setFileName(rs.getString("file_name"));
		pictureVO.setFileKey(rs.getString("file_key"));
		pictureVO.setSize(rs.getLong("size"));
		pictureVO.setPreviewUrl(rs.getString("preview_url"));
		pictureVO.setPreviewKey(rs.getString("preview_key"));
		return pictureVO;
	}

}
