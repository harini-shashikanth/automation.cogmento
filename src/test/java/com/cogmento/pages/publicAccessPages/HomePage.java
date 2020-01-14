/**
 * 
 */
package com.cogmento.pages.publicAccessPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cogmento.common.Config;
import com.cogmento.pages.common.GenericPage;

/**
 * @author Harini
 *
 */
public class HomePage extends GenericPage {
	// page entities
	private WebElement signUpBtn;
	private WebElement loginBtn;

	// constructor
	public HomePage(WebDriver driver) {
		setDriver(driver);
	}

	// getters and setters
	public WebElement getSignUpBtn() {
		signUpBtn = getDriver().findElement(By.xpath("//span[contains(text(),'sign up')]"));
		return signUpBtn;
	}

	public WebElement getLoginBtn() {
		loginBtn = getDriver().findElement(By.xpath("//span[contains(text(),'Log In')]"));
		return loginBtn;
	}

	// actions
	public void go2SignUpPage() {
		getSignUpBtn().click();
	}

	public void go2LoginPage() {
		getLoginBtn().click();
	}

	public void loadHomePage() {
		getDriver().get(Config.getProperty("homePageUrl"));
	}
}