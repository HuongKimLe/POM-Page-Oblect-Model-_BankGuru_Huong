package com.bankguru.user;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_01_RegisterAndLoginToSystem {
	WebDriver driver;
	WebDriverWait wait;
	private String UserID, Password, LoginPageUrl;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.demo.guru99.com/v4/");

	}

	@Test
	public void TC_01_Register() {

		LoginPageUrl = driver.getCurrentUrl();
		/* Click here link to open the register page */
		driver.findElement(By.xpath("//a[text()='here']")).click();

		// Wait for email textbox visible
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='emailid']"))));

		// Input email random
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys("huongtest01" + randomNumberEmail() + "@gmail.com");

		// Click Submit button

		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		// validate the page after register success

		driver.findElement(By.xpath("//h2[text()='Access details to demo site.']")).isDisplayed();

		// get text of userid and password

		UserID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {

		// open login page again
		driver.get(LoginPageUrl);

		// Wait for page visible
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name ='uid']"))));

		// Input data to user/ password textbox
		driver.findElement(By.xpath("//input[@name ='uid']")).sendKeys(UserID);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);

		// Click Login button
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		// Login success
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

	public int randomNumberEmail() {

		Random rand = new Random();
		int n = rand.nextInt(999999) + 1;

		return n;

	}

}
