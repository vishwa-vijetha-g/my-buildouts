package demo;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait;

    @BeforeSuite
    public void beforeSuiteMethod(){
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void testCase01(){
        try{
            System.out.println("Test Case 01: START");

            System.out.println("Test Case 01: PASS");

        }catch(Exception e){

            System.out.println("Test Case 01: FAIL");
            e.printStackTrace();

        }
    }

    @Test
    public void testCase02(){
        try{
            System.out.println("Test Case 02: START");

            System.out.println("Test Case 02: PASS");

        }catch(Exception e){

            System.out.println("Test Case 02: FAIL");
            e.printStackTrace();

        }
    }

    @Test
    public void testCase03(){
        try{
            System.out.println("Test Case 03: START");

            System.out.println("Test Case 03: PASS");

        }catch(Exception e){

            System.out.println("Test Case 03: FAIL");
            e.printStackTrace();

        }
    }

    @AfterSuite
    public void afterSuiteMethod(){
        driver.close();
        driver.quit();
    }
}
