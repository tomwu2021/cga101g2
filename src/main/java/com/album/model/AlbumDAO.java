package com.album.model;

public class AlbumDAO {
	package com.album.model;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import connection.JDBCConnection;

	public class AlbumDAO implements Album_DAO_Interface {
		
		Connection con;
		
		@Override
		public AlbumVO insert(AlbumVO albumvo) {
			con = new JDBCConnection().getRDSConnection();
			String sql ="insert into album(member_id,name,authority) values(?,?,0);";
			try {
				PreparedStatement stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, albumvo.getMemberId());
				stmt.setString(2, albumvo.getName());
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
		public AlbumVO makeDefaultAlbum(AlbumVO albumvo) {
			con = new JDBCConnection().getRDSConnection();
			String sql ="insert into album(member_id,name,authority) values(?,?,0);";
			try {
				PreparedStatement stmt = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, albumvo.getMemberId());
				stmt.setString(2, "未分類");
				stmt.execute();
				albumvo.setName("未分類");
				albumvo.setAuthority(0);
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return albumvo;
		}

		@Override
		public void delete() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public AlbumVO update(AlbumVO albumvo) {
			con = new JDBCConnection().getRDSConnection();
			String sql ="UPDATE album SET name=?,authority=? where album_id=?; ;";
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
		public List<AlbumVO> getPersonalAlbum(Integer id){
			String sql = "select name from album where member_id = ?;";
			List<AlbumVO> avoList = new ArrayList<AlbumVO>();
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				AlbumVO albumvo = new AlbumVO();
				while(rs.next()) {
					albumvo.setAlbumId(rs.getInt("album_id"));
					albumvo.setMemberId(rs.getInt("member_id"));
					albumvo.setAuthority(rs.getInt("authority"));
					albumvo.setCreateTime(rs.getTimestamp("create_time"));
					avoList.add(albumvo);
				}
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
			// TODO Auto-generated method stub
			return null;
		}

	}

}
