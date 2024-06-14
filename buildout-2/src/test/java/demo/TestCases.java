package demo;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    @BeforeSuite
    public void beforeSuiteMethod(){
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testCase01(){
        try{
            System.out.println("Test Case 01: START");

            driver.get("https://www.google.com");

            System.out.println("Test Case 01: PASS");

        }catch(Exception e){

            System.out.println("Test Case 01: FAIL");
            e.printStackTrace();

        }
    }

    @AfterSuite
    public void afterSuiteMethod(){
        driver.close();
        driver.quit();
    }
}
