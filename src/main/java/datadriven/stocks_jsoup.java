package datadriven;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class stocks_jsoup {

	public static void main(String[] args) throws IOException, InterruptedException {

	
	/**
	 * Notes: run the following commands in command Prompt 
	 * cd C:\Program Files (x86)\Google\Chrome\Application
	 *  chrome.exe -remote-debugging-port=9222 -user-data-dir=E:\chromeData
	 */
	System.out.println("Start test");

	// set the driver path- You can also use WebDriverManager for drivers
	System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

	// Create object of ChromeOptions Class
	ChromeOptions opt = new ChromeOptions();

	// pass the debuggerAddress and pass the port along with host. Since I am
	// running test on local so using localhost
	opt.setExperimentalOption("debuggerAddress", "localhost:9222 ");

	// pass ChromeOptions object to ChromeDriver constructor
	WebDriver driver = new ChromeDriver(opt);

	// now you can use now existing Browser

	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	String url = "https://robinhood.com/";
	driver.get(url);
	Thread.sleep(3000);
	driver.findElement(By.xpath("//span[contains(text(),'100 Most Popular')]")).click();
	 String strUrl = driver.getCurrentUrl();

	try {
		final Document document = Jsoup.connect(strUrl).get();
		
		System.out.println(document.outerHtml());
		
		
		
		
	}catch(Exception ex) {
		ex.printStackTrace();
		System.out.println("print stack message :"+ ex);
	}
	
	
	
	
	
	
}
}
