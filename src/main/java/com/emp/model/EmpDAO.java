package com.emp.model;

import java.sql.*;
import java.util.*;
import connection.JNDIConnection;

public class EmpDAO implements EmpDAO_interface {

	Connection con;

// 情境一 insert：新增一筆員工資料 --------------------------------------------------
	@Override
	public EmpVO insert(EmpVO empVO) {
		con = JNDIConnection.getRDSConnection();
		EmpVO empVO2 = insert(empVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empVO2;
	}

	public EmpVO insert(EmpVO empVO, Connection con) {
		final String INSERT_STMT = "INSERT INTO emp(emp_name, account,password,status) values(?,?,?,1);";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, empVO.getAccount());
				pstmt.setString(2, empVO.getAccount());
				pstmt.setString(3, empVO.getPassword());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					empVO.setEmpNo(rs.getInt(1));
				}
				return empVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

// 情境二 update：員工修改資料 --------------------------------------------------
	@Override
	public EmpVO update(EmpVO empVO) {
		con = JNDIConnection.getRDSConnection();
		EmpVO empVO2 = update(empVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empVO2;
	}

	public EmpVO update(EmpVO empVO, Connection con) {

		final StringBuilder UPDATE = new StringBuilder().append("UPDATE emp SET ");

		final String empName = empVO.getEmpName();
		final String account = empVO.getAccount();
		final String password = empVO.getPassword();

		if (empName != null && !empName.isEmpty()) {
			UPDATE.append("emp_name = ?,");
		}
		if (account != null && !account.isEmpty()) {
			UPDATE.append("account = ?,");
		}
		if (password != null && !password.isEmpty()) {
			UPDATE.append("password = ?,");
		}

		UPDATE.append("status = 1 ").append("where emp_no = ?");

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(UPDATE.toString());
				int offset = 1;

				if (empName != null && !empName.isEmpty()) {
					pstmt.setString(offset, empVO.getEmpName());
					offset += 1;
				}
				if (account != null && !account.isEmpty()) {
					pstmt.setString(offset, empVO.getAccount());
					offset += 1;
				}
				if (password != null && !password.isEmpty()) {
					pstmt.setString(offset, empVO.getPassword());
					offset += 1;
				}

				pstmt.setInt(offset, empVO.getEmpNo());
				pstmt.executeUpdate();
				return empVO;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

// 情境三 select：使用 empNo 查詢某一筆員工資料 -----------------------------------------------
	@Override
	public EmpVO getOneById(Integer id) {
		con = JNDIConnection.getRDSConnection();
		EmpVO empVO2 = getOneById(id, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empVO2;
	}

	public EmpVO getOneById(Integer id, Connection con) {
		final String SELECT_ONE_BYID = "SELECT emp_no, emp_name, account, create_time, status FROM emp where emp_no = ?;";

		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(SELECT_ONE_BYID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					EmpVO newEmp = new EmpVO();
					newEmp.setEmpNo(rs.getInt("emp_no"));
					newEmp.setEmpName(rs.getString("emp_name"));
					newEmp.setAccount(rs.getString("account"));
					newEmp.setCreateTime(rs.getTimestamp("create_time"));
					newEmp.setStatus(rs.getInt("status"));

					return newEmp;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

// 情境四 select：查詢全部員工資料 --------------------------------------------------
	@Override
	public List<EmpVO> getAll() {

		final String GETALL = "SELECT emp_no, emp_name, account, create_time, status FROM emp;";

		try (Connection con = JNDIConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETALL)) {
			ResultSet rs = pstmt.executeQuery();

			List<EmpVO> list = new ArrayList<>();
			while (rs.next()) {
				EmpVO newEmp = new EmpVO();
				newEmp.setEmpNo(rs.getInt("emp_no"));
				newEmp.setEmpName(rs.getString("emp_name"));
				newEmp.setAccount(rs.getString("account"));
				newEmp.setCreateTime(rs.getTimestamp("create_time"));
				newEmp.setStatus(rs.getInt("status"));
				list.add(newEmp);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

// 情境五 select：使用 account 查詢某一筆員工資料
	@Override
	public EmpVO getOneByAccount(String account) {

		final String SELECT_ONE_BY_EMPNAME = "SELECT emp_no, emp_name, account, password, create_time, status FROM emp where account = ?;";
		try (Connection con = JNDIConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(SELECT_ONE_BY_EMPNAME);) {

			pstmt.setString(1, account);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				EmpVO newEmp = new EmpVO();
				newEmp.setEmpNo(rs.getInt("emp_no"));
				newEmp.setEmpName(rs.getString("emp_name"));
				newEmp.setAccount(rs.getString("account"));
				newEmp.setPassword(rs.getString("password"));
				newEmp.setCreateTime(rs.getTimestamp("create_time"));
				newEmp.setStatus(rs.getInt("status"));
				return newEmp;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

// 情境六 update：修改員工的狀態
	@Override
	public boolean changeStatus(EmpVO empVO) {

		con = JNDIConnection.getRDSConnection();
		EmpVO empVO2 = getOneById(empVO.getEmpNo(), con);

		if (empVO.getEmpName() == null) {
			return false;
		}

		Boolean b = changeStatus(empVO2, empVO.getStatus(), con);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean changeStatus(EmpVO empVO, Integer status, Connection con) {

		final String CHANGE_STATUS = "UPDATE emp set status=? where emp_no = ?;";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(CHANGE_STATUS);
				pstmt.setInt(1, status);
				pstmt.setInt(2, empVO.getEmpNo());
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(EmpVO t) {
		return false;
	}

}
