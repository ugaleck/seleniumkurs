import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class StickersTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void checkStickers() {
        driver.navigate().to("http://localhost/litecart/public_html/en/");

        List<WebElement> products = driver.findElements(By.cssSelector(".product"));
        for(WebElement product : products){

            int quantityOfStickers = product.findElements(By.cssSelector(".sticker")).size();

            assertEquals(quantityOfStickers, 1);
        }

    }

    @After
    public void stop() {

        driver.quit();
        driver = null;
    }
}
