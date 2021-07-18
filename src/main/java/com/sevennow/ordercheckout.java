package com.sevennow;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ordercheckout
{

    public static void main(String[] args) throws InterruptedException 
        
    {
        System.setProperty("webdriver.chrome.driver", ".//drivers//chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        
        driver.get("https://www.7now.com");  
        
        Thread.sleep(3000); 
        
        //Login
        driver.findElement(By.xpath("//button[@class='btn signin']")).click();        
        driver.findElement(By.name("userName")).sendKeys("aparna5580@gmail.com");
        driver.findElement(By.name("password")).sendKeys("Aparna123");                  
        driver.findElement(By.xpath("//button[@class='btn confirm confirm-xl false']")).click();    
        
        Thread.sleep(5000);
        
        //Add item to the cart and checkout
        driver.findElement(By.xpath("//a[contains(@href,'/home/pizza-pairings/large-pizza---extreme-meat')]//div[contains(@class,'product')]//div[contains(@class,'product-description')]//div[contains(@class,'undefined')]//button[contains(@aria-label,'add')][normalize-space()='ADD']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//span[@class='basket-count']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
        Thread.sleep(5000);
                
        //Add invalid Payment card details
      
        driver.findElement(By.xpath("//button[normalize-space()='Add Card']")).click();
        
        // identify link
//        WebElement m = driver.findElement(By.name("cardnumber"));
//        //Javascript Executor
//        JavascriptExecutor j = (JavascriptExecutor) driver;
//        j.executeScript("arguments[0].click();", m);
//        System.out.println("Page title after click: " + driver.getTitle());
        
        
       
        driver.switchTo().frame(driver.findElement(By.cssSelector("[name='__privateStripeFrame0745']")));
        
        
//        int size = driver.findElements(By.tagName("iframe")).size();
//        System.out.println(size);
//        for (int i = 0; i <= size; i++) { 
//        driver.switchTo().frame(i);
//        System.out.println(i);
//        }
      //  WebElement cardnumber= driver.findElement(By.name("cardnumber"));
        WebElement cardnumber= driver.findElement(By.name("cardnumber"));
        		cardnumber.sendKeys("1111-1111-1111-1111");  
        driver.findElement(By.name("cardExpiry")).sendKeys("11/23"); 
        driver.findElement(By.name("cvc")).sendKeys("1111"); 
        driver.findElement(By.xpath("//input[contains(@placeholder,'ZIP')]")).sendKeys("75063"); 
        driver.findElement(By.xpath("//button[normalize-space()='Add Card']")).click();
        
        //Print invalid card error message
        String message =driver.findElement(By.xpath("//button[normalize-space()='Add Card']")).getText();
        System.out.println(message);
        
        //to navigate to home page
        driver.findElement(By.xpath("//img[contains(@alt,'checkout logo')]")).click();
        
        
        //Test case validation
        String actualUrl="https://www.7now.com/home";
        String expectedUrl= driver.getCurrentUrl();
        System.out.println(expectedUrl);
        if(actualUrl.contains(expectedUrl))
       
        {
            System.out.println("Test passed");
        }
        else
        {
            System.out.println("Test failed");
        }  
    }

}
