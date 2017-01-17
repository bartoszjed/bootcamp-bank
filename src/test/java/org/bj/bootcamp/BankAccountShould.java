package org.bj.bootcamp;

import org.junit.*;

import java.time.LocalDate;

import static java.util.function.Predicate.isEqual;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class BankAccountShould {

 private BankAccountImpl bankAccount;

    @Before
    public void setUp() throws Exception {
        bankAccount = new BankAccountImpl();
    }

    @Test
    public void
    print_statement_header_data() {
        String statementLine = bankAccount.getstatement();
        assertThat(statementLine, is("DATE   | AMOUNT   | BALANCE"));

    }

    @Test public void
    increase_balance_by_deposit_amount(){
        int previousBalance = bankAccount.getBalance();
        int depositAmount = 10;
        bankAccount.deposit(depositAmount);

        assertThat(bankAccount.getBalance(), is(previousBalance + depositAmount));
    }

    @Test public void
    decrease_balance_by_withdrawal_amount(){
        int previousBalance = bankAccount.getBalance();
        int withdrawAmmount = 10;
        bankAccount.withdraw(withdrawAmmount);

        assertThat(bankAccount.getBalance(), is(previousBalance - withdrawAmmount));
    }

    @Test public void
    generate_transaction_for_deposit(){
        int deposit = 500;
        bankAccount.deposit(deposit);
        String localDate = LocalDate.now().toString();
        TransactionRecord expectedResult = new TransactionRecord(localDate, deposit, deposit);

        assertEquals (expectedResult, bankAccount.getTransactions().get(0));
    }
}
