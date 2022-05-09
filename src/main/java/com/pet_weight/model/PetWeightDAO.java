package com.pet_weight.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import connection.JNDIConnection;

public class PetWeightDAO implements PetWeightDAO_interface{
	Connection con;
	
//	① create 新增寵物體重紀錄(前)------------------------		
	@Override
	public PetWeightVO insert(PetWeightVO petWeightVO) {
		con = JNDIConnection.getRDSConnection();
		PetWeightVO petWeightVO2 = insert(petWeightVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
		return petWeightVO2;
	}
	/** @see #insert*/	
	public PetWeightVO insert(PetWeightVO petWeightVO, Connection con) {
		final String INSERT = "INSERT INTO pet_weight (pet_id, weight_record, record_time) VALUES (?, ?, ?)";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				int index = 1;
				pstmt.setInt(index++, petWeightVO.getPetId());
				pstmt.setBigDecimal(index++, petWeightVO.getWeightRecord());
				pstmt.setDate(index++, petWeightVO.getRecordTime());
				pstmt.execute();
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					petWeightVO.setRecordId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return petWeightVO;
	}
	
//	② delete 刪除寵物體重紀錄(前)------------------------
	@Override
	public boolean delete(PetWeightVO petWeightVO) {
		con = JNDIConnection.getRDSConnection();
		boolean boo = delete(petWeightVO, con);
		
		try {
			con.close();
			return boo;
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
	}
	/** @see #delete*/
	public boolean delete(PetWeightVO petWeightVO, Connection con) {
		final String DELETE = "DELETE FROM pet_weight WHERE record_id = ?";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				int index = 1;
				pstmt.setInt(index, petWeightVO.getRecordId());
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

//	③ update 修改寵物體重紀錄(前)------------------------	
	@Override
	public PetWeightVO update(PetWeightVO petWeightVO) {
		con = JNDIConnection.getRDSConnection();
		PetWeightVO petWeightVO2 = update(petWeightVO, con);
		
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
		return petWeightVO2;
	}
	/** @see #update*/
	public PetWeightVO update(PetWeightVO petWeightVO, Connection con) {
		final StringBuffer UPDATE = new StringBuffer("UPDATE pet_weight SET ");
		final BigDecimal weightRecord = petWeightVO.getWeightRecord();
			if (weightRecord != null) UPDATE.append("weight_record = ? ,");
		final Date recordTime = petWeightVO.getRecordTime();
			if (recordTime != null) UPDATE.append("record_time = ? ,");
		UPDATE.deleteCharAt(UPDATE.length()-1).append("WHERE record_id = ?");
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE.toString());
				int offset = 1;
				if (weightRecord != null) pstmt.setBigDecimal(offset++, weightRecord);
				if (recordTime != null) pstmt.setDate(offset++, recordTime);
				pstmt.setInt(offset++, petWeightVO.getRecordId());
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return petWeightVO;		
	}

//	④ read 查詢單筆體重紀錄(前)------------------------
	@Override
	public PetWeightVO getOneById(Integer id) {
		final String GET_ONE = "SELECT record_id, pet_id, weight_record, record_time FROM pet_weight "
				         	 + "WHERE record_id = ?";
		con = JNDIConnection.getRDSConnection();
		PetWeightVO petWeightVO =new PetWeightVO();
			
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(GET_ONE);
				int index = 1;
				pstmt.setInt(index, id);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					petWeightVO.setRecordId(rs.getInt(index++));
					petWeightVO.setPetId(rs.getInt(index++));
					petWeightVO.setWeightRecord(rs.getBigDecimal(index++));
					petWeightVO.setRecordTime(rs.getDate(index++));
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
		return petWeightVO;
	}

//	⑤ read 查詢一寵物所有體重紀錄(前)------------------------
	@Override
	public List<PetWeightVO> getOneByPetId(Integer id) {
		final String GET_ONE_PET = "SELECT record_id, pet_id, weight_record, record_time "
				 + "FROM pet_weight WHERE pet_id = ? ORDER BY record_time DESC, record_id DESC";
		con = JNDIConnection.getRDSConnection();
		List<PetWeightVO> list = new ArrayList<PetWeightVO>();
		
		if (con != null) {
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_PET);
			int index = 1;
			pstmt.setInt(index,id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PetWeightVO petWeightVO =new PetWeightVO();
				petWeightVO.setRecordId(rs.getInt(index++));
				petWeightVO.setPetId(rs.getInt(index++));
				petWeightVO.setWeightRecord(rs.getBigDecimal(index++));
				petWeightVO.setRecordTime(rs.getDate(index++));
				list.add(petWeightVO);
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

//  ⑥ read 查詢一寵物最近一筆體重紀錄(前)
    public PetWeightVO getRecentWeight(Integer id) {
    	final String GET_ONE_RECENT = "SELECT record_id, pet_id, weight_record, record_time "
				 + "FROM pet_weight WHERE pet_id = ? ORDER BY record_time DESC, record_id DESC LIMIT 1";
		con = JNDIConnection.getRDSConnection();
		PetWeightVO petWeightVO =new PetWeightVO();
		
		if (con != null) {
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_RECENT);
			int index = 1;
			pstmt.setInt(index,id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				petWeightVO.setRecordId(rs.getInt(index++));
				petWeightVO.setPetId(rs.getInt(index++));
				petWeightVO.setWeightRecord(rs.getBigDecimal(index++));
				petWeightVO.setRecordTime(rs.getDate(index++));
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
		return petWeightVO;	
    }
	
//  ⑦ read 查詢一寵物最後一季記錄之平均體重(前)    
    public BigDecimal getAverageWeight(Integer id) {
    	final String GET_QUARTERLY_AVERAGE = "SELECT weight_record "
    			+ "FROM pet_weight WHERE pet_id = ? AND (record_time BETWEEN ? AND ?) ORDER BY record_time ASC, record_id ASC";
    	PetWeightVO petWeightVO = getRecentWeight(id);// 第一次開連線
    	Date endDate = petWeightVO.getRecordTime();
    	Calendar cal = Calendar.getInstance();
    	if(endDate!=null) {
        	cal.setTime(endDate);
        }
    	cal.add(Calendar.MONTH,-3);
    	cal.add(Calendar.DAY_OF_MONTH,1);
    	Date startDate = new Date(cal.getTime().getTime());
    	con = JNDIConnection.getRDSConnection();// 第二次開連線
    	BigDecimal weightSum = new BigDecimal(0);
    	BigDecimal count = new BigDecimal(0);
    	BigDecimal weightAverage = new BigDecimal(0);
    	
		if (con != null) {
		try {
			PreparedStatement pstmt = con.prepareStatement(GET_QUARTERLY_AVERAGE);
			pstmt.setInt(1,id);
			pstmt.setDate(2, startDate);
			pstmt.setDate(3, endDate);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BigDecimal weightRecord = rs.getBigDecimal(1);
				weightSum = weightSum.add(weightRecord);
				count = count.add(new BigDecimal(1));
			}
			if(count.intValue()!=0) {
				weightAverage = weightSum.divide(count);
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
		return weightAverage;
	}

    
//	⑧ read 不提供此功能------------------------
	@Override
	public List<PetWeightVO> getAll() {
		return null;
	}

}
