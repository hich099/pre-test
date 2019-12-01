package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalAmountException;
import com.priceminister.account.IllegalBalanceException;
import com.priceminister.account.util.MessageEnum;


public class CustomerAccount implements Account {

	private Double balance;

	public CustomerAccount() {
		// Initializes account's balance to zero
		this.balance = 0.0;
	}

	public Double getBalance() {
		return balance;
	}

	public void add(Double addedAmount) {

		// The added amount should be not null
		if(addedAmount == null) {
			throw new IllegalAmountException(MessageEnum.NULL_ADDED_AMOUNT_MESSAGE.getMessage());
        }

		// The added amount should be positive
		if(addedAmount <= 0) {
			throw new IllegalAmountException(MessageEnum.ILLEGAL_ADDED_AMOUNT_MESSAGE.getMessage());
        }

        this.balance += addedAmount;
    }

	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule)
		throws IllegalBalanceException {

		if(withdrawnAmount == null) {
			throw new IllegalAmountException(MessageEnum.NULL_WITHDRAWN_AMOUNT_MESSAGE.getMessage());
		}

		if(withdrawnAmount <= 0) {
			throw new IllegalAmountException(MessageEnum.ILLEGAL_WITHDRAWN_AMOUNT_MESSAGE.getMessage());
		}

		Double resultingAccountBalance = balance - withdrawnAmount;
		if (!rule.withdrawPermitted(resultingAccountBalance)) {
			throw new IllegalBalanceException(resultingAccountBalance);
		}

		balance = resultingAccountBalance;
		return balance;
	}

}