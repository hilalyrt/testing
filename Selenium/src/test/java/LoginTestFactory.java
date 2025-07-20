import org.testng.annotations.Factory;

public class LoginTestFactory {

    @Factory
    public Object[] createInstances() {
        return new Object[]{
                new LoginTestChrome("admin", "admin123", true),
                new LoginTestChrome("invaliduser", "invalidpassword", false),
                new LoginTestFirefox("admin", "admin123", true),
                new LoginTestFirefox("invaliduser", "invalidpassword", false)
        };
    }
}
