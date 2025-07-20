package org.example;

import java.util.Date;

public class Transaction {
    private double amount;
    private Date timestamp;
    private String memo; //işlemin açıklaması
    private Account inAccount;//işlemin yapıldığı hesap

    //ilk constructor memo olmadan
    public Transaction(double amount , Account inAccount) {
        this.amount = amount ;
        this.inAccount = inAccount ;
        this.timestamp = new Date();
        this.memo = ""; // Varsayılan olarak boş açıklama
    }

    //ikinci constructor memo eklenir
    public Transaction(double amount, String memo, Account inAccount) {
        this(amount, inAccount); //ilk constructırı çağırır.kod tekrarı önlenir
        this.memo = memo; //memo değeri eklendi
    }

    public double getMoney() {
        return this.amount;
    }

    public void transactionInfo() {
        System.out.println(this.amount + " " +this.inAccount +  " " + this.memo + " "+ this.timestamp);
    }
}
