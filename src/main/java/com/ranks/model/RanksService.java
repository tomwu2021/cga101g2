package com.ranks.model;

import java.util.*;

public class RanksService {

	private RanksDAO_interface dao;

	public RanksService() {
		dao = new RanksJDBCDAO();
	}

	// 情境一 update：修改 Ranks 的 rank_name
	public RanksVO update(RanksVO ranksVO) {
		return dao.update(ranksVO);
	}

	// 情境二 select：查詢所有會員等級
	public List<RanksVO> getAll() {
		return dao.getAll();
	}

}
