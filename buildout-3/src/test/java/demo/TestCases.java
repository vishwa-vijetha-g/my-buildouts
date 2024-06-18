package demo;

import java.time.Duration;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;
    WebDriverWait wait;
    JavascriptExecutor je;
    SoftAssert sa;

    @BeforeSuite
    public void beforeSuiteMethod(){
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        je = (JavascriptExecutor) driver;
        sa = new SoftAssert();
    }

    @Test
    public void testCase01(){
        try{
            System.out.println("Test Case 01: START");

            driver.get("https://www.youtube.com/");
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/");

            wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("About")));
            WebElement about = driver.findElement(By.linkText("About"));
            
            je.executeScript("arguments[0].scrollIntoView();", about);
            wait.until(ExpectedConditions.visibilityOf(about));
            about.click();
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p)[1]")));
            WebElement aboutText = driver.findElement(By.xpath("(//p)[1]"));

            System.out.println("About YouTube : " + aboutText.getText());

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

            driver.get("https://www.youtube.com/");

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Movies']")));
            WebElement movies = driver.findElement(By.xpath("//a[@title='Movies']"));

            je.executeScript("arguments[0].scrollIntoView();", movies);
            wait.until(ExpectedConditions.visibilityOf(movies));
            movies.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Next']")));
            WebElement next = driver.findElement(By.xpath("//button[@aria-label='Next']"));
            next.click();
            Thread.sleep(1000);
            next.click();
            Thread.sleep(1000);
            next.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='style-scope ytd-badge-supported-renderer'])[32]")));
            WebElement matureRating = driver.findElement(By.xpath("(//p[@class='style-scope ytd-badge-supported-renderer'])[32]"));

            sa.assertEquals(matureRating.getText(),"A");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer'])[16]")));
            WebElement genre = driver.findElement(By.xpath("(//span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer'])[16]"));
            
            sa.assertTrue(genre.getText().contains("Comedy") || genre.getText().contains("Animation"));
            
            sa.assertAll();

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

            driver.get("https://www.youtube.com/");

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Music']")));
            WebElement music = driver.findElement(By.xpath("//a[@title='Music']"));

            je.executeScript("arguments[0].scrollIntoView();", music);
            wait.until(ExpectedConditions.visibilityOf(music));
            music.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@aria-label='Next'])[1]")));
            WebElement next = driver.findElement(By.xpath("(//button[@aria-label='Next'])[1]"));
            next.click();
            Thread.sleep(1000);
            next.click();
            Thread.sleep(1000);
            next.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//h3[@class='style-scope ytd-compact-station-renderer'])[11]")));
            WebElement title = driver.findElement(By.xpath("(//h3[@class='style-scope ytd-compact-station-renderer'])[11]"));

            System.out.println(title.getText());

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@id='video-count-text'])[11]")));
            WebElement tracks = driver.findElement(By.xpath("(//p[@id='video-count-text'])[11]"));
            
            Integer numberOfTracks = Integer.valueOf(tracks.getText().trim().substring(0,2));
            sa.assertTrue(numberOfTracks<=50);

            sa.assertAll();

            System.out.println("Test Case 03: PASS");

        }catch(Exception e){

            System.out.println("Test Case 03: FAIL");
            e.printStackTrace();

        }
    }

    @Test
    public void testCase04(){
        try{
            System.out.println("Test Case 04: START");

            driver.get("https://www.youtube.com/");

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='News']")));
            WebElement news = driver.findElement(By.xpath("//a[@title='News']"));

            je.executeScript("arguments[0].scrollIntoView();", news);
            wait.until(ExpectedConditions.visibilityOf(news));
            news.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='author']/a/span")));
            List<WebElement> titles = driver.findElements(By.xpath("//div[@id='author']/a/span"));
            
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='post-text']/yt-formatted-string/span[1]")));
            List<WebElement> bodies = driver.findElements(By.xpath("//div[@id='post-text']/yt-formatted-string/span[1]"));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='vote-count-middle']")));
            List<WebElement> likes = driver.findElements(By.xpath("//span[@id='vote-count-middle']"));

            System.out.println("The Title,Body and Likes of Top 3 Latest News Posts : ");
            int sum = 0;

            for(int i=0;i<3;i++){
                System.out.println("Post " + (i+1));
                System.out.println(titles.get(i).getText());
                System.out.println(bodies.get(i).getText());
                System.out.println(likes.get(i).getText() + " Likes");
                sum += Integer.valueOf(likes.get(i).getText().trim());
            }
            System.out.println("Sum of Likes of 3 posts : " + sum);

            System.out.println("Test Case 04: PASS");

        }catch(Exception e){

            System.out.println("Test Case 04: FAIL");
            e.printStackTrace();

        }
    }

    @AfterSuite
    public void afterSuiteMethod(){
        // driver.close();
        // driver.quit();
    }
}
