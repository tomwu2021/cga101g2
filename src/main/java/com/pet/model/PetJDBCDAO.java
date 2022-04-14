package com.pet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import connection.JDBCConnection;

public class PetJDBCDAO implements PetDAO_interface{

	Connection con;

	private static final String INSERT = "INSERT INTO pet (member_id, pet_name, type, gender, introduction, picture_id, birthday, status) VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
	private static final String DELETE = "DELETE FROM pet WHERE pet_id = ?";
	private static final String UPDATE = "UPDATE pet SET pet_name = ?, type = ?, gender = ?, introduction = ?, picture_id = ?, birthday = ?, status = ? WHERE pet_id = ?";
	private static final String GET_ONE = "SELECT pet_id, member_id, pet_name, type, gender, introduction, picture_id, birthday, status FROM pet WHERE pet_id = ?";
	private static final String GET_ALL = "SELECT pet_id, member_id, pet_name, type, gender, introduction, picture_id, birthday, status FROM pet ORDER BY pet_id";
	
	
	@Override
	public PetVO insert(PetVO petVO) {
		
		con = JDBCConnection.getRDSConnection();
		PetVO petVO2 = insert(petVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return petVO2;
	}
	
	public PetVO insert(PetVO petVO, Connection con) {
	
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setInt(index++, petVO.getMemberId());
				pstmt.setString(index++, petVO.getPetName());
				pstmt.setInt(index++, petVO.getType());
				pstmt.setInt(index++, petVO.getGender());
				pstmt.setString(index++, petVO.getIntroduction());
				pstmt.setInt(index++, petVO.getPictureId());
				pstmt.setDate(index++, petVO.getBirthday());
				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					petVO.setPetId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return petVO;
	}

	
	@Override
	public boolean delete(PetVO petVO) {
		
		con = JDBCConnection.getRDSConnection();
		boolean boo = delete(petVO, con);
		
		try {
			con.close();
			return boo;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean delete(PetVO petVO, Connection con) {
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				int index = 1;
				pstmt.setInt(index++, petVO.getPetId());
				pstmt.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}	
		} else {
			return false;
		}
	}
	
	
	@Override
	public PetVO update(PetVO petVO) {
		
		con = JDBCConnection.getRDSConnection();
		PetVO petVO2 = update(petVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return petVO2;
	}
	
	public PetVO update(PetVO petVO, Connection con) {
		
		if (con != null) {
			try {
				
				PreparedStatement pstmt = con.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setString(index++, petVO.getPetName());
				pstmt.setInt(index++, petVO.getType());
				pstmt.setInt(index++, petVO.getGender());
				pstmt.setString(index++, petVO.getIntroduction());
				pstmt.setInt(index++, petVO.getPictureId());
				pstmt.setDate(index++, petVO.getBirthday());
				pstmt.setInt(index++, petVO.getStatus());
				pstmt.setInt(index++, petVO.getPetId());
				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					petVO.setPetId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return petVO;
		
	}

	
	@Override
	public PetVO getOneById(Integer id) {
		
		con = JDBCConnection.getRDSConnection();
		PetVO petVO =new PetVO();
			
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ONE);
				int index = 1;
				pstmt.setInt(index, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					petVO.setPetId(rs.getInt(index++));
					petVO.setMemberId(rs.getInt(index++));
					petVO.setPetName(rs.getString(index++));
					petVO.setType(rs.getInt(index++));
					petVO.setGender(rs.getInt(index++));
					petVO.setIntroduction(rs.getString(index++));
					petVO.setPictureId(rs.getInt(index++));
					petVO.setBirthday(rs.getDate(index++));
					petVO.setStatus(rs.getInt(index++));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return petVO;
		
	}
		
		
	@Override
	public List<PetVO> getAll() {
		
		con = JDBCConnection.getRDSConnection();
				List<PetVO> list = new ArrayList<PetVO>();
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ALL);
				int index = 1;
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					PetVO petVO =new PetVO();
					petVO.setPetId(rs.getInt(index++));
					petVO.setMemberId(rs.getInt(index++));
					petVO.setPetName(rs.getString(index++));
					petVO.setType(rs.getInt(index++));
					petVO.setGender(rs.getInt(index++));
					petVO.setIntroduction(rs.getString(index++));
					petVO.setPictureId(rs.getInt(index++));
					petVO.setBirthday(rs.getDate(index++));
					petVO.setStatus(rs.getInt(index++));
					list.add(petVO);
					index = 1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
}
