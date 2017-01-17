package org.bj.bootcamp;

public class App
{
    public static void main( String[] args )
    {
       BankAccountImpl bankAccount = new BankAccountImpl();

        bankAccount.deposit(1000);
        bankAccount.withdraw(100);
        bankAccount.deposit(500);

        bankAccount.printStatement();
    }
}
