package com.picture.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.model.MappingTableDto;

import connection.JDBCConnection;

public class PictureJDBCDAO implements PictureDAO_Interface {

	Connection con;

	public PictureVO insert(PictureVO pv, Connection con) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			return null;
		}
		return pv;
	}

	@Override
	public PictureVO insert(PictureVO pictureVO) {
		con = JDBCConnection.getRDSConnection();
		PictureVO pictureVO2 = insert(pictureVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pictureVO2;
	}

	@Override
	public PictureVO update(PictureVO pv) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<PictureVO> queryPicturesMapping(MappingTableDto mtd) {
		String sql = "SELECT * FROM picture p JOIN " + mtd.getTableName1() + " t ON(p.picture_id=t.picture_id) where "
				+ mtd.getColumn1() + "=" + mtd.getId1();
		Connection con = JDBCConnection.getRDSConnection();
		if (con != null) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				List<PictureVO> pvos = new ArrayList<PictureVO>();
				while (rs.next()) {
					PictureVO pvo = new PictureVO();
					pvo.setPictureId(rs.getInt("picture_id"));
					pvo.setFileKey(rs.getString("p_url"));
					pvo.setCreateTime(rs.getTimestamp("create_time"));
					pvo.setFileKey(rs.getString("file_key"));
					pvo.setFileName(rs.getString("filename"));
					pvo.setSize(rs.getLong("size"));
					pvos.add(pvo);
				}
				con.close();
				return pvos;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
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

	@Override
	public boolean delete(PictureVO t) {

		return false;
	}

}
