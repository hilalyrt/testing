package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

public class ATMTest {
    /**
     * ATM sınıfının giriş mesajının beklenen mesajı döndürdüğünü test eder.
     */
    @Test
    public void testIntroductionMessage() {
        assertEquals("Welcome to the ATM Project!", ATM.introduction()); // assertEquals()
    }

    /**
     * Kullanıcının yanlış şifre ile giriş yapamaması gerektiğini test et
     */
    @Test
    public void testInvalidLogin() {
        Bank bank = new Bank("Test Bank");
        User user = bank.addUser("John", "Doe", "1234");

        assertNull(bank.userLogin(user.getUUID(), "0000")); //yanlış şifre olduğu için giriş başarısız olmalı.
        // userLogin metodunun null döndürmesini beklenir.
    }
    /**
     * Kullanıcı girişinin doğru çalıştığını test eden repeatable test.
     * Bu test 5 kez çalıştırılır.
     */
    @RepeatedTest(10)  // Repeatable test
    public void testATMUserLogin() {
        Bank bank = new Bank("Test Bank");
        User user = bank.addUser("John", "Doe", "1234");

        // Kullanıcı doğru bilgilerle giriş yapabilmeli.
        assertNotNull(bank.userLogin(user.getUUID(), "1234")); // dönen değerin null olmadığını kontrol eder(goğru pin)
    }
}
