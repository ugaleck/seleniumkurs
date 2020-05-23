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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void checkStickers() {
        driver.navigate().to("http://localhost/litecart/public_html/en/");

        String mostPopular = "#box-most-popular";
        checkOnlyOneSticker(mostPopular);
        String campaigns = "#box-campaigns";
        checkOnlyOneSticker(campaigns);
        String latestProducts = "#box-latest-products";
        checkOnlyOneSticker(latestProducts);

    }
    //проверяет в каждом элементе списка, найденного по id всего блока, наличие только одногого
    // стикера, либо New либо Sale
    public void checkOnlyOneSticker(String listId){

        List<WebElement> products = driver.findElements(By.cssSelector(listId +" li"));
        System.out.println("popular products" + products.size());
        for(WebElement product : products){

            int countNewStickers = product.findElements(By.cssSelector("[title=New]")).size();
            int countSaleStickers = product.findElements(By.cssSelector("[title='On Sale']")).size();
            int countStickers = countNewStickers + countSaleStickers;

            assertEquals(countStickers, 1);

        }
    }

    @After
    public void stop() {

        driver.quit();
        driver = null;
    }
}
