package com.album.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.cookie.params.CookieSpecPNames;

import com.picture.model.PictureVO;

import connection.JDBCConnection;

public class AlbumJDBCDAO implements AlbumDAO_Interface {

	@Override
	public AlbumVO insert(AlbumVO albumvo) {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = "insert into album(member_id,name,authority) values(?,?,0);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			stmt.setInt(index++, albumvo.getMemberId());
			stmt.setString(index++, albumvo.getName());
			stmt.execute();
			albumvo.setAlbumId(Statement.RETURN_GENERATED_KEYS);
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
		String sql = "insert into album(member_id,name,authority) values(?,?,0);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			stmt.setInt(index++, albumvo.getMemberId());
			stmt.setString(index++, "未分類");
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
		Connection con = JDBCConnection.getRDSConnection();
		String sql = "UPDATE album SET name=?,authority=? where album_id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, albumvo.getName());
			stmt.setInt(2, albumvo.getAuthority());
			stmt.setInt(3, albumvo.getAlbumId());
			stmt.execute();
			albumvo.setName(albumvo.getName());
			albumvo.setAuthority(albumvo.getAuthority());
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albumvo;
	}

	@Override
	public AlbumVO getOneById(Integer id) {
		return null;
	}

	public List<AlbumVO> getPersonalAlbum(Integer memberId){
		Connection con = JDBCConnection.getRDSConnection();
		String sql = "select name from album where member_id = ?;";
		List<AlbumVO> avoList = new ArrayList<AlbumVO>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, memberId);
			ResultSet rs = stmt.executeQuery();
			AlbumVO albumvo = new AlbumVO();
			while (rs.next()) {
				albumvo.setAlbumId(rs.getInt("album_id"));
				albumvo.setMemberId(rs.getInt("member_id"));
				albumvo.setAuthority(rs.getInt("authority"));
				albumvo.setCreateTime(rs.getTimestamp("create_time"));
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

	@Override
	public Integer selectDefaultAlbumByMemberId(Integer mid) {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = "SELECT album_id FROM album WHERE member_id = ? " + "ORDER BY create_time ASC LIMIT 1;";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, mid);
			ResultSet rs = stmt.executeQuery();
			Integer albumId = rs.getInt("album_id");
			rs.close();
			stmt.close();
			con.close();
			return albumId;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}

	public List<PictureVO> getFirstPictureOfAlbums(Integer memberId) {
		Connection con = JDBCConnection.getRDSConnection();
		String sql = "SELECT a.member_id,ph.album_id,p.* \r\n" + "	from picture p \r\n" + "	JOIN photo ph  \r\n"
				+ "		ON(p.picture_id=ph.picture_id)\r\n" + "	JOIN album a\r\n"
				+ "		ON(ph.album_id=a.album_id)\r\n" + "	WHERE ph.album_id \r\n"
				+ "		IN(SELECT album_id from album where member_id = ?)\r\n" + "GROUP BY a.album_id";
		List<PictureVO> pictureList = new ArrayList<PictureVO>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, memberId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				PictureVO pictureVo = new PictureVO();
				pictureVo.setPictureId(rs.getInt("picture_id"));
				pictureVo.setFileKey(rs.getString("p_url"));
				pictureVo.setCreateTime(rs.getTimestamp("create_time"));
				pictureVo.setFileKey(rs.getString("file_key"));
				pictureVo.setFileName(rs.getString("filename"));
				pictureVo.setSize(rs.getLong("size"));
				pictureList.add(pictureVo);
			}
			rs.close();
			stmt.close();
			con.close();
			return pictureList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(AlbumVO albumVO) {
		Connection con = JDBCConnection.getRDSConnection();
		if (isAlbum(albumVO.getAlbumId(), con) > 0) {
			String sql = "DELETE FROM album WHERE album_id = ?";
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, albumVO.getAlbumId());
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

	public Integer isAlbum(Integer id, Connection con) {
		String sql = "SELECT album_id FROM album where album_id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			Integer albumId = rs.getInt(1);
			rs.close();
			stmt.close();
			con.close();
			return albumId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
