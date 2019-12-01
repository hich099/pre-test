package com.priceminister.account.util;

public enum MessageEnum {

	ILLEGAL_ADDED_AMOUNT_MESSAGE("The amount to be added is zero or negative"),

	NULL_ADDED_AMOUNT_MESSAGE("The amount to be added is null"),

	ILLEGAL_WITHDRAWN_AMOUNT_MESSAGE("The amount to be withdrawn is zero or negative"),

	NULL_WITHDRAWN_AMOUNT_MESSAGE("The amount to be withdrawn is null");

	private String message;

	private MessageEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
