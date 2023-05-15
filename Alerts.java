package SDET.SDET_Assignment1_Selenium;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Alerts {
	//Creating WebDriver Object
	WebDriver driver;
	@BeforeClass
	public void startchrome() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		//Maximizing the browser window
		driver.manage().window().maximize();
		//Accessing the url by using get method
		driver.get("https://demo.automationtesting.in/Alerts.html");
	}
	@Test
	public void alertOk() {
		//Clicking alert OK
		driver.findElement(By.xpath("//*[@id='OKTab']/button")).click();
		driver.switchTo().alert().accept();
	}
	@Test(dataProvider="TestData")
	public void alertOk_And_Cancle() {
		//Clicking OK
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"CancelTab\"]/button")).click();
		driver.switchTo().alert().accept();
		//Validating ok response message
		assertEquals(driver.findElement(By.id("demo")).getText(),"You pressed Ok");
		//Clicking Cancle
		driver.findElement(By.xpath("//*[@id=\"CancelTab\"]/button")).click();
		driver.switchTo().alert().dismiss();
		//Validating Cancle response message
		assertEquals(driver.findElement(By.id("demo")).getText(),"You pressed Cancel");
	}
	@Test
	public void alert_with_checkbox() {
		driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[3]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"Textbox\"]/button")).click();
		//Entering text on textbox
		driver.switchTo().alert().sendKeys("Mrudula");
		driver.switchTo().alert().accept();
		//Validating ok response message
		assertEquals(driver.findElement(By.id("demo1")).getText(),"Hello Mrudula How are you today");
		driver.findElement(By.xpath("//*[@id=\"Textbox\"]/button")).click();
		driver.switchTo().alert().sendKeys("Mrudula");
		driver.switchTo().alert().dismiss();	
	}
	@AfterClass
	public void ClosingDriver() {
		assertEquals(driver.getTitle(),"Frames");
		driver.close();
	}
}
