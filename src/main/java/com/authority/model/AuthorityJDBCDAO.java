package com.authority.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.JDBCConnection;

public class AuthorityJDBCDAO implements AuthorityDAO_interface{
	
	Connection con;
	
	@Override
	public AuthorityVO insert(AuthorityVO authorityVO) {
		con = JDBCConnection.getRDSConnection();
		AuthorityVO authorityVO2 = insert(authorityVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authorityVO2;
	}
	
	public AuthorityVO insert(AuthorityVO authorityVO, Connection con) {
		final String INSERT ="insert into authority (function_name) values (?)";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, authorityVO.getFunctionName());
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					authorityVO.setFunctionId(rs.getInt(1));
				}
				return authorityVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public boolean delete(AuthorityVO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AuthorityVO update(AuthorityVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthorityVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthorityVO> getAll() {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		
		final String GETAll = "select function_id, function_name from authority;";
		
		try(Connection con =JDBCConnection.getRDSConnection();
			PreparedStatement pstmt = con.prepareStatement(GETAll);
			ResultSet rs = pstmt.executeQuery();){
			
			while(rs.next()) {
				AuthorityVO authorityVO = new AuthorityVO();
				authorityVO.setFunctionId(rs.getInt("function_id"));
				authorityVO.setFunctionName(rs.getString("function_name"));
				
				list.add(authorityVO);	
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}	
}
