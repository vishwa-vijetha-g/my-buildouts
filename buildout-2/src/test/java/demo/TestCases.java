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

            driver.get("http://www.flipkart.com");
            
            search("Washing Machine");

            By popularityLocator = By.xpath("//div[text()='Popularity']");
            wait.until(ExpectedConditions.elementToBeClickable(popularityLocator));
            WebElement popularity = driver.findElement(popularityLocator);
            popularity.click();

            Thread.sleep(2500);
            
            By ratingsLocator = By.className("XQDdHH");
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(ratingsLocator));
            List<WebElement> ratings = driver.findElements(ratingsLocator);
            int count = 0;

            for(int i=0;i<24;i++){
                Double r = Double.valueOf(ratings.get(0).getText());
                if(r<=4.0){
                    count++;
                }
            }

            System.out.println("The count of items with rating less than or equal to 4 stars in Page 1 are: " + count);

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

            search("iPhone");

            Thread.sleep(2500);

            By discountsLocator = By.className("UkUFwK");
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(discountsLocator));
            List<WebElement> discounts = driver.findElements(discountsLocator);

            System.out.println("The Titles and Discount % of items with more than 17% discount are: ");

            for(int i=0;i<24;i++){
                Integer d = Integer.valueOf(discounts.get(i).getText().substring(0, 2));
                if(d>17){
                    WebElement title = driver.findElement(By.xpath("(//div[@class='UkUFwK'])["+(i+1)+"]/ancestor::div[@class='yKfJKb row']/div[1]/div[1]"));
                    System.out.println(title.getText() + " - " + d + "% Discount");
                }
            }

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
            
            search("Coffee Mug");
            
            Thread.sleep(2500);
            
            By starsLocator = By.xpath("(//div[@class='XqNaEv'])[1]");
            wait.until(ExpectedConditions.elementToBeClickable(starsLocator));
            WebElement stars = driver.findElement(starsLocator);
            stars.click();
            
            Thread.sleep(2500);

            By reviewsLocator = By.className("Wphh3N");
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(starsLocator));
            List<WebElement> reviews = driver.findElements(reviewsLocator);
            
            TreeMap<Integer,By> map = new TreeMap<>(Collections.reverseOrder());

            for(int i=0;i<reviews.size();i++){
                Integer r = reviewsCount(reviews.get(i).getText());
                map.put(r,By.xpath("(//span[@class='Wphh3N'])["+(i+1)+"]/ancestor::div[@class='slAVV4']//img[@class='DByuf4']"));
            }

            System.out.println("The Title and Image URL of the 5 items with highest number of reviews are: ");

            int count = 0;
            WebElement review;
            for(Map.Entry<Integer,By> me:map.entrySet()){
                //System.out.println(me.getKey());
                review = driver.findElement(me.getValue());
                System.out.println(count+1);
                System.out.println("Title - " + review.getAttribute("alt"));
                System.out.println("Image URL - " + review.getAttribute("src"));
                count++;
                if(count==5){
                    break;
                }
            }


            System.out.println("Test Case 03: PASS");

        }catch(Exception e){

            System.out.println("Test Case 03: FAIL");
            e.printStackTrace();

        }
    }

    //Wrapper Method
    public void search(String value){
        By searchLocator = By.name("q");
        WebElement search = driver.findElement(searchLocator);
        Actions a = new Actions(driver);
        a.sendKeys(search,Keys.CONTROL).sendKeys(search,"A").sendKeys(search,Keys.BACK_SPACE).build().perform();
        a.sendKeys(search,value).sendKeys(search,Keys.ENTER).build().perform();
    }

    //Utility Method
    public Integer reviewsCount(String text){
        StringBuilder sb = new StringBuilder();
        for(char c:text.toCharArray()){
            if(c=='(' || c==',' || c==')'){
                continue;
            }
            sb.append(c);
        }
        return Integer.valueOf(sb.toString());
    }

    @AfterSuite
    public void afterSuiteMethod(){
        driver.close();
        driver.quit();
    }
}
