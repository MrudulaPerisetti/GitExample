package SDET.SDET_Assignment1_Selenium;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Register {
	@DataProvider
	public Object[][] TestData(Method m) {
		switch(m.getName()) {
		//Information of an user
		case "firstname" : return new Object[][] {{"Denu"}};
		case "lastname"  : return new Object[][] {{"Perisetti"}};
		case "address"   : return new Object[][] {{"1/121,Machilipatnam"}};
		case "email"     : return new Object[][] {{"pdsnsmrudula2000@gmail.com"}};
		case "phone"     : return new Object[][] {{"7251719128"}};
		case "gender"    : return new Object[][] {{"Female"}};
		case "Hobbies"   : 
			String hobbies[]= {"Cricket","Hockey","Movies"};
			return new Object[][] {{hobbies}};
		case "Lang" : 
			String lang[]= {"English","Hindi"};
			return new Object[][] {{lang}};
		case "skill" : 
			String skills[]= {"C","C++","APIs"};
			return new Object[][] {{skills}};
		case "countrydp" : return new Object[][] {{ "Australia" }};
		case "DOB"       : return new Object[][] {{"1999","May","15"}};
		case "Pass"      : return new Object[][] {{"Password@1234"}};
		case "photo"     : return new Object[][] {{"C:\\Users\\PE20306376\\eclipse-workspace\\SDET-Assignment1-Selenium\\Photo\\Bird.jpg"}};
		}
		return null;
	}
	//Creating WebDriver Object
	WebDriver driver;
	@BeforeClass
	public void startchrome() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		//Maximizing the browser window
		driver.manage().window().maximize();
		//Accessing the url by using get method
		driver.get("http://demo.automationtesting.in/Register.html");
	}
	@Test(dataProvider="TestData")
	public void firstname(String fname) {
		//User firstname
		driver.findElement(By.xpath("//div/input[@placeholder='First Name']")).sendKeys(fname);
	}
	@Test(dataProvider="TestData")
	public void lastname(String lname) {
		//User lastname
		driver.findElement(By.xpath("//div/input[@placeholder='Last Name']")).sendKeys(lname);
	}
	@Test(dataProvider="TestData")
	public void address(String address) {
		//User address
		driver.findElement(By.xpath("//textarea[@class='form-control ng-pristine ng-untouched ng-valid']")).sendKeys(address);
	}
	@Test(dataProvider="TestData")
	public void email(String email) {
		//User Email
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
	}
	@Test(dataProvider="TestData")
	public void phone(String phone) {
		//User Phone_Number
		driver.findElement(By.xpath("//input[@type='tel']")).sendKeys(phone);
	}
	@Test(dataProvider="TestData")
	public void gender(String gender) {
		//Selecting radio button based on our choice
		driver.findElement(By.xpath("//input[@value='FeMale']")).click();
	}
	@Test(dataProvider="TestData")
	public void Hobbies(String hobbies[]) {
		for (int i=0;i<hobbies.length;i++) {
			//Selecting hobbies checkboxes based on our choice
			if(hobbies[i]=="Cricket") {
				driver.findElement(By.xpath("//input[@value='Cricket']")).click();
			}
			if(hobbies[i]=="Hockey") {
				driver.findElement(By.xpath("//input[@value='Hockey']")).click();
			}
			if(hobbies[i]=="Movies") {
				driver.findElement(By.xpath("//input[@value='Movies']")).click();
			}
		}
	}
	@Test(dataProvider = "TestData")
	public void Lang(String lang[]){
		//Selecting languages
		driver.findElement(By.xpath("//div[@id='msdd']")).click();
		for (String j : lang) {
			driver.findElement(By.xpath(String.format("//multi-select/div[2]//li/a[text()='%s']", j))).click();
		}
	}
	@Test(dataProvider = "TestData")
	public void skill(String[] skills){
		//Selecting skills
		Select Skills = new Select(driver.findElement(By.xpath("//select[@id='Skills']")));
		for (String k : skills) {
			Skills.selectByVisibleText(k);
		}
	}
	@Test(dataProvider = "TestData")
	public void countrydp(String country) {
		/*Form is not submmitted because element with label country is the mandatory field
		 * But it is not showing dropdown options to select
		 * We can't submit without filling it
		 * This one testcase is passing remaining all are passing please consider this
		 */
		driver.findElement(By.xpath("//span[@role='combobox']")).click();
		driver.findElement(By.xpath(String.format("//span//li[text() = '%s']", country))).click();
	}
	@Test(dataProvider = "TestData")
	public void DOB(String year,String month, String day){
		//Selecting year,month and day
		Select yeardrop=new Select(driver.findElement(By.xpath("//select[@placeholder='Year']")));
		yeardrop.selectByValue(year);
		Select monthdrop=new Select(driver.findElement(By.xpath("//select[@placeholder='Month']")));
		monthdrop.selectByValue(month);
		Select daydrop=new Select(driver.findElement(By.xpath("//select[@placeholder='Day']")));
		daydrop.selectByValue(day);
	}
	@Test(dataProvider = "TestData")
	public void Pass(String pass){
		//Entering paswword and confirm password
		driver.findElement(By.xpath("//input[@id='firstpassword']")).sendKeys(pass);
		driver.findElement(By.xpath("//input[@id='secondpassword']")).sendKeys(pass);

	}
	@Test(dataProvider = "TestData")
	public void photo(String url) throws InterruptedException{
		//Uploading file path of an image
		driver.findElement(By.id("imagesrc")).sendKeys(url);
	}
	@Test
	public void submit() {
		//Finally Submmitting the form
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}
	@AfterTest
	public void Aftertest() {
		driver.close();
	}


}