package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.dnd.DragSourceDropEvent;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;// this is global variable , so in this base class we can call any where.

	// public static void-we need not to create a object for this method.
	// launch the browser
	public static void launchBrowser() {
	WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	// windows Maximize
	public static void windowMaximize() {
		driver.manage().window().maximize();
	}

	// launch Url with String type
	public static void launchUrl(String url) {
		driver.get(url);
	}

	// to get a page Title
	public static String pageTitle() {
		String title = driver.getTitle();
		return title;
	}

	// to get a current page Url
	public static String pageUrl() {
		String url = driver.getCurrentUrl();
		return url;
	}

	// to send keys
	public static void passText(String txt, WebElement ele) {
		ele.sendKeys(txt);
	}

	// to close entire Browser
	public static void closeEntireBrowser() {
		driver.quit();
	}

	// to click button in action class
	public static void clickButton(WebElement element) {
		element.click();
	}
	// To take a screen shot 
	public static void screenShot(String imgName) throws IOException {
	//step 1 type casting
        TakesScreenshot ts = (TakesScreenshot)driver;
    //step 2 use getScreenShotAs()
        File image = ts.getScreenshotAs(OutputType.FILE);
    //step 3 create a destination file plus imageName     
        File F = new File("location + ingName.png");
    //step 4 copy file content from temp folder to destination folder or permanent folder
        FileUtils.copyFile(image, F);    
	}
	//Actions
	public static Actions a ; 
	//To move to the cursor
	public static void moveTheCursor(WebElement targetWebElement) {
		a = new Actions(driver);
		a.moveToElement(targetWebElement).perform();	
	}
	//drag and drop
	public static void dragDrop(WebElement dragWebElement, WebElement dropElement) {
		a = new Actions(driver);
		a.dragAndDrop(dragWebElement, dropElement).perform();	
	}
	//JavascriptExecutor
	public static JavascriptExecutor js;
	//To scroll the page
	public static void scrollThePage(WebElement tarWebele) {
		//downCasting method
		js = (JavascriptExecutor)driver;
		   //method                    //to dispalyed in top webpage
		js.executeScript("arguments[0].scrollIntoView(true)", tarWebele);
	}
	public static void scroll(WebElement element) {
	    js = (JavascriptExecutor)driver;
	       //method                    //to displayed in bottom webpage
	    js.executeScript("arguments[0].scrollIntoView(false)", element);	
	}
	//dataDriven
	//to read the page
	public static void excelRead(String sheetName, int rowNum, int cellNum) throws IOException {
		//1.object creation for file and mention excel path
		File f = new File("excel location.xlsx");
		//2.to read the file
		FileInputStream fis  = new FileInputStream(f);
		//3. to read .xlsx file format
		//create a object for XSSF Workbook
		//This method is in interface so they done as UPCASTING METHOD
		Workbook wb = new XSSFWorkbook(fis);
		//4.get sheet from workbook
		Sheet mysheet = wb.getSheet("Data");
		//to get a row as rowNum
		Row r = mysheet.getRow(rowNum);
		//to get a cell as cellNum
		Cell c = r.getCell(cellNum);
		//To find the cell data type
		int cellType = c.getCellType();
		
		String value = " " ; 
		//to check condition for wheather the string or not
		if (cellType==1) {
			//to get a value present in a cell
			String value2 = c.getStringCellValue();	
		}
		//cellType = other than 1 that is dateCell
		//cellType = other than 1 that is numericCell
		    //predefined class  //used to check whether cell is date or not
		else if (DateUtil.isCellDateFormatted(c)) {
			//to get the date
			Date dd = c.getDateCellValue();
			//create a object for class with SimpledateFormatMethod
			SimpleDateFormat s = new SimpleDateFormat();
			//to convert date into string value using FORMAT() method
			String value1 = s.format(dd);
		}
		else {
			         //to get the Numeric values
			double d = c.getNumericCellValue();//5678.0
			//to convert double dataype to long datatype
			//long is lower datatype
			//double is higher datatype
			//higher datatype couldnt stored into lower datatype so then use downcasting method.
			long l = (long) d;
			//convert long datatype to string datatype with static method.
			//className.methodName ----> static method format.
			String valueOf = String.valueOf(l);	
		}	
	}
	//to create a new excel file
	public static void createNewExcelFile(int rowNum, int cellNum, String newData) throws IOException {
		//1.object creation for file and mention excel path
		File f = new File("C:\\Users\\Kalilur Rahman\\eclipse-workspace\\FW-DD-SAMPLE-CHALLENGE2\\Excel\\kash.xlsx");
		//3. to read .xlsx file format
		//create a object for XSSF Workbook
		//This method is in interface so they done as UPCASTING METHOD
		Workbook w = new XSSFWorkbook();
		//4.create sheet from workbook
		Sheet newSheet = w.createSheet("Datas");
		//to create a new row as rowNum
		Row newRow = newSheet.createRow(rowNum);
		//to create a new cell as cellNum
		Cell newCell = newRow.createCell(cellNum);
		//we can set the value in cell
		newCell.setCellValue(newData);
		//file output stream
		FileOutputStream fos = new FileOutputStream(f);
		//to write a output stream
		w.write(fos);	
	}
	//to create a cell
	public static void createCell(int rowNum, int cellNum, String newData) throws IOException {
		//1.object creation for file and mention excel path
		File f = new File("C:\\Users\\Kalilur Rahman\\eclipse-workspace\\FW-DD-SAMPLE-CHALLENGE2\\Excel\\kash.xlsx");
		//2.to read the file
		FileInputStream fis  = new FileInputStream(f);
		//3. to read .xlsx file format
		//create a object for XSSF Workbook
		//This method is in interface so they done as UPCASTING METHOD
		Workbook wb = new XSSFWorkbook(fis);
		//4.get sheet from workbook
		Sheet s = wb.getSheet("Datas");
		//to get a Row as rowNum
		Row r = s.getRow(rowNum);
		//to create a cell as cellNum
		Cell c = r.createCell(cellNum);
		//we can set the value in cell
		c.setCellValue(newData);
		//file output stream
		FileOutputStream fos = new FileOutputStream(f);
		//to write a output stream
		wb.write(fos);	
	}
	//to create a Row
	public static void createRow(int creRow, int creCell, String newData) throws IOException {
		//1.object creation for file and mention excel path
		File f = new File("C:\\Users\\Kalilur Rahman\\eclipse-workspace\\FW-DD-SAMPLE-CHALLENGE2\\Excel\\kash.xlsx");
		//2.to read the file
		FileInputStream fis  = new FileInputStream(f);
		//3. to read .xlsx file format
		//create a object for XSSF Workbook
		//This method is in interface so they done as UPCASTING METHOD
		Workbook wb = new XSSFWorkbook(fis);
		//4.get sheet from workbook
		Sheet s = wb.getSheet("Datas");	
		//to create a Row as creRow
		Row r = s.createRow(creRow);
		//to create a cell as creCell
		Cell c = r.createCell(creCell);
		//we can set the value in cell
		c.setCellValue(newData);
		//file output stream
		FileOutputStream fos = new FileOutputStream(f);
		//to write a output stream
		wb.write(fos);
	}
	//to update data to particular cell
	public static void updateDataToParticularCell(int getTheRow, int getTheCell, String exisitingData, String writeNewData) throws IOException {
		//1.object creation for file and mention excel path
		File f = new File("C:\\Users\\Kalilur Rahman\\eclipse-workspace\\FW-DD-SAMPLE-CHALLENGE2\\Excel\\kash.xlsx");
		//2.to read the file
		FileInputStream fis  = new FileInputStream(f);
		//3. to read .xlsx file format
		//create a object for XSSF Workbook
		//This method is in interface so they done as UPCASTING METHOD
		Workbook wb = new XSSFWorkbook(fis);
		//4.get sheet from workbook
		Sheet s = wb.getSheet("Datas");	
		//to get the row as getTheRow
		Row r = s.getRow(getTheRow);
		//to get the cell as getTheCell
		Cell c = r.getCell(getTheCell);
		//to get a value present in a cell
		String str = c.getStringCellValue();
		if (str.equals(exisitingData)) {
			//we can set the value in cell
			c.setCellValue(writeNewData);	
		}
		//file output stream
		FileOutputStream fos = new FileOutputStream(f);
		//to write a output stream
		wb.write(fos);	
	}
	//to write the data in Excel
	public static void WriteData(int createRow, int createCell, String setCellValue) throws IOException {
		//1.object creation for file and mention excel path
		File f = new File("C:\\Users\\Kalilur Rahman\\eclipse-workspace\\FW-DD-SAMPLE-CHALLENGE1\\Excel\\challenge1.xlsx");
		//parent class want to call means first create a Object with upCasting method
		Workbook wb = new XSSFWorkbook();
		//to create a sheet
		Sheet mysheet = wb.createSheet("data");
		//to create a Row
		Row newRow = mysheet.createRow(0);
		//to create a cell
		Cell newCell = newRow.createCell(0);
		//first want to set the values in cell ---->setCellValue
		newCell.setCellValue("selenium");
		//to write a normal file using FileOutputStream
		FileOutputStream fos = new FileOutputStream(f);
		//write the datas
		wb.write(fos);
	}
	
	
	
	
	

}
