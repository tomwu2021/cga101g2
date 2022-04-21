package com.group_buyer.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
//@Entity
//@Table(name = "group_buyer")
public class GroupBuyerPK implements Serializable{
//	@Column(name = "group_order_id")
	 private Integer groupOrderId;
//	@Column(name = "member_id")
	 private Integer memberId;
	 
	 public GroupBuyerPK() {
		// TODO Auto-generated constructor stub
	}
	 public GroupBuyerPK(Integer groupOrderId,Integer memberId){
		 this.groupOrderId=groupOrderId;
		 this.memberId=memberId;
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
