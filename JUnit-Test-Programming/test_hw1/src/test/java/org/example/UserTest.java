package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

public class UserTest {
    /**
     * Kullanıcının UUID’sinin null olup olmadığını test eder.
     */
    @Test
    public void testUserCreation() {
        Bank bank = new Bank("Test Bank");
        User user = new User("John", "Doe", "1234", bank);

        // Kullanıcının UUID’si null olmamalı.
        assertNotNull(user.getUUID()); // assertNotNull()
    }

    /**
     * Yeni bir kullanıcı eklediğimizde, en az bir hesabı olmalı.
     */
    @Test
    public void testUserAccountCount() {
        Bank bank = new Bank("Test Bank");
        User user = new User("John", "Doe", "1234", bank);

        // Kullanıcıya bir hesap eklenir
        Account newAccount = new Account("Checking", user, bank);
        user.addAccount(newAccount);

        // Kullanıcının en az 1 hesabı olmalı
        assertTrue(user.numAccounts() >= 1);
        assertFalse(user.numAccounts() < 1);
    }


    /**
     * Farklı PIN numaralarıyla doğrulama testini yapar.
     */
    @ParameterizedTest
    @ValueSource(strings = {"1234", "4321", "0000", "9999","1"})
    public void testPinValidation(String pin) {
        Bank bank = new Bank("Test Bank");
        User user = new User("Test", "User", pin, bank);

        // User must be able to log in with the correct PIN.
        assertTrue(user.validatePin(pin)); // assertTrue()
        assertFalse(user.validatePin("WRONG_PIN"));
    }
}
