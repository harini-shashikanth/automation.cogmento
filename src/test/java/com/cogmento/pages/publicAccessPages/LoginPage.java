/**
 * 
 */
package com.cogmento.pages.publicAccessPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cogmento.pages.common.GenericPage;

/**
 * @author Harini
 *
 */
public class LoginPage extends GenericPage {
	private WebElement usernameTxt;
	private WebElement passwordTxt;
	private WebElement submitBtn;

	public LoginPage(WebDriver driver) {
		super.setDriver(driver);
	}

	public WebElement getUsernameTxt() {
		usernameTxt = getDriver().findElement(By.xpath("//input[@placeholder='E-mail address']"));
		return usernameTxt;
	}

	public WebElement getPasswordTxt() {
		passwordTxt = getDriver().findElement(By.xpath("//input[@placeholder='Password']"));
		return passwordTxt;
	}

	public WebElement getSubmitBtn() {
		submitBtn = getDriver().findElement(By.xpath("//div[@class='ui fluid large blue submit button']"));
		return submitBtn;
	}
	/*
	 * Actions
	 */

	public void login(String username, String password) {
		getUsernameTxt().sendKeys(username);
		getPasswordTxt().sendKeys(password);
		getSubmitBtn().click();
	}
}
