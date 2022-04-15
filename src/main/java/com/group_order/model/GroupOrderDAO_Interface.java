package com.group_order.model;

import java.util.List;

import com.common.model.JDBCDAO_Interface;

public interface GroupOrderDAO_Interface extends JDBCDAO_Interface<GroupOrderVO> {

	// 用商品id找所有團購訂單
	public List<GroupOrderVO> getAllByProductId(Integer id);

	// 用商品id找所有進行中團購訂單
	public List<GroupOrderVO> getAllInProgressByProductId(Integer id);
	
	// 找所有進行中團購訂單
	public List<GroupOrderVO> getAllInProgress();

	// 修改團購訂單截止時間(如果訂單以數量結單，且在最後截止時間達標)
	public Integer updateEndTimeByGroupOrderId(Integer id);

	// 修改團購訂單狀態
	public Integer updateStatusByGroupOrderId(Integer id, Integer status);
}
