package com.priceminister.account;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;
import com.priceminister.account.util.MessageEnum;


/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass.
 * Then focus on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send it to recrutement-dev@priceminister.com
 * 
 */
public class CustomerAccountTest {
    
    Account customerAccount;
    AccountRule rule;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        customerAccount = new CustomerAccount();
    }
    
    /**
     * Tests that an empty account always has a balance of 0.0, not a NULL.
     */
    @Test
    public void testAccountWithoutMoneyHasZeroBalance() {
    	assertEquals(new Double(0.0), customerAccount.getBalance());
    }
    
    /**
     * Adds money to the account and checks that the new balance is as expected.
     */
    @Test
    public void testAddPositiveAmount() {
        customerAccount.add(2.0);

        assertEquals(new Double(2.0), customerAccount.getBalance());
    }

    /**
     * Adds negative money to the account and checks that an exception is raised.
     */
    @Test
    public void testAddNegativeAmount() {

    	thrown.expect(IllegalAmountException.class);
        thrown.expectMessage(is(MessageEnum.ILLEGAL_ADDED_AMOUNT_MESSAGE.getMessage()));

        customerAccount.add(-1.0);
    }

    /**
     * Adds zero amount of money to the account and checks that an exception is raised.
     */
    @Test
    public void testAddZeroAmount() {

    	thrown.expect(IllegalAmountException.class);
        thrown.expectMessage(is(MessageEnum.ILLEGAL_ADDED_AMOUNT_MESSAGE.getMessage()));

        customerAccount.add(0.0);
    }

    /**
     * Adds null to the account and checks that an exception is raised.
     */
    @Test
    public void testAddNullAmount() {

    	thrown.expect(IllegalAmountException.class);
        thrown.expectMessage(is(MessageEnum.NULL_ADDED_AMOUNT_MESSAGE.getMessage()));

        customerAccount.add(null);
    }

    /**
     * Tests that an illegal withdrawal throws the expected exception.
     */
    @Test
    public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {

    	thrown.expect(IllegalBalanceException.class);

        rule = new CustomerAccountRule();
    	customerAccount.withdrawAndReportBalance(3.0, rule);
    }

    /**
     * Withdrawals negative amount from the account and checks that an exception is raised.
     * @throws IllegalBalanceException 
     */
    @Test
    public void testWithdrawalNegativeAmount() throws IllegalBalanceException {

    	thrown.expect(IllegalAmountException.class);
        thrown.expectMessage(is(MessageEnum.ILLEGAL_WITHDRAWN_AMOUNT_MESSAGE.getMessage()));

        customerAccount.withdrawAndReportBalance(-1.0, rule);
    }

    /**
     * Withdrawals zero amount from the account and checks that an exception is raised.
     * @throws IllegalBalanceException 
     */
    @Test
    public void testWithdrawalZeroAmount() throws IllegalBalanceException {

    	thrown.expect(IllegalAmountException.class);
        thrown.expectMessage(is(MessageEnum.ILLEGAL_WITHDRAWN_AMOUNT_MESSAGE.getMessage()));

        customerAccount.withdrawAndReportBalance(0.0, rule);
    }

    /**
     * Withdrawals null from the account and checks that an exception is raised.
     * @throws IllegalBalanceException 
     */
    @Test
    public void testWithdrawalNullAmount() throws IllegalBalanceException {

    	thrown.expect(IllegalAmountException.class);
        thrown.expectMessage(is(MessageEnum.NULL_WITHDRAWN_AMOUNT_MESSAGE.getMessage()));

        customerAccount.withdrawAndReportBalance(null, rule);
    }

    /**
     * Tests Withdraw amount of money from account's balance.
     */
    @Test
    public void testWithdraw() throws IllegalBalanceException {

		customerAccount.add(5.0);

		rule = new CustomerAccountRule();
		customerAccount.withdrawAndReportBalance(3.0, rule);

		assertEquals(new Double(2.0), customerAccount.getBalance());

    }

}
