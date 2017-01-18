package org.bj.bootcamp;

import org.junit.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BankAccountShould {

    public static final int SMALL_AMOUNT = 500;
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
        bankAccount.deposit(SMALL_AMOUNT);

        assertThat(bankAccount.getBalance(), is(previousBalance + SMALL_AMOUNT));
    }

    @Test public void
    decrease_balance_by_withdrawal_amount(){
        int previousBalance = bankAccount.getBalance();
        bankAccount.withdraw(SMALL_AMOUNT);

        assertThat(bankAccount.getBalance(), is(previousBalance - SMALL_AMOUNT));
    }

    @Test public void
    generate_transaction_for_deposit(){
        bankAccount.deposit(SMALL_AMOUNT);

        TransactionRecord expectedResult = new TransactionRecord(localDateTime, SMALL_AMOUNT, SMALL_AMOUNT);
        assertThat(bankAccount.getTransactions(), contains(expectedResult));
    }

    @Test public void
    generate_transaction_for_withdrawal_while_start_balance_is_zero(){
        bankAccount.withdraw(SMALL_AMOUNT);

        TransactionRecord expectedResult = new TransactionRecord(localDateTime, -SMALL_AMOUNT, -SMALL_AMOUNT);
        assertThat(bankAccount.getTransactions(), contains(expectedResult));
    }

    @Test public void
    generate_transactions_for_multiple_operations(){
        int deposit = 1000;
        bankAccount.deposit(deposit);
        int withdrawal = 100;
        bankAccount.withdraw(withdrawal);
        bankAccount.deposit(SMALL_AMOUNT);

        TransactionRecord r1 = new TransactionRecord(localDateTime, deposit, deposit);
        TransactionRecord r2 = new TransactionRecord(localDateTime, -withdrawal, deposit-withdrawal);
        TransactionRecord r3 = new TransactionRecord(localDateTime, SMALL_AMOUNT, deposit-withdrawal+ SMALL_AMOUNT);
        List<TransactionRecord> expectedResult = asList(r1, r2, r3);
        assertThat(bankAccount.getTransactions(), contains(expectedResult));
    }

    @Test public void
    generate_formatted_transactions_for_multiple_operations(){
        int deposit = 1000;
        bankAccount.deposit(deposit);
        int withdrawal = 100;
        bankAccount.withdraw(withdrawal);
        bankAccount.deposit(SMALL_AMOUNT);

        TransactionRecord r1 = new TransactionRecord(localDateTime, deposit, deposit);
        TransactionRecord r2 = new TransactionRecord(localDateTime, -withdrawal, deposit-withdrawal);
        TransactionRecord r3 = new TransactionRecord(localDateTime, SMALL_AMOUNT, deposit-withdrawal+ SMALL_AMOUNT);

        List<String> expectedResult = asList(r1.toString(), r2.toString(), r3.toString());

        Statement statement = new Statement(bankAccount.getTransactions());
        assertThat(statement.getTransactionStrings(), contains(expectedResult));
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
