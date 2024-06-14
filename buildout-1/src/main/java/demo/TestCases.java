package demo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        try{

            System.out.println("Start Test case: Test Case - 01");

            driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            
            By nameLocator = By.xpath("(//input[@class='whsOnd zHQkBf'])[1]");
            wait.until(ExpectedConditions.elementToBeClickable(nameLocator));
            WebElement name = driver.findElement(nameLocator);
            name.click();
            name.sendKeys("Vishwa Vijetha G");

            By whyLocator = By.className("KHxj8b");
            wait.until(ExpectedConditions.elementToBeClickable(whyLocator));
            WebElement why = driver.findElement(whyLocator);
            why.click();
            String whyResponse = "I want to be the best QA Engineer! " + System.currentTimeMillis()/1000;
            why.sendKeys(whyResponse);

            By expLocator = By.xpath("(//div[@class='AB7Lab Id5V1'])[1]");
            wait.until(ExpectedConditions.elementToBeClickable(expLocator));
            WebElement experience = driver.findElement(expLocator);
            experience.click();

            By learnLocator = By.cssSelector(".uHMk6b.fsHoPb");
            List<WebElement> learnings = driver.findElements(learnLocator);
            for(int i=0;i<4;i++){
                if(i==2) continue;
                wait.until(ExpectedConditions.elementToBeClickable(learnings.get(i)));
                learnings.get(i).click();
            }

            By addressLocator = By.xpath("//div[@class='MocG8c HZ3kWc mhLiyf LMgvRb KKjvXb DEh1R']");
            wait.until(ExpectedConditions.elementToBeClickable(addressLocator));
            WebElement addressed = driver.findElement(addressLocator);
            addressed.click();

            By mrLocator = By.xpath("(//div[@data-value='Mr'])[2]");
            wait.until(ExpectedConditions.elementToBeClickable(mrLocator));
            WebElement mr = driver.findElement(mrLocator);
            mr.click();

            By dateLocator = By.xpath("//input[@type='date']");
            wait.until(ExpectedConditions.elementToBeClickable(dateLocator));
            WebElement date = driver.findElement(dateLocator);
            date.click();
            date.sendKeys(LocalDate.now().minusDays(7).format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            

            By hourLocator = By.xpath("//input[@aria-label='Hour']");
            wait.until(ExpectedConditions.elementToBeClickable(hourLocator));
            WebElement hour = driver.findElement(hourLocator);
            hour.click();
            hour.sendKeys(LocalTime.now().format(DateTimeFormatter.ofPattern("hh")));

            By minuteLocator = By.xpath("//input[@aria-label='Minute']");
            wait.until(ExpectedConditions.elementToBeClickable(minuteLocator));
            WebElement minute = driver.findElement(minuteLocator);
            minute.click();
            minute.sendKeys(LocalTime.now().format(DateTimeFormatter.ofPattern("mm")));

            /* AM PM SELECTOR WEB ELEMENT IS NOT PRESENT IN THE FORM, HENCE COMMENTING THE LOGIC

            By amOrPmLocator = By.xpath("//div[@class='MocG8c HZ3kWc mhLiyf LMgvRb KKjvXb']");
            By amLocator = By.xpath("//div[@data-value='AM']");
            By pmLocator = By.xpath("//div[@data-value='PM']");

            wait.until(ExpectedConditions.elementToBeClickable(amOrPmLocator));
            WebElement amOrPm = driver.findElement(amOrPmLocator);
            amOrPm.click();

            if(LocalTime.now().format(DateTimeFormatter.ofPattern("a")).equals("AM")){
                wait.until(ExpectedConditions.elementToBeClickable(amLocator));
                WebElement am = driver.findElement(amLocator);
                am.click();
            }else{
                wait.until(ExpectedConditions.elementToBeClickable(pmLocator));
                WebElement pm = driver.findElement(pmLocator);
                pm.click();
            }

            */


            //POPUP IS NOT COMING UP, UPON REDIRECTION TO ANOTHER WEBSITE, HENCE SKIPPING THE STEP

            By submitLocator = By.xpath("//span[text()='Submit']");
            wait.until(ExpectedConditions.elementToBeClickable(submitLocator));
            WebElement submit = driver.findElement(submitLocator);
            submit.click();

            Thread.sleep(2500); // ADDING THREAD.SLEEP() FOR VISUALIZATION

            System.out.println("Test Case - 01 : PASS");

        }catch(Exception e){

            e.printStackTrace();
            System.out.println("Test Case - 01 : FAIL");

        }
    }


}
