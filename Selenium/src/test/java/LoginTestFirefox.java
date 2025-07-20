import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class LoginTestFirefox {
    private WebDriver driver;

    private String username;
    private String password;
    private boolean expectedSuccess;

    public LoginTestFirefox() {
        // Default constructor
    }

    public LoginTestFirefox(String username, String password, boolean expectedSuccess) {
        this.username = username;
        this.password = password;
        this.expectedSuccess = expectedSuccess;
    }

    @BeforeClass
    public void setUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return new Object[][]{
                {"admin", "admin123", true},
               {"invaliduser", "invalidpassword", false}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, boolean expectedSuccess) {
        // Use Paths.get() to ensure correct file path
        String filePath = Paths.get("src", "main", "resources", "Login.html").toAbsolutePath().toUri().toString();
        System.out.println("Loading file from path: " + filePath);
        driver.get(filePath);

        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();

        // You can add validation/assertions here based on expectedSuccess
    }

  /*  @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}
