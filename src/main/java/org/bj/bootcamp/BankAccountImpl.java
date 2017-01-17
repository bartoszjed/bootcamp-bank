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

    public BankAccountImpl(Integer balance) {
        this.balance = balance;
    }

    public String getstatement() {
        return "DATE   | AMOUNT   | BALANCE";
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
        this.transactionList.add(new TransactionRecord(LocalDate.now().toString(),withdrawAmount, this.balance));
    }

    public List<TransactionRecord> getTransactions() {
        return transactionList;
    }
}