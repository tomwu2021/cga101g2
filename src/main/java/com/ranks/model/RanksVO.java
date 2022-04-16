package com.ranks.model;

import java.math.BigDecimal;

public class RanksVO implements java.io.Serializable {

	private Integer rankId;
	private String rankName;
	private Integer chargeAmount;
	private BigDecimal discount;
	private Integer bonus;

	public RanksVO() {
	}

	public Integer getRankId() {
		return rankId;
	}

	public void setRankId(Integer rankId) {
		this.rankId = rankId;
	}

	public String getRankName() {
		return rankName;
	}

	public void setRankName(String rankName) {
		this.rankName = rankName;
	}

	public Integer getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(Integer chargeAmount) {
		this.chargeAmount = chargeAmount;
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

	@Override
	public String toString() {
		return "RanksVO [rankId=" + rankId + ", rankName=" + rankName + ", chargeAmount=" + chargeAmount + ", discount="
				+ discount + ", bonus=" + bonus + "]";
	}

}
