package com.picture.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import aws.S3Service;
import connection.JDBCConnection;

public class PictureDAO implements PictureDAO_Interface {

	Connection con;

	@Override
	public PictureVO insert(PictureVO pv) {
		con = new JDBCConnection().getRDSConnection();
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

//	public void insertToMapping(String tableName, String columnName1, String columnName2, String mappingId1,String mappingId2,
	
//			List<PictureVO> picList) {
//		con = new JDBCConnection().getRDSConnection();
//		Integer dMapping_id = (Integer) Integer.parseInt(Mapping_id);
//		String sql = "insert into " + tableName + "(" + mappingColumnName + ",picture_id) values(?,?);";
//		List<PictureVO> picList2 = insertPictures(picList);
//		if (con != null) {
//			try {
//				PreparedStatement stmt = con.prepareStatement(sql);
//				for (PictureVO pic : picList2) {
//					stmt.setInt(1, pic.getPicture_id());
//					stmt.setInt(2, dMapping_id);
//					stmt.addBatch();
//				}
//				stmt.executeBatch();
//				con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}

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
