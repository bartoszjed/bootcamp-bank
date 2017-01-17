package org.bj.bootcamp;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    public static String NEW_LINE = System.getProperty("line.separator");
    public static String STATEMENT_HEADER = "DATE       | AMOUNT | BALANCE";
    private final List<TransactionRecord> transactionList;

    public Statement(List<TransactionRecord> transactionList) {
        this.transactionList = transactionList;
    }

    public List<String> getTransactionStrings(){
        List<String> result = new ArrayList<>();
        for(TransactionRecord element:this.transactionList){
            result.add(element.toString());
        }
        return result;
    }

    public String getStatementContent(){
        final String NEW_LINE = System.getProperty("line.separator");


        String result = Statement.STATEMENT_HEADER;

        for(String line: getTransactionStrings()){
            result.concat(NEW_LINE);
            result.concat(line);
        }
        return result;
    }

    public void print() {
        System.out.println(
                this.transactionList.stream()
                .sorted()
        );
    }
}
