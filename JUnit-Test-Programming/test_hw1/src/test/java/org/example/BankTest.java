package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BankTest {
    /**
     * Kullanıcının doğru bilgilerle giriş yapıp yapamadığını test eder.
     */
    @Test
    public void testUserLogin() {
        Bank bank = new Bank("Test Bank");
        User user = bank.addUser("John", "Doe", "1234");

        // Kullanıcı giriş yapabilmeli.
        assertEquals(user, bank.userLogin(user.getUUID(), "1234")); // assertEquals()
    }
    /**
     * Geçersiz kullanıcı ID ile giriş yapıldığında hata fırlatılıp fırlatılmadığını test eder.
     */
    @Test
    public void testInvalidUserLogin() {
        Bank bank = new Bank("Test Bank");

        // If you log in with the wrong UUID, an error should be thrown.
        assertThrows(NullPointerException.class, () -> {
            bank.userLogin("wrong_uuid", "1234").getFirstName(); //null.getFirstName()Java'da geçersiz bir işlem olduğu için bir
            // NullPointerException hatasına yol açar
        });
    }

    @Test
    public void testUserLoginFailure() {
        Bank bank = new Bank("Test Bank");
        User user = bank.addUser("John", "Doe", "1234");

        // If you log in with a wrong user ID, null should be returned.
        assertNull(bank.userLogin("INVALID_ID", "1234"));
    }
}
