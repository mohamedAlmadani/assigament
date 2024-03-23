package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class LoginTest {

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    }

    @DataProvider(name = "credentials")
    public Object[][] getData() {
        return new Object[][]{
                {"admin*", "admin123"},
                {"admin12", "123"},
                {"Admin1", "***00**"},
                {"test", "123"},
                {"user", "369"}
        };
    }
    @Test(dataProvider = "credentials", threadPoolSize = 3)
    @Description("Login with negative credentials and assert 'Invalid credentials' message")
    public void testLoginWithNegativeCredentials(String username, String password) {
        goToLoginPage();
        login(username, password);
        assertErrorMessage();
    }
    @Step("Go to login page")
    private void goToLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }
    @Step("Login with credentials: {0}, {1}")
    private void login(String username, String password) {
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//BUTTON[@type='submit']")).click();
    }

    @Step("Assert error message")
    private void assertErrorMessage() {
        assertTrue(driver.findElement(By.xpath("//P[@class='oxd-text oxd-text--p oxd-alert-content-text'][text()='Invalid credentials']")).getText().contains("Invalid credentials"));
    }




    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
