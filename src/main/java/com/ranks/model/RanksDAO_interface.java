package com.ranks.model;

import java.util.List;
import com.ranks.model.RanksVO;

public interface RanksDAO_interface {

	public void insert(RanksVO ranksVO);

	public void update(RanksVO ranksVO);

	public void delete(Integer rank_id);

	public RanksVO findByPrimaryKey(Integer rank_id);

	public List<RanksVO> getAll();

}
