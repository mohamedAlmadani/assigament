package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class InterdependentTests {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test(priority = 1)
    public void testFacebook() {
        driver.get("https://www.facebook.com");
    }

    @Test(priority = 2)
    public void testGoogle() {
        driver.get("https://www.google.com");
    }

    @Test(priority = 3)
    public void testAmazon() {
        driver.get("https://www.amazon.com");
    }
    @AfterClass
    public static void tearDown() {
        driver.quit();
    }


}

