package com.picture.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import connection.JDBCConnection;

public class PictureDAO implements PictureDAO_Interface {

	Connection con;

	@Override
	public PictureVO insert(PictureVO pv) {
		con = JDBCConnection.getRDSConnection();
		String sql = "insert into picture(p_url, file_key, file_name, size) values(?,?,?,?)";
		if (con != null) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				stmt.setString(index++, pv.getpUrl());
				stmt.setString(index++, pv.getFileKey());
				stmt.setString(index++, pv.getFileName());
				stmt.setLong(index++, pv.getSize());
				stmt.execute();
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					pv.setPictureId(rs.getInt(1));
				}
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("沒有連線");
		}
		return pv;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public PictureVO update(PictureVO pv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PictureVO getOneById(Integer pv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PictureVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
