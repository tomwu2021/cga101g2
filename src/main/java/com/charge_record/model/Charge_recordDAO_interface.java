package com.charge_record.model;

import java.util.List;
import com.members.model.MembersVO;

public interface Charge_recordDAO_interface {

	public void insert(Charge_recordVO charge_recordVO);

	public void update(Charge_recordVO charge_recordVO);

	public void delete(Integer record_id);

	public MembersVO findByPrimaryKey(Integer record_id);

	public List<Charge_recordVO> getAll();

}
