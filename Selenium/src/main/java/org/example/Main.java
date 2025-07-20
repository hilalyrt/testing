package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Main {

    WebDriver driver;

    // AirPods 4 URLs
    String urlMediaMarkt = "https://www.mediamarkt.com.tr/tr/product/_apple-airpods-4-bluetooth-kulak-ici-kulaklik-mxp93tua-1239694.html";
    String urlApple = "https://www.apple.com/tr/shop/buy-airpods/airpods-4/aktif-g%C3%BCr%C3%BClt%C3%BC-engelleme-%C3%B6zelli%C4%9Fi-olmadan";
    String urlN11 = "https://www.n11.com/urun/apple-airpods-4-bluetooth-kulak-ici-kulaklik-apple-turkiye-garantili-59266704?magaza=aslantelekom&utm_source=comp_akakce&utm_medium=cpc&utm_campaign=akakce_genel";

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void compareAirpodsPrices() throws InterruptedException {
        double priceMediaMarkt = getPriceFromMediaMarkt();
        double priceApple = getPriceFromApple();
        double priceN11 = getPriceFromN11();

        double min = Math.min(priceMediaMarkt, Math.min(priceApple, priceN11));
        double max = Math.max(priceMediaMarkt, Math.max(priceApple, priceN11));
        double avg = (priceMediaMarkt + priceApple + priceN11) / 3;

        System.out.println("========== AirPods 4 Fiyat Karşılaştırması ==========");
        System.out.println("MediaMarkt: " + priceMediaMarkt + " TL");
        System.out.println("Apple: " + priceApple + " TL");
        System.out.println("n11: " + priceN11 + " TL");
        System.out.println("---------------------------------------------");
        System.out.printf("En Ucuz: %.2f TL\n", min);
        System.out.printf("Ortalama: %.2f TL\n", avg);
        System.out.printf("En Pahalı: %.2f TL\n", max);

        Assert.assertTrue(priceMediaMarkt > 0, "MediaMarkt fiyatı 0!");
        Assert.assertTrue(priceApple > 0, "Apple fiyatı 0!");
        Assert.assertTrue(priceN11 > 0, "n11 fiyatı 0!");
    }

    private double getPriceFromMediaMarkt() throws InterruptedException {
        driver.get(urlMediaMarkt);
        Thread.sleep(3000); // Sayfa yüklenmesini bekle
        WebElement priceElement = driver.findElement(By.cssSelector("span[data-test='branded-price-whole-value']"));
        return parsePrice(priceElement.getText());
    }

    private double getPriceFromApple() throws InterruptedException {
        driver.get(urlApple);
        Thread.sleep(3000);
        WebElement priceElement = driver.findElement(By.cssSelector("span.price-point.price-point-fullPrice-short"));
        return parsePrice(priceElement.getText());
    }

    private double getPriceFromN11() {
        driver.get(urlN11);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("ins[content]")
            ));
            return parsePrice(priceElement.getText());
        } catch (Exception e) {
            System.err.println("n11 fiyatı alınamadı: " + e.getMessage());
            return 0.0;
        }
    }

    private double parsePrice(String priceText) {
        priceText = priceText.replaceAll("[^\\d,]", "").replace(",", ".");
        try {
            return Double.parseDouble(priceText);
        } catch (Exception e) {
            System.err.println("Fiyat ayrıştırılamadı: " + priceText);
            return 0.0;
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
