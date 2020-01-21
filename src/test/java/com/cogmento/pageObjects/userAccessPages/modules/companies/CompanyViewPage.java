/**
 * 
 */
package com.cogmento.pageObjects.userAccessPages.modules.companies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cogmento.pageObjects.userAccessPages.UserPageTemplate;

/**
 * @author Harini
 *
 */
public class CompanyViewPage extends UserPageTemplate {

	String name;

	@FindBy(xpath = "//div[text()='Name']/..")
	WebElement nameElement;

	public CompanyViewPage(WebDriver driver) {
		setDriver(driver);
		PageFactory.initElements(getDriver(), this);
	}

	public String getName() {
		return nameElement.findElement(By.xpath(".//p")).getText();

	}
}
