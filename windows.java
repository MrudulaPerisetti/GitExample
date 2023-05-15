package SDET.SDET_Assignment1_Selenium;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class windows {
	//Creating WebDriver Object
	WebDriver driver;
	String parent,child;
	@BeforeTest
	public void startchrome() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		//Maximizing the browser window
		driver.manage().window().maximize();
		//Accessing the url by using get method
		driver.get("https://demo.automationtesting.in/Windows.html");	
	}
	
	@Test(priority=1)
	public void Tabbed_Window() {
		//Clicking on new tabbed window
		driver.findElement(By.xpath("//a[@href='#Tabbed']")).click();
		//click button to open next window
		driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
		Set parentWindow=driver.getWindowHandles();
		Iterator t=parentWindow.iterator();
		//parent id
		parent=(String) t.next();
		//child id
		child=(String) t.next();
		//switching to child window
		driver.switchTo().window(child);
		//Validation
		assertEquals(driver.getTitle(),"Selenium");
		//closing child window
		driver.close();
		
	}
	
	@Test(priority=2)
	public void Seperate_Windows() {
		driver.switchTo().window(parent);
		//Applying implicit wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@href='#Seperate']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		Set parentWindow=driver.getWindowHandles();
		Iterator t=parentWindow.iterator();
		parent=(String) t.next();
		child=(String) t.next();
		driver.switchTo().window(child);
		assertEquals(driver.getTitle(),"Selenium");
		driver.close();
	}
	
	@Test(priority=3)
	public void Multiple_Windows() {
		driver.switchTo().window(parent);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@href='#Multiple']")).click();
		driver.findElement(By.xpath("//*[@id=\"Multiple\"]/button")).click();
		Set parentWindow=driver.getWindowHandles();
		String parent=driver.getWindowHandle();
		Iterator t=parentWindow.iterator();
		//Iterating through No.of child windows
		while(t.hasNext()) {
			String child=(String)t.next();
			if(!parent.equals(child)) {
				driver.switchTo().window(child);
				System.out.println(driver.switchTo().window(child).getTitle());
				driver.close();
			}
		}
		//Switching to parent window and validating the title
		driver.switchTo().window(parent);
		assertEquals(driver.switchTo().window(parent).getTitle(),"Frames & windows");
	}
	
	@AfterClass
	public void ClosingBrowser() {
		driver.close();
	}	

}
