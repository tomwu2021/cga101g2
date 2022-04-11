package com.remind.model;

import java.util.List;
import com.remind.model.RemindVO;

public interface RemindDVO_interface {

	public void insert(RemindVO RemindVO);

	public void update(RemindVO RemindVO);

	public void delete(Integer remind_id);

	public RemindVO findByPrimaryKey(Integer remind_id);

	public List<RemindVO> getAll();

}
