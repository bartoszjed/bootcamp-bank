package org.bj.bootcamp;

import java.util.Objects;

/**
 * Created by Bartosz Jedrzejczak on 17/01/2017.
 */
public class TransactionRecord {
    private final String transactionDate;
    private final double amount;
    private final double balance;

    public TransactionRecord(String transactionDate, double amount, double balance) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.balance = balance;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionRecord that = (TransactionRecord) o;
        return Double.compare(that.amount, amount) == 0 &&
                Double.compare(that.balance, balance) == 0 &&
                Objects.equals(transactionDate, that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionDate, amount, balance);
    }



//    @Override
//    public String toString() {
//        return transactionDate
//                .concat(" | ")
//                .concat( ((Double) amount).toString() )
//                .concat(" | ")
//                .concat( ((Double) balance).toString() );
//    }


    @Override
    public String toString() {
        return "TransactionRecord{" +
                "date='" + transactionDate + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}
