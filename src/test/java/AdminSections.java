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
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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

        int quantityOfMenuTabs = driver.findElements(By.cssSelector("ul#box-apps-menu li")).size();
        System.out.println(quantityOfMenuTabs);
        for(int i = 1; i<=quantityOfMenuTabs; i++){
            String actualTabXpath = "//ul[@id='box-apps-menu']/li["+i+"]";
            driver.findElement(By.xpath(actualTabXpath)).click();
            driver.findElement(By.xpath("//h1"));

            int quantityOfSubTabs = driver.findElements(By.xpath(actualTabXpath+"//li")).size();
            System.out.println("Subtabs: " + quantityOfSubTabs);

            if(quantityOfSubTabs>0){
                for(int j=1; j<=quantityOfSubTabs; j++){
                    String actualSubTabXpath = actualTabXpath+"//li"+"["+j+"]";

                    WebElement actualSubTab = driver.findElement(By.xpath(actualSubTabXpath));
                    System.out.println("actual subtab "+actualSubTab);

                    actualSubTab.click();
                    driver.findElement(By.xpath("//h1"));
                }
            }

        }
    }


    @AfterClass
    public static void stop() {

        driver.quit();
        driver = null;
    }

}
