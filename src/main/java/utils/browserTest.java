package utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;


public class browserTest {

	WebDriver driver;

	@BeforeMethod
	public static void main(String[] args) throws IOException {
		
		
		/**
		 * Notes: run the following commands in command Prompt
		 * cd C:\Program Files (x86)\Google\Chrome\Application
		 * chrome.exe -remote-debugging-port=9222 -user-data-dir=E:\chromeData
		 */
		System.out.println("Start test");


		// set the driver path- You can also use WebDriverManager for drivers
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		// Create object of ChromeOptions Class
		ChromeOptions opt=new ChromeOptions();

		// pass the debuggerAddress and pass the port along with host. Since I am running test on local so using localhost
		opt.setExperimentalOption("debuggerAddress","localhost:9222 ");

		// pass ChromeOptions object to ChromeDriver constructor
		WebDriver driver=new ChromeDriver(opt);

		// now you can use now existing Browser
		driver.get("http://Robinhoood .com");
		
		
		
		
		
		
//		// Create object of ChromeDriver class
//		ChromeDriver driver = new ChromeDriver();
//
//		// getCapabilities will return all browser capabilities
//		Capabilities cap = driver.getCapabilities();
//
//		// asMap method will return all capability in MAP
//		Map<String, Object> myCap = cap.asMap();
//
//		// print the map data-
//		System.out.println(myCap);
//
//		String url = "https://www.google.com";
//		driver.get(url);
//		driver.manage().window().maximize();
	}
}
