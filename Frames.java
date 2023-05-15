package SDET.SDET_Assignment1_Selenium;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames {
	//Creating WebDriver Object
	WebDriver driver;
	@BeforeClass
	public void startchrome() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		//Maximizing the browser window
		driver.manage().window().maximize();
		//Accessing the url by using get method
		driver.get("https://demo.automationtesting.in/Frames.html");	
	}
	@Test
	public void FirstFrame() {
		// Single Frame1
		driver.switchTo().frame(0);
		//Enter string on textbox in iframe
		driver.findElement(By.tagName("input")).sendKeys("Mrudula"); 
	}
	@Test
	public void IFrameWithinIFrame() {
		// Switching to main frame
		driver.switchTo().defaultContent(); 
		driver.findElement(By.xpath("//a[text() = 'Iframe with in an Iframe']")).click();
		// switching to inner frame1
		WebElement frame1=driver.findElement(By.xpath("//iframe[contains(@src,'MultipleFrames')]")); 
		driver.switchTo().frame(frame1);
		// switching to inner frame2
		WebElement frame2=driver.findElement(By.xpath("//iframe[contains(@src,'SingleFrame')]")); 
		driver.switchTo().frame(frame2);
		//Enter string on textbox
		driver.findElement(By.tagName("input")).sendKeys("Mrudula_Perisetti"); 
	}
	@AfterClass
	public void ClosingDriver() {
		assertEquals(driver.getTitle(),"Frames");
		driver.close();
	}
}