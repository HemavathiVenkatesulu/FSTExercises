package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.Timeout;
import io.github.bonigarcia.wdm.WebDriverManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC005_SauceLabDemo {
	
	WebDriver driver;

	@BeforeTest
	public void browser_launch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}


	@Test
	public void test() {
		WebElement LoginBtn = driver.findElement(By.id("login-button"));
		
		Assert.assertEquals(driver.getTitle(), "Swag Labs");
		System.out.println("Page title is verified");
		
		WebElement UserName=driver.findElement(By.xpath("//*[@id=\"user-name\"]"));
		UserName.sendKeys("standard_user");
		WebElement password=driver.findElement(By.xpath("//*[@id=\"password\"]"));
		password.sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();



		Select drpdwn = new Select((driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select"))));
		WebElement option = drpdwn.getFirstSelectedOption();
		Assert.assertEquals(option.getText(), "Name (A to Z)");

		WebElement Add_Cart=driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]"));
		Add_Cart.click();

		String actual_cart = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).getText();
		Assert.assertEquals(1, actual_cart);
		

		WebElement lastProduct=driver.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
		lastProduct.click();
		String sec_actual_cart = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).getText();
		Assert.assertEquals(2, sec_actual_cart);

		
		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();
		driver.findElement(By.id("remove-sauce-labs-backpack")).click();
		driver.findElement(By.name("continue-shopping")).click();

		String After_removal = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a/span")).getText();
		Assert.assertEquals(1, After_removal);

		driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[3]/a")).click();

		String cart_quantity = driver.findElement(By.cssSelector(".cart_quantity")).getText();
		if (cart_quantity.equals("1")) {
			System.out.println("Added Product is available");
		} 

	
		driver.findElement(By.name("continue-shopping")).click();

		
		Select dropdown = new Select(
				(driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select"))));
		dropdown.selectByVisibleText("Price (low to high)");

		
		Select dropdown2 = new Select(
				(driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select"))));
		WebElement opt2 = dropdown2.getFirstSelectedOption();
		String price_low_act = opt2.getText();
		Assert.assertEquals("Price (low to high)", price_low_act);

	}
	@AfterTest
	public void browser_close() {
		driver.quit();
	}

}
