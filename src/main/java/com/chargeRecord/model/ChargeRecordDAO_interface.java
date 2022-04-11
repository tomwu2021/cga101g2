package com.chargeRecord.model;

import java.util.List;
import com.members.model.MembersVO;

public interface ChargeRecordDAO_interface {

	public void insert(ChargeRecordVO charge_recordVO);

	public void update(ChargeRecordVO charge_recordVO);

	public void delete(Integer record_id);

	public MembersVO findByPrimaryKey(Integer record_id);

	public List<ChargeRecordVO> getAll();

}
