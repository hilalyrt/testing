package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;  //JUnit 5'in (@Test) anotasyonunu içe aktarıyor.

public class AccountTest {
    /**
     * Para yatırma işleminin bakiyeyi doğru şekilde güncelleyip güncellemediğini test eder.
     */
    @Test
    public void testDepositTransaction() {
        Bank bank = new Bank("Test Bank");
        User user = new User("Alice", "Doe", "1234", bank);
        Account account = new Account("Savings", user, bank);

        // 1000 units of money are deposited.
        account.addTransaction(1000, "Initial deposit");
        // Balance should be 1000.
        assertEquals(1000, account.getBalance()); // Eğer bakiye 1000 değilse test başarısız olur.
        //Balance should not be 500
        assertNotEquals(500, account.getBalance());
    }

    /**
     * Para çekme işleminin bakiyeyi doğru şekilde güncelleyip güncellemediğini test eder.
     */
    @Test
    public void testWithdrawTransaction() {
        Bank bank = new Bank("Test Bank");
        User user = new User("Alice", "Doe", "1234", bank);
        Account account = new Account("Savings", user, bank);

        // First 1000 units are deposited, then 500 are withdrawn.
        account.addTransaction(1000, "Deposit");
        account.addTransaction(-500, "Withdraw");

        // Balance must be 500.
        assertTrue(account.getBalance() == 500);  // assertTrue()
        assertFalse(account.getBalance() != 500); // assertFalse()
    }

    /**
     * Yeni açılan hesabın bakiyesinin başlangıçta sıfır olup olmadığını kontrol et
     */
    @Test
    public void testZeroBalance() {
        Bank bank = new Bank("Test Bank");
        User user = new User("Alice", "Doe", "1234", bank);
        Account account = new Account("Savings", user, bank);

        // Başlangıçta bakiye 0 olmalı
        assertEquals(0, account.getBalance());
    }

}

