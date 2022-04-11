package com.ranks.model;

import java.math.BigDecimal;

public class RanksVO implements java.io.Serializable {

	private Integer rank_id;
	private String rank_name;
	private Integer charge_amount;
	private BigDecimal discount;
	private Integer bonus;

	public RanksVO() {
	}

	public Integer getRank_id() {
		return rank_id;
	}

	public void setRank_id(Integer rank_id) {
		this.rank_id = rank_id;
	}

	public String getRank_name() {
		return rank_name;
	}

	public void setRank_name(String rank_name) {
		this.rank_name = rank_name;
	}

	public Integer getCharge_amount() {
		return charge_amount;
	}

	public void setCharge_amount(Integer charge_amount) {
		this.charge_amount = charge_amount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getBonus() {
		return bonus;
	}

	public void setBonus(Integer bonus) {
		this.bonus = bonus;
	}

}
