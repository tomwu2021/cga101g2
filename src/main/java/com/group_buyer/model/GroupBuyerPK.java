package com.group_buyer.model;

import java.io.Serializable;
import java.util.Objects;

public class GroupBuyerPK implements Serializable {
	private Integer groupOrderId;
	private Integer memberId;

	public GroupBuyerPK() {
		// TODO Auto-generated constructor stub
	}

	public GroupBuyerPK(Integer groupOrderId, Integer memberId) {
		this.groupOrderId = groupOrderId;
		this.memberId = memberId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(groupOrderId, memberId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		GroupBuyerPK pk = (GroupBuyerPK) obj;
		return Objects.equals(groupOrderId, pk.groupOrderId) && Objects.equals(memberId, pk.memberId);
	}

}
