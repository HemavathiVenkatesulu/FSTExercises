package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.Timeout;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC002_ForgotPassword {

	public String url = "https://dev100605.service-now.com/navpage.do";
	public WebDriver driver;

	@BeforeTest
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
	}

	@Test
	public void ForgotPassword() throws InterruptedException {
	
		driver.findElement(By.xpath("//a[contains(text(),'Forgot Password')]"));
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Forgot Password')]")).isDisplayed());
		
		driver.findElement(By.xpath("//a[contains(text(),'Forgot Password')]")).click();
	
		WebElement IdentifyTab=driver.findElement(By.xpath("//ol//li[1]"));
		if(IdentifyTab.isDisplayed()) 
		{	
		System.out.println("Identify tab is displayed");		
		}
		
		WebElement VerifyTab=driver.findElement(By.xpath("//ol//li[2]"));
		if(VerifyTab.isDisplayed()) {
			
			System.out.println("Verify tab is dispalyed");			
		}
		
		WebElement ResetTab=driver.findElement(By.xpath("//ol//li[3]"));
		if(ResetTab.isDisplayed()){
			
			System.out.println("Reset tab is displayed");		
			
		}
		WebElement UserNamelbl=driver.findElement(By.xpath("//label[contains(text(),'User name')]"));
		if(UserNamelbl.isDisplayed()) {
			
			System.out.println("UserName label is displayed");
			
		}
		WebElement UserNametxt=driver.findElement(By.id("sysparm_user_id_0"));
		if(UserNametxt.isDisplayed()) {
			
			System.out.println("UserName textfield is displayed");
			
		}
		WebElement NextButton=driver.findElement(By.xpath("//button[contains(text(),'Next')]"));
		if(NextButton.isDisplayed()) {
			System.out.println("Button Next is displayed");	
		}
		
		UserNametxt.sendKeys("admin");
		NextButton.click();
		Thread.sleep(1000);
	}

	
	@AfterTest
	public void tearDown() {
		driver.close();

	}

}
