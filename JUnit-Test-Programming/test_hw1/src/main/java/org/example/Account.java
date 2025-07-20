package org.example;

import java.util.ArrayList;

public class Account {
    private String accountName;
    private String uuid;
    private User accountHolder;
    private ArrayList<Transaction> transactions;

    public Account (String name , User holder, Bank theBank){
        this.accountName = name ;
        this.accountHolder = holder ;
        this.uuid = theBank.getNewAccountUUID();
        this.transactions = new ArrayList<Transaction>();
    }

    public String getUUID() {
        return uuid;
    }

    public String  getSummaryLine(){
        double balance = this.getBalance();

        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance,
                    this.accountName);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance,
                    this.accountName);
        }
    }
    public User getAccountHolder() {
        return this.accountHolder;
    }

    public double getBalance(){
        double balance = 0;
        for (Transaction t :this.transactions){
            balance += t.getMoney();
        }
        return balance ;
    }

    public void addTransaction(double amount, String memo) {

        // Creating a new transaction and adding to the list.
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);
    }

    public void showTransactions(){
        for (Transaction t : transactions){
            t.transactionInfo();
        }
    }
}
