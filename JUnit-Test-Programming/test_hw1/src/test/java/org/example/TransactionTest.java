package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TransactionTest {
    /**
     * Yeni bir işlem oluşturulduğunda, işlem tutarının ve referansının doğru atanıp atanmadığını test eder.
     */
    @Test
    public void testTransactionCreation() {
        Bank bank = new Bank("Test Bank");
        User user = new User("Alice", "Doe", "1234", bank);
        Account account = new Account("Savings", user, bank);
        Transaction transaction1 = new Transaction(500, "Deposit", account);
        Transaction transaction2 = transaction1;

        // Transaction amount must be 500.
        assertEquals(500, transaction1.getMoney());
        // Transaction amount must not be 600.
        assertNotEquals(600, transaction1.getMoney());
        // The two transaction objects must be the same.
        assertSame(transaction1, transaction2);

    }
    /**
     * Farklı işlemlerin aynı nesne olup olmadığını test eder.
     */
    @Test
    public void testDifferentTransactions() {
        Bank bank = new Bank("Test Bank");
        User user = new User("Alice", "Doe", "1234", bank);
        Account account = new Account("Savings", user, bank);
        Transaction transaction1 = new Transaction(500, "Deposit", account);
        Transaction transaction2 = new Transaction(1000, "Deposit", account);

        // The two transaction objects must not be the same.
        assertNotSame(transaction1, transaction2);  // assertNotSame()
    }

    /**
     * *İşlem sonrasında işlem geçmişinin boş olmaması gerektiğini test eder.
     */
    @Test
    public void testTransactionHistory() {
        Bank bank = new Bank("Test Bank");
        User user = new User("Alice", "Doe", "1234", bank);
        Account account = new Account("Savings", user, bank);

        account.addTransaction(1000, "Initial deposit"); //1000 tutarında "Initial deposit" işlemi ekleniyor.

        // Hesap işlem geçmişi boş olmamalı
        assertFalse(account.getBalance() == 0);
    }

}
