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


    public BankAccountImpl() {
        this.balance = 0;
    }

    public void printStatement() {
        (new Statement(this.transactionList)).print();
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int depositAmount) {
        this.balance += depositAmount;
        this.transactionList.add(new TransactionRecord(LocalDate.now().toString(),depositAmount, this.balance));
    }

    public void withdraw(int withdrawAmount) {
        deposit(-withdrawAmount);
    }

    public List<TransactionRecord> getTransactions() {
        return transactionList;
    }


}
