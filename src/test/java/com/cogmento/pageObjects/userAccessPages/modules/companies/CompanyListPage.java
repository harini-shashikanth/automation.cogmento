/**
 * 
 */
package com.cogmento.pageObjects.userAccessPages.modules.companies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.cogmento.pageObjects.userAccessPages.UserPageTemplate;

/**
 * @author Harini
 *
 */
public class CompanyListPage extends UserPageTemplate {
	/**
	 * 
	 */
	private WebElement newCompanyBtn;

	public CompanyListPage(WebDriver driver) {
		super.setDriver(driver);
	}

	public WebElement getNewCompanyBtn() {
		newCompanyBtn = getDriver().findElement(By.xpath("//button[contains(text(),'New')]"));
		return newCompanyBtn;
	}
}
