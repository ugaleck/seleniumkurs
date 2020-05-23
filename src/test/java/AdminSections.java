import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AdminSections {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void start() {
        //driver = new FirefoxDriver();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void loginAsAdmin() {
        driver.navigate().to("http://localhost/litecart/public_html/admin/login.php");

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(titleIs("My Store"));
    }
    @Test
    public void clickAdminSections(){
        //Appearence
        driver.findElement(By.xpath("//span[text()='Appearence']")).click();
        driver.findElement(By.xpath("//h1[text()=' Template']"));

        driver.findElement(By.id("doc-template")).click();
        driver.findElement(By.xpath("//h1[text()=' Template']"));

        driver.findElement(By.id("doc-logotype")).click();
        driver.findElement(By.xpath("//h1[text()=' Logotype']"));

        //Catalog
        String catalog = "//span[text()='Catalog']";
        driver.findElement(By.xpath(catalog)).click();
        clickEveryItemFromDirectory(catalog);

        //Countries
        driver.findElement(By.xpath("//span[text()='Countries']")).click();
        driver.findElement(By.xpath("//h1"));

        //Currencies
        driver.findElement(By.xpath("//span[text()='Currencies']")).click();
        driver.findElement(By.xpath("//h1"));

        //Customers
        String customers = "//span[text()='Customers']";
        driver.findElement(By.xpath(customers)).click();
        driver.findElement(By.xpath("//h1"));
        clickEveryItemFromDirectory(customers);

        //Geo Zones
        driver.findElement(By.xpath("//span[text()='Geo Zones']")).click();
        driver.findElement(By.xpath("//h1"));

        //Languages
        String languages = "//span[text()='Languages']";
        driver.findElement(By.xpath(languages)).click();
        driver.findElement(By.xpath("//h1"));
        clickEveryItemFromDirectory(languages);

        //Modules
        String modules = "//span[text()='Modules']";
        driver.findElement(By.xpath(modules)).click();
        driver.findElement(By.xpath("//h1"));
        clickEveryItemFromDirectory(modules);

        //Orders
        String orders = "//span[text()='Orders']";
        driver.findElement(By.xpath(orders)).click();
        driver.findElement(By.xpath("//h1"));
        clickEveryItemFromDirectory(orders);

        //Pages
        driver.findElement(By.xpath("//span[text()='Pages']")).click();
        driver.findElement(By.xpath("//h1"));

        //Reports
        String reports = "//span[text()='Reports']";
        driver.findElement(By.xpath(reports)).click();
        driver.findElement(By.xpath("//h1"));
        clickEveryItemFromDirectory(reports);

        //Settings
        String settings = "//span[text()='Settings']";
        driver.findElement(By.xpath(settings)).click();
        driver.findElement(By.xpath("//h1"));
        clickEveryItemFromDirectory(settings);

        //Slides
        driver.findElement(By.xpath("//span[text()='Slides']")).click();
        driver.findElement(By.xpath("//h1"));

        //Tax
        String tax = "//span[text()='Tax']";
        driver.findElement(By.xpath(tax)).click();
        driver.findElement(By.xpath("//h1"));
        clickEveryItemFromDirectory(tax);

        //Translations
        String translations = "//span[text()='Translations']";
        driver.findElement(By.xpath(translations)).click();
        driver.findElement(By.xpath("//h1"));
        clickEveryItemFromDirectory(translations);

        //Users
        driver.findElement(By.xpath("//span[text()='Users']")).click();
        driver.findElement(By.xpath("//h1"));

        //vQmods
        String vQmods = "//span[text()='vQmods']";
        driver.findElement(By.xpath(translations)).click();
        driver.findElement(By.xpath("//h1"));
        clickEveryItemFromDirectory(vQmods);

    }
    public void clickEveryItemFromDirectory(String directory){
        List<WebElement> directoryItems = driver.findElements(By.xpath
                (directory+"/../../ul[@class='docs']/li"));

        for(int i = 1; i<=directoryItems.size(); i++){
            driver.findElement(By.xpath(directory+"/../../ul[@class='docs']/li["+i+"]")).click();
            driver.findElement(By.xpath("//h1"));
        }
    }


    @AfterClass
    public static void stop() {

        driver.quit();
        driver = null;
    }

}
