package com.pet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.JNDIConnection;

public class PetDAO implements PetDAO_interface{
	Connection con;

//	① create 新增寵物資料(前)------------------------	
	@Override
	public PetVO insert(PetVO petVO) {
		con = JNDIConnection.getRDSConnection();
		PetVO petVO2 = insert(petVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
		return petVO2;
	}
	/** @see #insert*/
	public PetVO insert(PetVO petVO, Connection con) {
		final String INSERT = "INSERT INTO pet (member_id, pet_name, sort1_id, gender, introduction, picture_id, birthday, status)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, 0)";
	
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				int breed = petVO.getSort1Id();
				pstmt.setInt(index++, petVO.getMemberId());
				if (petVO.getPetName() != null) pstmt.setString(index++, petVO.getPetName().trim());
				else pstmt.setNull(index++, 0);
				pstmt.setInt(index++, breed);
				if (petVO.getGender() != null) pstmt.setInt(index++, petVO.getGender());
				else pstmt.setNull(index++, 0);
				if (petVO.getIntroduction() !=null && !petVO.getIntroduction().trim().isEmpty()) pstmt.setString(index++, petVO.getIntroduction().trim());
				else pstmt.setNull(index++, 0);
				if (petVO.getPictureId() !=null) pstmt.setInt(index++, petVO.getPictureId());
				else if(breed==1) pstmt.setInt(index++, 999);
				else if(breed==2) pstmt.setInt(index++, 1000);
				if (petVO.getBirthday() != null) pstmt.setDate(index++, petVO.getBirthday());
				else pstmt.setNull(index++, 0);
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
	
//	② delete 刪除預設寵物用---------------------------
	@Override
	public boolean delete(PetVO petVO) {
		con = JNDIConnection.getRDSConnection();
		boolean boo = delete(petVO, con);
		
		try {
			con.close();
			return boo;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/** @see #delete*/
	public boolean delete(PetVO petVO, Connection con) {
		final String DELETE = "DELETE FROM pet WHERE member_id = ? AND pet_name IS NULL";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				int index = 1;
				pstmt.setInt(index, petVO.getMemberId());
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
	
//	③ update 更新寵物資料(前)------------------------	
	@Override
	public PetVO update(PetVO petVO) {
		con = JNDIConnection.getRDSConnection();
		PetVO petVO2 = update(petVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
		return petVO2;
	}
	/** @see #update*/
	public PetVO update(PetVO petVO, Connection con) {
		final StringBuffer UPDATE = new StringBuffer("UPDATE pet SET ");		
		final String petName = petVO.getPetName();
			if (petName != null && !petName.trim().isEmpty()) UPDATE.append("pet_name = ?,");
		final Integer sort1Id = petVO.getSort1Id();
			if (sort1Id != null) UPDATE.append("sort1_id = ?,");
		final Integer gender = petVO.getGender();
			if (gender != null) UPDATE.append("gender = ?,");
		final String introduction = petVO.getIntroduction();
			UPDATE.append("introduction = ?,");
		final Integer pictureId = petVO.getPictureId();
			if (pictureId != null) UPDATE.append("picture_id = ?,");
		UPDATE.append("birthday = ? WHERE pet_id = ?");// 以id查
			
		if (con != null) {
			int offset = 1;
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE.toString());
				if (petName != null && !petName.trim().isEmpty()) pstmt.setString(offset++, petName.trim());
				if (sort1Id != null) pstmt.setInt(offset++, sort1Id);
				if (gender != null) pstmt.setInt(offset++, gender);
					pstmt.setString(offset++, introduction);
				if (pictureId != null) pstmt.setInt(offset++, pictureId);
					pstmt.setDate(offset++, petVO.getBirthday());
					pstmt.setInt(offset++, petVO.getPetId());
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return petVO;	
	}

//  ④ update 公開/隱藏寵物資料(前)------------------------
	@Override
	public boolean changeStatus(Integer id, Integer status) {
		con = JNDIConnection.getRDSConnection();
		boolean bool = changeStatus(id, status, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
		return bool;
	}
	/** @see #changeStatus*/
	public boolean changeStatus(Integer id, Integer status, Connection con) {
		final String CHANGESTATUS ="UPDATE pet SET status = ? WHERE pet_id = ?";		
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(CHANGESTATUS);
				int index = 1;
				pstmt.setInt(index++, status);
				pstmt.setInt(index++, id);
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return false;
		}
		return true;
	}

//  ⑤ read 查詢單筆寵物資料(前)------------------------
	@Override
	public PetVO getOneById(Integer id) {
		final String GET_ONE_PET = "SELECT pet_id, member_id, pet_name, sort1_id, gender, introduction, picture_id, birthday, status "
								 + "FROM pet WHERE pet_id = ?";
		con = JNDIConnection.getRDSConnection();
		PetVO petVO =new PetVO();
			
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_PET);
				int index = 1;
				pstmt.setInt(index, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					petVO.setPetId(rs.getInt(index++));
					petVO.setMemberId(rs.getInt(index++));
					petVO.setPetName(rs.getString(index++));
					petVO.setSort1Id(rs.getInt(index++));
					petVO.setGender(rs.getInt(index++));
					petVO.setIntroduction(rs.getString(index++));
					petVO.setPictureId(rs.getInt(index++));
					petVO.setBirthday(rs.getDate(index++));
					petVO.setStatus(rs.getInt(index++));
				}
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
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
		
//  ⑥ read 查詢一名會員的寵物資料-----------------------
	@Override
	public List<PetVO> getOneByMemberId(Integer id) {
		final String GET_ONE_MEMBER = "SELECT pet_id, member_id, pet_name, sort1_id, gender, introduction, picture_id, birthday, status "
									+ "FROM pet WHERE member_id = ?";
		con = JNDIConnection.getRDSConnection();
		List<PetVO> list = new ArrayList<PetVO>();
			
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ONE_MEMBER);
				int index = 1;
				pstmt.setInt(index, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					PetVO petVO =new PetVO();
					petVO.setPetId(rs.getInt(index++));
					petVO.setMemberId(rs.getInt(index++));
					petVO.setPetName(rs.getString(index++));
					petVO.setSort1Id(rs.getInt(index++));
					petVO.setGender(rs.getInt(index++));
					petVO.setIntroduction(rs.getString(index++));
					petVO.setPictureId(rs.getInt(index++));
					petVO.setBirthday(rs.getDate(index++));
					petVO.setStatus(rs.getInt(index++));
					list.add(petVO);
					index = 1;
				}
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
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
	
//  ⑦ read 查詢所有寵物資料(後)------------------------
	@Override
	public List<PetVO> getAll() {
		final String GET_ALL = "SELECT pet_id, member_id, pet_name, sort1_id, gender, introduction, picture_id, birthday, status FROM pet ORDER BY member_id, pet_id";
		con = JNDIConnection.getRDSConnection();
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
					petVO.setSort1Id(rs.getInt(index++));
					petVO.setGender(rs.getInt(index++));
					petVO.setIntroduction(rs.getString(index++));
					petVO.setPictureId(rs.getInt(index++));
					petVO.setBirthday(rs.getDate(index++));
					petVO.setStatus(rs.getInt(index++));
					list.add(petVO);
					index = 1;
				}
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
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
	
//  ⑧ read 查詢所有指定月份生日且狀態為公開的寵物資料(後)------
	@Override
	public List<PetVO> getAllByBirth(Integer birthMonth){
		final String GET_BIRTHMONTH_PET = "SELECT pet_id, member_id, pet_name, sort1_id, gender, introduction, picture_id, birthday, status "
										+ "FROM pet WHERE DATE_FORMAT(birthday,'%m') = ? and status = 0 ORDER BY member_id, pet_id";
		con = JNDIConnection.getRDSConnection();
				List<PetVO> list = new ArrayList<PetVO>();

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_BIRTHMONTH_PET);
				int index = 1;
				pstmt.setInt(index, birthMonth);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					PetVO petVO =new PetVO();
					petVO.setPetId(rs.getInt(index++));
					petVO.setMemberId(rs.getInt(index++));
					petVO.setPetName(rs.getString(index++));
					petVO.setSort1Id(rs.getInt(index++));
					petVO.setGender(rs.getInt(index++));
					petVO.setIntroduction(rs.getString(index++));
					petVO.setPictureId(rs.getInt(index++));
					petVO.setBirthday(rs.getDate(index++));
					petVO.setStatus(rs.getInt(index++));
					list.add(petVO);
					index = 1;
				}
			} catch (SQLException e) {
				throw new RuntimeException("A database error occured. "
						+ e.getMessage());
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
//  ⑨ create 預設寵物資料(前)------------------------
	public PetVO defaultInsert(PetVO petVO) {
		con = JNDIConnection.getRDSConnection();
		PetVO petVO2 = insert(petVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
		return petVO2;
	}
	/** @see #defaultInsert*/
	public PetVO defaultInsert(PetVO petVO, Connection con) {
		final String INSERT = "INSERT INTO pet (member_id, sort1_id, picture_id, status)"
				+ "VALUES (?, ?, ?, 0)";

		if (con != null) {
		try {
			PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			int defaultPic = 999;
			pstmt.setInt(index++, petVO.getMemberId());
			pstmt.setInt(index++, petVO.getSort1Id());
			pstmt.setInt(index++, defaultPic);
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
}
