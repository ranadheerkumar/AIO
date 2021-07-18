package datadriven;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class stocks {

	public static void main(String[] args) throws IOException, InterruptedException {

		/**
		 * Notes: run the following command in command Prompt 
		 * cd C:\Program Files (x86)\Google\Chrome\Application chrome.exe -remote-debugging-port=9222 -user-data-dir=E:\chromeData
		 */
		System.out.println("Start test");

		// set the driver path- You can also use WebDriverManager for drivers
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

		// Create object of ChromeOptions Class
		ChromeOptions opt = new ChromeOptions();

		// pass the debuggerAddress and pass the port along with host. Since I am
		// running test on local so using localhost
		opt.setExperimentalOption("debuggerAddress", "localhost:9222");

		// pass ChromeOptions object to ChromeDriver constructor
		WebDriver driver = new ChromeDriver(opt);

		// now you can use now existing Browser

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
		String url = "https://robinhood.com/";
		System.out.println("before driver");
		driver.get(url);
		System.out.println("after driver");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'100 Most Popular')]")).click();

		String path = ".\\datafiles\\stocks.xlsx";
		XLUtility xlutil = new XLUtility(path);

		// Write headers in excel sheet
		xlutil.setCellData("Sheet1", 0, 0, "Name");
		xlutil.setCellData("Sheet1", 0, 1, "Symbol");
		xlutil.setCellData("Sheet1", 0, 2, "Price");
		xlutil.setCellData("Sheet1", 0, 3, "Today");
		xlutil.setCellData("Sheet1", 0, 4, "Market");
		List<WebElement> links = driver.findElements(By.tagName("a"));// By.tagName("a")
		System.out.println("Links:" + links);
		Iterator<WebElement> it = links.iterator();
		while (it.hasNext()) {
			url = it.next().getText();// findElements(By.tagName("href"); //.getAttribute("rel href");
			
			WebElement el1= it.next().findElement(By.cssSelector("span[@class='css-ktio0g']"));
			System.out.println("values:" + el1.getText());
			/*
			WebElement table = driver.findElement(By.tagName("a"));
			int rows = table.findElements(By.tagName("a")).size(); // rows present in web table
			for (int r = 1; r <= 2; r++) {
				// read data from web table
				url = table.findElement(By.tagName("a")).getText();
				System.out.println("value--->"+url);
				// writing the data in excel sheet
				xlutil.setCellData("Sheet1", r, 0, url);
				System.out.println("capturing list:" + url + "rows: " + r);
			}
			System.out.println("Web scrapping is done succesfully.....");
			*/
			// driver.close();
		}
	}
}
