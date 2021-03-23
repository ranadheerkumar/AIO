package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class ExcelDataProvider {
 
  	WebDriver driver;
  	@BeforeMethod
  	  public void setUp() {
           	  System.out.println("Start test");
        	  System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        	  driver = new ChromeDriver();
        	  String url = "https://www.google.com";
        	  driver.get(url);
        	  driver.manage().window().maximize();
        	 
  	  }
  	
  	@DataProvider(name ="excel-data")
  	public Object[][] excelDP() throws IOException{
        	//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
        	Object[][] arrObj = getExcelData(System.getProperty("user.dir")+"/TestData.xlsx","DataSet");
        	return arrObj;
  	}
  	//This method handles the excel - opens it and reads the data from the respective cells using a for-loop & returns it in the form of a string array
  	public String[][] getExcelData(String fileName, String sheetName){
        	
        	String[][] data = null;   	
  	  	try
  	  	{
  	   
  	  		FileInputStream fis = new FileInputStream(fileName);
  	   	XSSFWorkbook wb = new XSSFWorkbook(fis);
  	   	XSSFSheet sh = wb.getSheet(sheetName);
  	   	XSSFRow row = sh.getRow(0);
  	   	int noOfRows = sh.getPhysicalNumberOfRows();
  	   	int noOfCols = row.getLastCellNum();
  	   	Cell cell;
  	   	data = new String[noOfRows-1][noOfCols];
  	   	
  	   	for(int i =1; i<noOfRows;i++){
  		     for(int j=0;j<noOfCols;j++){
  		    	   row = sh.getRow(i);
  		    	   cell= row.getCell(j);
  		    	   data[i-1][j] = cell.getStringCellValue();
  	   	 	   }
  	   	}
  	  	}
  	  	catch (Exception e) {
  	     	   System.out.println("The exception is: " +e.getMessage());
           	}
        	return data;
  	}
  	

  		 public void writeExcel(String filePath,String fileName,String sheetName,String[] dataToWrite) throws IOException{

  	        //Create an object of File class to open xlsx file

  	        File file =    new File(filePath+"\\"+fileName);

  	        //Create an object of FileInputStream class to read excel file

  	        FileInputStream inputStream = new FileInputStream(file);

  	        Workbook Workbook = null;

  	        //Find the file extension by splitting  file name in substring and getting only extension name

  	        String fileExtensionName = fileName.substring(fileName.indexOf("."));

  	        //Check condition if the file is xlsx file

  	        if(fileExtensionName.equals(".xlsx")){

  	        //If it is xlsx file then create object of XSSFWorkbook class

  	        Workbook = new XSSFWorkbook(inputStream);

  	        }

  	        //Check condition if the file is xls file

  	        else if(fileExtensionName.equals(".xls")){

  	            //If it is xls file then create object of XSSFWorkbook class

  	            Workbook = new HSSFWorkbook(inputStream);

  	        }    

  	    //Read excel sheet by sheet name    

  	    Sheet sheet = Workbook.getSheet(sheetName);

  	    //Get the current count of rows in excel file

  	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

  	    //Get the first row from the sheet

  	    Row row = sheet.getRow(0);

  	    //Create a new row and append it at last of sheet

  	    Row newRow = sheet.createRow(rowCount+1);

  	    //Create a loop over the cell of newly created Row

  	    for(int j = 0; j < row.getLastCellNum(); j++){

  	        //Fill data in row

  	        Cell cell = newRow.createCell(j);

  	        cell.setCellValue(dataToWrite[j]);

  	    }

  	    //Close input stream

  	    inputStream.close();

  	    //Create an object of FileOutputStream class to create write data in excel file

  	    FileOutputStream outputStream = new FileOutputStream(file);

  	    //write data in the excel file

  	    Workbook.write(outputStream);
  	    
  	    //Create an array with the data in the same order in which you expect to be filled in excel file

  	    String[] valueToWrite = {"Mr. E","Ravi garu"};

  	    //Create an object of current class

  	    WriteExcel objExcelFile = new WriteExcel();

  	    //Write the file using file name, sheet name and the data to be filled

  	    objExcelFile.writeExcel(System.getProperty("user.dir")+"/TestData.xlsx","DataSet",null, valueToWrite);

  	    //close output stream

  	    outputStream.close();
  		
  	    }
  		
  	
  
  	@Test(dataProvider ="excel-data")
  	public void search(String keyWord1, String keyWord2){
        	WebElement txtBox = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
        	  txtBox.sendKeys(keyWord1," ",keyWord2);
        	  Reporter.log("Keyword entered is : " +keyWord1+ " " +keyWord2);
        	  txtBox.sendKeys(Keys.ENTER);
        	  Reporter.log("Search results are displayed.");
  	}
  	
 
  	


  	@AfterMethod
  	public void burnDown(){
        	driver.quit();
  	} 	
}