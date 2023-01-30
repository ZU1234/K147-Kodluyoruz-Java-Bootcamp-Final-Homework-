package com.biletx.client.enums;

public enum CreditCardType {
	MasterCard("Mastercard"), Visa("Visa");

	private String description;

	CreditCardType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
