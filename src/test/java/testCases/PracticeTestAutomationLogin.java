package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PracticeTestAutomationLogin {


    static WebDriver driver;
    static WebDriverWait wait;



    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

    }

    @Test(priority = 1)
    public void loginWithInvalidPassword() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        usernameField.sendKeys("student");
        passwordField.sendKeys("incorrectPassword");
        WebElement loginButton = driver.findElement(By.id("submit"));
        loginButton.click();
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(errorMessage.getText(), "Your password is invalid!", "Error message validation failed");
        softAssert.assertAll();
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
