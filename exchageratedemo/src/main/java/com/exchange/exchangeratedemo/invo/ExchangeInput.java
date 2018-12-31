package com.exchange.exchangeratedemo.invo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExchangeInput {

	@JsonProperty
	private String dollar;
	
	@JsonProperty
	private String division;

	public String getDollar() {
		return dollar;
	}

	public void setDollar(String dollar) {
		this.dollar = dollar;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}
	
	
	
}
