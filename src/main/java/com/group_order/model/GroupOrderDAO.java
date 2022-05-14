package com.group_order.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GroupOrderDAO implements GroupOrderDAO_Interface{

	@Override
	public GroupOrderVO insert(GroupOrderVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(GroupOrderVO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GroupOrderVO update(GroupOrderVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GroupOrderVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupOrderVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupOrderVO> getAllByProductId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateEndTimeByGroupOrderId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateStatusByGroupOrderId(Integer id, Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupOrderVO> getAllInProgress2(Connection con) {
		return null;
	}

	@Override
	public List<GroupOrderVO> getAllInProgressByProductId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupOrderVO> getAllInProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GroupOrderVO> getAllInProgress(Connection con) throws SQLException {
		return null;
	}

}
