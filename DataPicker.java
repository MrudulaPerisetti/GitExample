package SDET.SDET_Assignment1_Selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DataPicker {
	//Creating WebDriver Object
	WebDriver driver;
	@BeforeClass
	public void startchrome() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		//Maximizing the browser window
		driver.manage().window().maximize();
		//Accessing the url by using get method
		driver.get("https://demo.automationtesting.in/Datepicker.html");	
	}
	@Test
	public void Cal1_DataPicker_Disabled() {
		//Declare year to select in the calender
		String year="2024";
		String action="";
		//Click action on cal
		driver.findElement(By.xpath("//img[@class='imgdp']")).click();
		while(true) {
			WebElement Syear=driver.findElement(By.xpath("//div/span[@class='ui-datepicker-year']"));
			//Getting current year from the calender
			String Currentyear=Syear.getText();
			if(Currentyear.equalsIgnoreCase(year)) {
				break;
			}
			//Coverting string to int
			int CyearInt=Integer.parseInt(Currentyear);
			int yearInt=Integer.parseInt(year);
			if(CyearInt<yearInt) {
				driver.findElement(By.xpath("//a/span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
				action="Next";
			}
			if(CyearInt>yearInt){
				driver.findElement(By.xpath("//a/span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
				action="Prev";
			}
		}
		//declaring month to select
		String month="March";
		while(true) {
			WebElement Smonth=driver.findElement(By.xpath("//div/span[@class='ui-datepicker-month']"));
			//Getting current month from the calender
			String CurrentMonth=Smonth.getText();
			if(CurrentMonth.equals(month)) {
				break;
			}
			if(action.equals("Next")) {
				driver.findElement(By.xpath("//a/span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			}
			if(action.equals("Prev")) {
				driver.findElement(By.xpath("//a/span[@class='ui-icon ui-icon-circle-triangle-w']")).click();
			}
		}
		String day="5";
		List<WebElement> dates = driver.findElements(By.xpath("//a[@class='ui-state-default']"));
		int count = dates.size();
		//Selecting date from the calender
		for(int i=0;i<count;i++)
		{
			String text=driver.findElements(By.xpath("//a[@class='ui-state-default']")).get(i).getText();
			if(text.equalsIgnoreCase(day))
			{
				driver.findElements(By.xpath("//a[@class='ui-state-default']")).get(i).click();
				break;
			}
		}
	}
	@Test
	public void Cal2_DataPicker_Enabled() throws InterruptedException {
		WebElement s=driver.findElement(By.id("datepicker2"));
		s.click();
		Thread.sleep(2000);
		//Declaring date to select
		String month="July";
		String year="2025";
		String day="15";
		//Selecting month from calender dropdown list
		WebElement drop1=driver.findElement(By.xpath("//select[@title='Change the month']"));
		Select staticdrop=new Select(drop1);
		staticdrop.selectByVisibleText(month);
		//Selecting year from calender dropdown list
		WebElement drop2=driver.findElement(By.xpath("//select[@title='Change the year']"));
		Select staticdrop2=new Select(drop2);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		staticdrop2.selectByVisibleText(year);
		//Selecting date from the calender
		List<WebElement> dates = driver.findElements(By.xpath("//a[@href='javascript:void(0)']"));
		int count = dates.size();
		for(int i=0;i<count;i++)
		{
			String text=driver.findElements(By.xpath("//a[@href='javascript:void(0)']")).get(i).getText();
			if(text.equalsIgnoreCase(day))
			{
				driver.findElements(By.xpath("//a[@href='javascript:void(0)']")).get(i).click();
				break;
			}
		}
		((WebElement) driver.findElements(By.xpath("//a[@title='Close the datepicker']"))).click();
	}
}


