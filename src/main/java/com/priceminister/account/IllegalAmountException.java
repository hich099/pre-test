package com.priceminister.account;

public class IllegalAmountException extends RuntimeException {

	private static final long serialVersionUID = 1445152319691281922L;

	public IllegalAmountException(String message) {
		super(message);
	}
}