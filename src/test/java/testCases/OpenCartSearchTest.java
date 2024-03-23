package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class OpenCartSearchTest {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void login() {
        driver.get("http://opencart.abstracta.us/index.php?route=account/login");
        WebElement emailInput = driver.findElement(By.id("input-email"));
        WebElement passwordInput = driver.findElement(By.id("input-password"));
        WebElement loginButton = driver.findElement(By.cssSelector("input[value='Login']"));
        emailInput.clear();
        emailInput.sendKeys("clarusway@gmail.com");
        passwordInput.clear();
        passwordInput.sendKeys("123456789");
        loginButton.click();
        WebElement searchInput = driver.findElement(By.name("search"));
        boolean isDisplayed =searchInput.isDisplayed();
        Assert.assertEquals(isDisplayed,true);
    }

    @Test(dataProvider = "searchTerms", priority = 2)
    public void searchProduct(String searchTerm) {
        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.clear();
        searchInput.sendKeys(searchTerm);
        searchInput.click();
    }

    @DataProvider(name = "searchTerms")
    public Object[][] searchData() {
        return new Object[][]{
                {"Mac"},
                {"iPad"},
                {"Samsung"}
        };
    }


    @AfterClass
    public void tearDown() {
        driver.quit();

    }

}
