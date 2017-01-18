package org.bj.bootcamp;

import org.junit.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BankAccountShould {

 private BankAccountImpl bankAccount;
 private String localDateTime;


    @Before
    public void setUp() throws Exception {
        bankAccount = new BankAccountImpl();
        localDateTime = LocalDate.now().toString();
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
    generate_transaction_for_deposit_while_start_balance_is_zero(){
        int deposit = 500;
        bankAccount.deposit(deposit);

        TransactionRecord expectedResult = new TransactionRecord(localDateTime, deposit, deposit);
        assertThat(bankAccount.getTransactions().get(0), is(expectedResult));
    }

    @Test public void
    generate_transaction_for_withdrawal_while_start_balance_is_zero(){
        int withdrawal = 500;
        bankAccount.withdraw(withdrawal);


        TransactionRecord expectedResult = new TransactionRecord(localDateTime, -withdrawal, -withdrawal);
        assertThat(bankAccount.getTransactions().get(0), is(expectedResult));
    }

    @Test public void
    generate_transactions_for_multiple_operations(){
        int deposit = 500;
        bankAccount.deposit(deposit);

        int withdrawal = 100;
        bankAccount.withdraw(withdrawal);

        List<TransactionRecord> expectedResult = new ArrayList<>();
        expectedResult.add(new TransactionRecord(localDateTime, deposit, deposit));
        expectedResult.add(new TransactionRecord(localDateTime, -withdrawal, deposit-withdrawal));

        assertThat(bankAccount.getTransactions(), is(expectedResult));
    }

    @Test public void
    generate_formatted_transactions_for_multiple_operations(){
        int deposit = 1000;
        bankAccount.deposit(deposit);

        int withdrawal = 100;
        bankAccount.withdraw(withdrawal);

        int deposit2 = 500;
        bankAccount.deposit(deposit2);

        List<String> expectedResult = new ArrayList<>();
        TransactionRecord r1 = new TransactionRecord(localDateTime, deposit, deposit);
        TransactionRecord r2 = new TransactionRecord(localDateTime, -withdrawal, deposit-withdrawal);
        TransactionRecord r3 = new TransactionRecord(localDateTime, deposit2, deposit-withdrawal+deposit2);

        expectedResult.add(r1.toString());
        expectedResult.add(r2.toString());
        expectedResult.add(r3.toString());

        Statement statement = new Statement(bankAccount.getTransactions());
        //assertEquals (expectedResult, statement.getTransactionStrings());
        assertThat(expectedResult, is(statement.getTransactionStrings()));
    }

    @Test public void
    get_statement_header_string() {
        Statement statement = new Statement(bankAccount.getTransactions());
        String statementLine = statement.getStatementContent();
        assertThat(Statement.STATEMENT_HEADER, is(statementLine));
    }

    @Test public void
    get_statement_multi_line_content () {
        Statement statement = new Statement(bankAccount.getTransactions());

        int deposit = 1000;
        bankAccount.deposit(deposit);

        int withdrawal = 100;
        bankAccount.withdraw(withdrawal);

        TransactionRecord r1 = new TransactionRecord(localDateTime, deposit, deposit);
        TransactionRecord r2 = new TransactionRecord(localDateTime, -withdrawal, deposit-withdrawal);

        String expectedStatement = Statement.STATEMENT_HEADER + Statement.NEW_LINE + r1.toString() + Statement.NEW_LINE + r2.toString();
        assertThat(statement.getStatementContent(), is(expectedStatement));
    }
}
