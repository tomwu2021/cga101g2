package com.emp_authority.model;

import java.sql.*;
import java.util.*;
import connection.JDBCConnection;

public class EmpAuthorityJDBCDAO implements EmpAuthorityDAO_interface {

	Connection con;
	
	// 情境一 insert：新增員工權限
	@Override
	public EmpAuthorityVO insert(EmpAuthorityVO empAuthorityVO) {
		con = JDBCConnection.getRDSConnection();
		EmpAuthorityVO empAuthorityVO2 = insert(empAuthorityVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empAuthorityVO2;
	}

	public EmpAuthorityVO insert(EmpAuthorityVO empAuthorityVO, Connection con) {
		final String INSERT_STMT = "INSERT INTO emp_authority(emp_no, function_id) values(?,?);";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, empAuthorityVO.getEmpNo());
				pstmt.setInt(2, empAuthorityVO.getFunctionId());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					empAuthorityVO.setEmpNo(rs.getInt(1));
				}
				return empAuthorityVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 情境二 delete：刪除一筆員工權限
	@Override
	public boolean delete(EmpAuthorityVO empAuthorityVO) {
		con = JDBCConnection.getRDSConnection();
		boolean boo = delete(empAuthorityVO, con);
		
		try {
			con.close();
			return boo;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(EmpAuthorityVO empAuthorityVO, Connection con) {
		final String DELETE = "DELETE FROM emp_authority WHERE emp_no = ? and function_id = ?";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				pstmt.setInt(1, empAuthorityVO.getEmpNo());
				pstmt.setInt(2, empAuthorityVO.getFunctionId());
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
	public EmpAuthorityVO update(EmpAuthorityVO empAuthorityVO) {
		return null;
	}

	@Override
	public EmpAuthorityVO getOneById(Integer id) {
		return null;
	}

	@Override
	public List<EmpAuthorityVO> getAll() {
		return null;
	}

	// 情境三：查詢某員工所有權限編號
	@Override
	public List<EmpAuthorityVO> getAllByEmpNo(Integer empNO) {
		final String GETALL = "SELECT emp_no, function_id FROM emp_authority where emp_no = ?;";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETALL)) {
			
			pstmt.setInt(1, empNO);
			ResultSet rs = pstmt.executeQuery();

			List<EmpAuthorityVO> list = new ArrayList<>();
			while (rs.next()) {
				EmpAuthorityVO newEmpAuthority = new EmpAuthorityVO();
				newEmpAuthority.setEmpNo(rs.getInt("emp_no"));
				newEmpAuthority.setFunctionId(rs.getInt("function_id"));
				list.add(newEmpAuthority);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	// 情境四：查詢某權限所有員工編號
	@Override
	public List<EmpAuthorityVO> getAllByFunctionId(Integer functionId) {
		final String GETALL = "SELECT emp_no, function_id FROM emp_authority where function_id = ?;";

		try (Connection con = JDBCConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETALL)) {
			
			pstmt.setInt(1, functionId);
			ResultSet rs = pstmt.executeQuery();

			List<EmpAuthorityVO> list = new ArrayList<>();
			while (rs.next()) {
				EmpAuthorityVO newEmpAuthority = new EmpAuthorityVO();
				newEmpAuthority.setEmpNo(rs.getInt("emp_no"));
				newEmpAuthority.setFunctionId(rs.getInt("function_id"));
				list.add(newEmpAuthority);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
