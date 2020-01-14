/**
 * All user pages (after login) have similar structure. 
 * They have the same left section and the top section. 
 * This has been included into this template class. 
 * Each page will inherit this class. 
 * This way they will inherit the left and top sections, and the associated elements and actions.
 *  
 */
package com.cogmento.pages.userAccessPages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.cogmento.pages.common.GenericPage;

/**
 * @author Harini
 *
 */
public class UserPageTemplate extends GenericPage {
	/*
	 * Left Vertical Menu Bar
	 */
	private WebElement homeLnk;
	private WebElement calendarLnk;
	private WebElement contactsLnk;
	private WebElement companiesLnk;
	private WebElement dealsLnk;
	private WebElement tasksLnk;
	private WebElement casesLnk;
	private WebElement callsLnk;
	private WebElement documentsLnk;
	private WebElement emailLnk;
	private WebElement campaignsLnk;
	private WebElement formsLnk;
	/*
	 * Top Horizontal Bar
	 */
	private WebElement nameDisplay;
	private WebElement deleteRecordsLnk;
	private WebElement pinnedRecordsLnk;

	/*
	 * Getters for Left Vertical Menu Bar
	 */
	public WebElement getHomeLnk() {
		homeLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Home')]"));
		return homeLnk;
	}

	public WebElement getCalendarLnk() {
		calendarLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Calendar')]"));
		return calendarLnk;
	}

	public WebElement getContactsLnk() {
		contactsLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Contacts')]"));
		return contactsLnk;
	}

	public WebElement getCompaniesLnk() {
		companiesLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Companies')]"));
		return companiesLnk;
	}

	public WebElement getDealsLnk() {
		dealsLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Deals')]"));
		return dealsLnk;
	}

	public WebElement getTasksLnk() {
		tasksLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Tasks')]"));
		return tasksLnk;
	}

	public WebElement getCasesLnk() {
		casesLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Cases')]"));
		return casesLnk;
	}

	public WebElement getCallsLnk() {
		callsLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Calls')]"));
		return callsLnk;
	}

	public WebElement getDocumentsLnk() {
		documentsLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Documents')]"));
		return documentsLnk;
	}

	public WebElement getEmailLnk() {
		emailLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Email')]"));
		return emailLnk;
	}

	public WebElement getCampaignsLnk() {
		campaignsLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Campaigns')]"));
		return campaignsLnk;
	}

	public WebElement getFormsLnk() {
		formsLnk = getDriver().findElement(By.xpath("//span[contains(text(),'Forms')]"));
		return formsLnk;
	}

	/*
	 * Getters for Top Horizontal Bar
	 */
	public WebElement getNameDisplay() {
		nameDisplay = getDriver().findElement(By.xpath("//span[@class='user-display']"));
		return nameDisplay;
	}

	public WebElement getDeleteRecordsLnk() {
		deleteRecordsLnk = getDriver().findElement(By.xpath("//i[@class='pin icon']"));
		return deleteRecordsLnk;
	}

	public WebElement getPinnedRecordsLnk() {
		pinnedRecordsLnk = getDriver().findElement(By.xpath("//i[@class='red trash alternate outline icon']"));
		return pinnedRecordsLnk;
	}

	
}
