import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CountriesAndGeozones {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        wait.until(titleIs("Countries | My Store"));
    }
    //1a
    @Test
    public void checkCountriesInAlthabeticalOrder(){

        WebElement countryTable = driver.findElement(By.cssSelector("form[name=countries_form] table"));
        List<WebElement> rows = countryTable.findElements(By.className("row"));

        List<String> countries = new ArrayList<String>();
        for (WebElement row : rows){
            String country = row.findElement(By.xpath("./td[5]")).getText();
            countries.add(country);
        }
        Assert.assertTrue(isInAlphabeticalOrder(countries));

    }
    //1b
    @Test
    public void checkZonesInAlphabeticalOrder(){

        WebElement countryTable = driver.findElement(By.cssSelector("form[name=countries_form] table"));
        int numberOfRows = countryTable.findElements(By.xpath("//tr/td[6]")).size();
        for(int row = 2; row <=numberOfRows; row ++){
            int numberOfZones = Integer.parseInt(driver.findElement(By.xpath
                    ("//tr["+row+"]//td[6]")).getText());
            if(numberOfZones>0){
                driver.findElement(By.xpath("//tr["+row+"]/td[5]/a")).click();

                WebElement zoneTable = driver.findElement(By.id("table-zones"));
                List<WebElement> zoneRows = zoneTable.findElements(By.cssSelector("tr:not(.header)"));

                List<String> zones = new ArrayList<String>();
                for (WebElement zoneRow : zoneRows){
                    String zone = zoneRow.findElement(By.xpath("./td[3]")).getText();

                    if(!zone.equals("")){
                        zones.add(zone);
                    }

                }

                Assert.assertTrue(isInAlphabeticalOrder(zones));
                driver.findElement(By.xpath("//span[text()='Countries']")).click();
            }
        }
    }
    //2
    @Test
    public void checkGeozonesInAlphabeticalOrder(){
        driver.findElement(By.xpath("//span[text()='Geo Zones']")).click();

        String rowXpath = "//form[@name='geo_zones_form']//tr[@class='row']";
        int numberOfGeozones = driver.findElements(By.xpath(rowXpath)).size();

        for (int rowNumber = 1; rowNumber <= numberOfGeozones; rowNumber++){
            driver.findElement(By.xpath(rowXpath+"["+rowNumber+"]//a")).click();

            WebElement geoTable = driver.findElement(By.cssSelector("#table-zones"));
             List<WebElement> selectedZones = geoTable.findElements
                    (By.xpath(".//td[3]/select/option[@selected='selected']"));
             List<String> selectedTexts = new ArrayList<String>();
             for(WebElement geo: selectedZones){
                 String selectedText = geo.getText();
                 selectedTexts.add(selectedText);
             }
             Assert.assertTrue(isInAlphabeticalOrder(selectedTexts));
             driver.findElement(By.xpath("//span[text()='Geo Zones']")).click();
        }
    }

    public boolean isInAlphabeticalOrder(List<String> list){
        int n = list.size();

        for (int i = 1; i < n; i++) {

            if(list.get(i).charAt(0)<list.get(i-1).charAt(0)){
                return false;
            }
        }
        return true;
    }

    @After
    public void stop() {

        driver.quit();
        driver = null;
    }
}
