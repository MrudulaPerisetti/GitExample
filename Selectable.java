package SDET.SDET_Assignment1_Selenium;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
public class Selectable {
	//Creating WebDriver Object
	WebDriver driver;
	@BeforeClass
	public void startchrome() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		//Maximizing the browser window
		driver.manage().window().maximize();
		//Accessing the url by using get method
		driver.get("https://demo.automationtesting.in/Selectable.html");
	}
	@Test
	public void Default_Functionality() {
		//In this default functionality we cannot perform any action. But I done clicking on each element by using loop
		//Working fine at last it is showing element not interactable
		List<WebElement> dates = driver.findElements(By.xpath("//li[@style='text-align: left; height: 32px']"));
		int count = dates.size();
		for(int i=0;i<count;i++)
		{
			String text=driver.findElements(By.xpath("//li[@style='text-align: left; height: 32px']")).get(i).getText();
			//Printing the text of an button
			System.out.println(text);
			driver.findElements(By.xpath("//li[@style='text-align: left; height: 32px']")).get(i).click();
			//Used implicit wait to see the flow clearly
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
	}
	@Test
	public void Serialize() {
		//Clicking serialize button
		driver.findElement(By.xpath("//a[@href='#Serialize']")).click();
		List<WebElement> dates = driver.findElements(By.xpath("//ul[@class='SerializeFunc']//li[@style='text-align: left; height: 32px']"));
		int count = dates.size();
		//Iterating through each and every element
		for(int i=0;i<count;i++)
		{
			String text=driver.findElements(By.xpath("//ul[@class='SerializeFunc']//li[@style='text-align: left; height: 32px']")).get(i).getText();
			//Spliting obtained visible text
			String[] Split=text.split("Sakinalium - ");
			String s="";
			for(int j=0;j<Split.length;j++) {
				//concatinating each and every character after split
				s=s+Split[j];
			}
			driver.findElements(By.xpath("//ul[@class='SerializeFunc']//li[@style='text-align: left; height: 32px']")).get(i).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//Getting text result from the popup message
			String data=driver.findElement(By.id("result")).getText();
			//comparing splitted test and popup test
			assertEquals("You've selected: "+s,"You've selected: "+data);
		}
	}
	@AfterClass
	public void ClosingBrowser() {
		//Finally closing the browser
		driver.close();
	}	
}
