package com.pet_activity.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.remind.model.RemindVO;

import connection.JNDIConnection;

public class PetActivityDAO implements PetActivityDAO_interface{
	Connection con;
	
//	① create 新增寵物活動紀錄(前)------------------------		
	@Override
	public PetActivityVO insert(PetActivityVO petActivityVO) {
		con = JNDIConnection.getRDSConnection();
		PetActivityVO petActivityVO2 = insert(petActivityVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
		return petActivityVO2;
	}
	/** @see #insert*/	
	public PetActivityVO insert(PetActivityVO petActivityVO, Connection con) {
		final String INSERT = "INSERT INTO pet_activity (pet_id, activity, record_time) VALUES (?, ?, ?)";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setInt(index++, petActivityVO.getPetId());
				pstmt.setString(index++, petActivityVO.getActivity().trim());
				pstmt.setDate(index++, petActivityVO.getRecordTime());
				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					petActivityVO.setRecordId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return petActivityVO;
	}

//	② create 將提醒轉為寵物活動紀錄(前)------------------------	
	@Override
	public PetActivityVO convert(RemindVO remindVO) {
		// TODO 需要JOIN Pet得到 member_id
		return null;
	}
	
//	③ delete 刪除寵物活動紀錄(前)------------------------
	@Override
	public boolean delete(PetActivityVO petActivityVO) {
		con = JNDIConnection.getRDSConnection();
		boolean boo = delete(petActivityVO, con);
		
		try {
			con.close();
			return boo;
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
	}
	/** @see #delete*/
	public boolean delete(PetActivityVO petActivityVO, Connection con) {
		final String DELETE = "DELETE FROM pet_activity WHERE record_id = ?";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				int index = 1;
				pstmt.setInt(index, petActivityVO.getRecordId());
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

//	④ update 修改寵物活動紀錄(前)------------------------	
	@Override
	public PetActivityVO update(PetActivityVO petActivityVO) {
		con = JNDIConnection.getRDSConnection();
		PetActivityVO petActivityVO2 = update(petActivityVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
		return petActivityVO2;
	}
	/** @see #update*/
	public PetActivityVO update(PetActivityVO petActivityVO, Connection con) {
		final StringBuffer UPDATE = new StringBuffer("UPDATE pet_activity SET ");
		final String activity = petActivityVO.getActivity();
			if (activity != null && !activity.trim().isEmpty()) UPDATE.append("activity = ?,");
		final Date recordTime = petActivityVO.getRecordTime();
			if (recordTime != null) UPDATE.append("record_time = ?,");
		UPDATE.deleteCharAt(UPDATE.length()-1).append("WHERE record_id = ?");
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE.toString());
				int offset = 1;
				if (activity != null && !activity.trim().isEmpty()) pstmt.setString(offset++, activity.trim());
				if (recordTime != null) pstmt.setDate(offset++, recordTime);
				pstmt.setInt(offset++, petActivityVO.getRecordId());
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return petActivityVO;		
	}

//	⑤ read 查詢單筆活動紀錄(前)------------------------
	@Override
	public PetActivityVO getOneById(Integer id) {
		final String GET_ONE = "SELECT record_id, pet_id, activity, record_time FROM pet_activity "
				         	 + "WHERE record_id = ?";
		con = JNDIConnection.getRDSConnection();
		PetActivityVO petActivityVO =new PetActivityVO();
			
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ONE);
				int index = 1;
				pstmt.setInt(index, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					petActivityVO.setRecordId(rs.getInt(index++));
					petActivityVO.setPetId(rs.getInt(index++));
					petActivityVO.setActivity(transInner(rs.getString(index++)));
					petActivityVO.setRecordTime(rs.getDate(index++));
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
		return petActivityVO;
	}

//	⑥ read 查詢一寵物所有活動紀錄(前)------------------------
	@Override
	public List<PetActivityVO> getOneByPetId(Integer id) {
		final String GET_ONE_PET = "SELECT record_id, pet_id, activity, record_time "
				 + "FROM pet_activity WHERE pet_id = ? ORDER BY record_time DESC, record_id DESC";
		con = JNDIConnection.getRDSConnection();
		List<PetActivityVO> list = new ArrayList<PetActivityVO>();
		
		if (con != null) {
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_PET);
			int index = 1;
			pstmt.setInt(index,id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PetActivityVO petActivityVO =new PetActivityVO();
				petActivityVO.setRecordId(rs.getInt(index++));
				petActivityVO.setPetId(rs.getInt(index++));
				petActivityVO.setActivity(transOuter(rs.getString(index++)));
				petActivityVO.setRecordTime(rs.getDate(index++));
				list.add(petActivityVO);
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
	
//	⑦ read 查詢一寵物最近4筆活動紀錄(前)------------------------
	@Override
	public List<PetActivityVO> getFourByPetId(Integer id) {
		final String GET_FOUR_PET = "SELECT record_id, pet_id, activity, record_time "
				 + "FROM pet_activity WHERE pet_id = ? ORDER BY record_time DESC, record_id DESC LIMIT 4";
		con = JNDIConnection.getRDSConnection();
		List<PetActivityVO> list = new ArrayList<PetActivityVO>();
		
		if (con != null) {
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_FOUR_PET);
			int index = 1;
			pstmt.setInt(index,id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PetActivityVO petActivityVO =new PetActivityVO();
				petActivityVO.setRecordId(rs.getInt(index++));
				petActivityVO.setPetId(rs.getInt(index++));
				petActivityVO.setActivity(transOuter(rs.getString(index++)));
				petActivityVO.setRecordTime(rs.getDate(index++));
				list.add(petActivityVO);
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
	
//	⑧ read 不提供此功能------------------------
	@Override
	public List<PetActivityVO> getAll() {
		return null;
	}
	/**TODO 文字換行顯示用*/
	public String transOuter(String str) { 
		while (str.indexOf("<") != -1) { 
			  str = str.substring(0, str.indexOf("<")) + "&lt;" 
			    + str.substring(str.indexOf("<") + 1); 
			 }
		while (str.indexOf(">") != -1) { 
			  str = str.substring(0, str.indexOf(">")) + "&gt;" 
			    + str.substring(str.indexOf(">") + 1); 
			 }
		 return str; 
	}
	public String transInner(String str) { 
		while (str.indexOf("<") != -1) { 
			  str = str.substring(0, str.indexOf("<")) + "&lt;" 
			    + str.substring(str.indexOf("<") + 1); 
			 }
		while (str.indexOf(">") != -1) { 
			  str = str.substring(0, str.indexOf(">")) + "&gt;" 
			    + str.substring(str.indexOf(">") + 1); 
			 }
		while (str.indexOf("\r") != -1) { 
		  str = str.substring(0, str.indexOf("\r")) 
		    + str.substring(str.indexOf("\r") + 1); 
		 } 
		 while (str.indexOf("\n") != -1) { 
			  str = str.substring(0, str.indexOf("\n")) + "<br/>" 
			    + str.substring(str.indexOf("\n") + 1); 
			 }
		 while (str.indexOf(" ") != -1) { 
		  str = str.substring(0, str.indexOf(" ")) + "&nbsp;" 
		    + str.substring(str.indexOf(" ") + 1); 
		 } 
		 return str; 
	}
	
}
