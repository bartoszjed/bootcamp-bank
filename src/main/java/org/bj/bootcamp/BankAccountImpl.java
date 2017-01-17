package org.bj.bootcamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartosz Jedrzejczak on 17/01/2017.
 */
public class BankAccountImpl {

    private Integer balance;
    private List<TransactionRecord> transactionList = new ArrayList<>();
    public static String STATEMENT_HEADER = "DATE       | AMOUNT | BALANCE";

    public BankAccountImpl() {
        this.balance = 0;
    }

    public void printStatement() {
        System.out.println(getStatementContent());
    }

    public String getStatementContent(){
        final String NEW_LINE = System.getProperty("line.separator");


        String result = STATEMENT_HEADER;

        for(String line: getTransactionStrings()){
            result.concat(NEW_LINE);
            result.concat(line);
        }
        return result;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int depositAmount) {
        this.balance += depositAmount;
        this.transactionList.add(new TransactionRecord(LocalDate.now().toString(),depositAmount, this.balance));
    }

    public void withdraw(int withdrawAmount) {
        balance -= withdrawAmount;
        this.transactionList.add(new TransactionRecord(LocalDate.now().toString(),-withdrawAmount, this.balance));
    }

    public List<TransactionRecord> getTransactions() {
        return transactionList;
    }

    public List<String> getTransactionStrings(){
        List<String> result = new ArrayList<>();
        for(TransactionRecord element:this.transactionList){
            result.add(element.toString());
        }
        return result;
    }
}
