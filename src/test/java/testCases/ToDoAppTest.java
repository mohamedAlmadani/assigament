package testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ToDoAppTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://crossbrowsertesting.github.io/");
    }

    @Test(priority = 1)
    public void testOpenToDoApp() {
        WebElement toDoAppLink = driver.findElement(By.linkText("To-Do App"));
        toDoAppLink.click();
        String expectedUrl = "https://crossbrowsertesting.github.io/todo-app.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }


    @Test(priority = 2)
    public void testCheckToDoItems() {
        WebElement todo4Checkbox = driver.findElement(By.xpath("//input[@name='todo-4']"));
        todo4Checkbox.click();
        WebElement todo5Checkbox = driver.findElement(By.xpath("//input[@name='todo-5']"));
        todo5Checkbox.click();

        List<WebElement> todoItems = driver.findElements(By.xpath("//span[@class='done-false']"));
        Assert.assertEquals(todoItems.size(), 3);
        boolean isTodo4Present = true;
        boolean isTodo5Present = true;
        for (WebElement todoItem : todoItems) {
            String itemText = todoItem.getText();
            System.out.println(itemText);

            if (itemText.contains("todo4")) {
                isTodo4Present = false;
            }
            if (itemText.contains("todo5")) {
                isTodo5Present = false;
            }
        }

      Assert.assertTrue(isTodo4Present && isTodo5Present);


    }

    @Test(priority = 3)
    public void testArchiveOldTodos() {
        WebElement archiveLink = driver.findElement(By.linkText("archive"));
        archiveLink.click();
        List<WebElement> todoItems = driver.findElements(By.xpath("//span[@class='done-false']"));
        Assert.assertEquals(todoItems.size(), 3);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
