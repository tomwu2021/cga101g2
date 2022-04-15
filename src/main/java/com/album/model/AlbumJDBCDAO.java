package com.album.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.JDBCConnection;

public class AlbumJDBCDAO implements AlbumDAO_Interface {

	Connection con;

	@Override
	public AlbumVO insert(AlbumVO albumvo) {
		con = JDBCConnection.getRDSConnection();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return albumvo;
	}

	@Override
	public AlbumVO update(AlbumVO albumvo) {
		con = JDBCConnection.getRDSConnection();
		String sql = "UPDATE album SET name=?,authority=? where album_id=?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, albumvo.getName());
			stmt.setInt(2, albumvo.getAuthority());
			stmt.setInt(3, albumvo.getAlbumId());
			stmt.execute();
			albumvo.setName(albumvo.getName());
			albumvo.setAuthority(albumvo.getAuthority());
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

	public List<AlbumVO> getPersonalAlbum(Integer memberId) {
		con = JDBCConnection.getRDSConnection();
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
			con.close();
			return avoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<AlbumVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer selectDefaultAlbumByMemberId(Integer mid) {
		String sql = "SELECT id FROM album WHERE member_id = ? ORDER BY create_time ASC LIMIT 1;";
		
		
		return null;
	}

	@Override
	public boolean delete(AlbumVO t) {
		// TODO Auto-generated method stub
		return false;
	}

}