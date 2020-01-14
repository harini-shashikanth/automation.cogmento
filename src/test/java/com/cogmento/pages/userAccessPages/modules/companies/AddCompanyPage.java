/**
 * 
 */
package com.cogmento.pages.userAccessPages.modules.companies;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cogmento.common.BrowserUtil;
import com.cogmento.common.ExcelUtil;
import com.cogmento.pages.userAccessPages.common.UserPageTemplate;

/**
 * @author Harini
 *
 */
public class AddCompanyPage extends UserPageTemplate {
	/**
	 * 
	 */
	// fields
	WebElement nameTxt;
	WebElement accessBtn;
	WebElement websiteTxt;
	WebElement fetchBtn;
	WebElement streetAddressTxt;
	WebElement cityTxt;
	WebElement stateTxt;
	WebElement zipCodeTxt;
	WebElement countryDropdown;
	WebElement phoneCountryDropdown;
	WebElement phoneTxt;
	WebElement phoneType;
	WebElement tagsTxt;
	WebElement descriptionTxt;
	@FindBy(xpath = "//div[@class='three fields']")
	List<WebElement> socialChannelBlocks;
	WebElement industryTxt;
	WebElement numberOfEmployeesTxt;
	WebElement stockSymbolTxt;
	WebElement annualRevenueTxt;
	WebElement priorityDropdown;
	WebElement statusDropdown;

	@FindBy(xpath = "//div[@name='source']")
	WebElement sourceDropdown;

	WebElement categoryDropdown;
	WebElement vatNumberTxt;
	WebElement identifierTxt;
	WebElement imageTxt;
	WebElement saveBtn;
	WebElement cancelBtn;

	// constructor
	public AddCompanyPage(WebDriver driver) {
		super.setDriver(driver);
		PageFactory.initElements(getDriver(), this);
	}

	// actions
	public void addCompany(Map<String, String> newCompany) {
		getNameTxt().sendKeys(newCompany.get("Name"));
		getWebsiteTxt().sendKeys(newCompany.get("Website"));
		getStreetAddressTxt().sendKeys(newCompany.get("Address - Street Address"));
		getCityTxt().sendKeys(newCompany.get("Address - City"));
		getStateTxt().sendKeys(newCompany.get("Address - State/County"));
		getZipCodeTxt().sendKeys(newCompany.get("Address - Zip Code"));
		//
		getCountryDropdown().findElement(By.xpath(".//input[@class='search']")).sendKeys(newCompany.get("Address - Country"));
		String country = newCompany.get("Address - Country");
		String countryXpath = ".//span[contains(text(),'" + country + "')]";
		getCountryDropdown().findElement(By.xpath(countryXpath)).click();
		//
		getPhoneCountryDropdown().findElement(By.xpath(".//input[@class='search']")).sendKeys(newCompany.get("Phone - Country"));
		String phoneCountry = newCompany.get("Phone - Country");
		String phoneCountryXpath = ".//span[contains(text(),'" + phoneCountry + "')]";
		getPhoneCountryDropdown().findElement(By.xpath(phoneCountryXpath)).click();
		//
		getPhoneTxt().sendKeys(newCompany.get("Phone - Number"));
		getPhoneType().sendKeys(newCompany.get("Phone - Type"));
		//
		getTagsTxt().findElement(By.xpath(".//input[@class='search']")).sendKeys(newCompany.get("Tag - tag1") + Keys.ENTER);
		getTagsTxt().findElement(By.xpath(".//input[@class='search']")).sendKeys(newCompany.get("Tag - tag1") + Keys.ENTER);
		getDescriptionTxt().sendKeys(newCompany.get("Description"));
		// Social Channels
		int i;
		WebElement lastBlock;
		// Twitter
		i = getSocialChannelBlocks().size();
		lastBlock = getSocialChannelBlocks().get(i - 1);
		lastBlock.findElement(By.xpath(".//div[@class='text']")).click();
		lastBlock.findElement(By.xpath(".//span[contains(text(),'Twitter')]")).click();
		lastBlock.findElement(By.xpath(".//input")).sendKeys(newCompany.get("Social Channels - Twitter Handle"));
		lastBlock.findElement(By.xpath(".//i[@class='add icon']")).click();
		// Facebook
		i = getSocialChannelBlocks().size();
		lastBlock = getSocialChannelBlocks().get(i - 1);
		lastBlock.findElement(By.xpath(".//div[@class='text']")).click();
		lastBlock.findElement(By.xpath(".//span[contains(text(),'Facebook')]")).click();
		lastBlock.findElement(By.xpath(".//input")).sendKeys(newCompany.get("Social Channels - Facebook Profile Link"));
		lastBlock.findElement(By.xpath(".//i[@class='add icon']")).click();
		// LinkedIn
		i = getSocialChannelBlocks().size();
		lastBlock = getSocialChannelBlocks().get(i - 1);
		lastBlock.findElement(By.xpath(".//div[@class='text']")).click();
		lastBlock.findElement(By.xpath(".//span[contains(text(),'LinkedIn')]")).click();
		lastBlock.findElement(By.xpath(".//input")).sendKeys(newCompany.get("Social Channels - LinkedIn Profile Link"));
		//
		getIndustryTxt().sendKeys(newCompany.get("Industry"));
		getNumberOfEmployeesTxt().sendKeys(newCompany.get("Num. of Employees"));
		getStockSymbolTxt().sendKeys(newCompany.get("Stock Symbol"));
		getAnnualRevenueTxt().sendKeys(newCompany.get("Annual Revenue"));
		// Priority
		getPriorityDropdown().click();
		String priority = newCompany.get("Priority");
		String xpathPriority = ".//span[contains(text(),'" + priority + "')]";
		getPriorityDropdown().findElement(By.xpath(xpathPriority)).click();
		// Status
		getStatusDropdown().click();
		String status = newCompany.get("Status");
		String xpathStatus = ".//span[contains(text(),'" + status + "')]"; // <span class="text">New</span>
		getStatusDropdown().findElement(By.xpath(xpathStatus)).click();
		// Source
		getSourceDropdown().click();
		String source = newCompany.get("Source");
		String sourceXpath = ".//span[contains(text(),'" + source + "')]"; // <span class="text">Customer</span>
		getSourceDropdown().findElement(By.xpath(sourceXpath)).click();
		// Category
		getCategoryDropdown().click();
		String category = newCompany.get("Category");
		String categoryXpath = ".//span[contains(text(), '" + category + "')]"; // <span class="text">Client</span>
		getCategoryDropdown().findElement(By.xpath(categoryXpath)).click();
		//
		getVatNumberTxt().sendKeys(newCompany.get("VAT Number"));
		getIdentifierTxt().sendKeys(newCompany.get("Identifier"));
		//image upload
		getImageTxt().click();
		String exeFileNameSuffix = newCompany.get("exe file suffix for Image Upload");
		String exeFileNamePrefix = BrowserUtil.getBrowserName(getDriver()) + "_";
		String exeFileName = exeFileNamePrefix + exeFileNameSuffix;
		String exeAbsolutePath = ExcelUtil.getAbsolutePath("exe\\" + exeFileName);
		
		try {
			Runtime.getRuntime().exec(exeAbsolutePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BrowserUtil.sleep(5000);
		getSaveBtn().click();
		BrowserUtil.sleep(5000);
	}

	// getters
	public WebElement getNameTxt() {
		nameTxt = getDriver().findElement(By.xpath("//input[@name='name']"));
		return nameTxt;
	}

	public WebElement getAccessBtn() {
		accessBtn = getDriver().findElement(By.xpath("//button[@class='ui small fluid positive toggle button']"));
		return accessBtn;
	}

	public WebElement getWebsiteTxt() {
		websiteTxt = getDriver().findElement(By.xpath("//input[@name='url']"));
		return websiteTxt;
	}

	public WebElement getFetchBtn() {
		fetchBtn = getDriver().findElement(By.xpath("//button[contains(text(),'Fetch')]"));
		return fetchBtn;
	}

	public WebElement getStreetAddressTxt() {
		streetAddressTxt = getDriver().findElement(By.xpath("//input[@placeholder='Street Address']"));
		return streetAddressTxt;
	}

	public WebElement getCityTxt() {
		cityTxt = getDriver().findElement(By.xpath("//input[@placeholder='City']"));
		return cityTxt;
	}

	public WebElement getStateTxt() {
		stateTxt = getDriver().findElement(By.xpath("//input[@placeholder='State / County']"));
		return stateTxt;
	}

	public WebElement getZipCodeTxt() {
		zipCodeTxt = getDriver().findElement(By.xpath("//input[@placeholder='Zip Code']"));
		return zipCodeTxt;
	}

	public WebElement getCountryDropdown() {
		countryDropdown = getDriver().findElement(By.xpath("//div[@name='country']"));
		return countryDropdown;
	}

	public WebElement getPhoneCountryDropdown() {
		phoneCountryDropdown = getDriver().findElement(By.xpath("//div[@name='hint']"));
		return phoneCountryDropdown;
	}

	public WebElement getPhoneTxt() {
		phoneTxt = getDriver().findElement(By.xpath("//input[@placeholder='Number']"));
		return phoneTxt;
	}

	public WebElement getPhoneType() {
		phoneType = getDriver().findElement(By.xpath("//input[@placeholder='Home, Work, Mobile...']"));
		return phoneType;
	}

	public WebElement getTagsTxt() {
		// tagsTxt = getDriver().findElement(By.xpath("//div[@class='ui fluid multiple
		// search selection dropdown']"));
		tagsTxt = getDriver().findElement(By.xpath("//div[contains(@class, 'fluid multiple search selection dropdown')]"));
		// div[@class='ui active visible fluid multiple search selection dropdown']
		return tagsTxt;
	}

	public WebElement getDescriptionTxt() {
		descriptionTxt = getDriver().findElement(By.xpath("//textarea[@name='description']"));
		return descriptionTxt;
	}

	public List<WebElement> getSocialChannelBlocks() {
		//socialChannelBlocks = getDriver().findElements(By.xpath("//div[@class='three fields']"));
		return socialChannelBlocks;
	}

	public WebElement getIndustryTxt() {
		industryTxt = getDriver().findElement(By.xpath("//input[@name='industry']"));
		return industryTxt;
	}

	public WebElement getNumberOfEmployeesTxt() {
		numberOfEmployeesTxt = getDriver().findElement(By.xpath("//input[@name='num_employees']"));
		return numberOfEmployeesTxt;
	}

	public WebElement getStockSymbolTxt() {
		stockSymbolTxt = getDriver().findElement(By.xpath("//input[@name='symbol']"));
		return stockSymbolTxt;
	}

	public WebElement getAnnualRevenueTxt() {
		annualRevenueTxt = getDriver().findElement(By.xpath("//input[@name='annual_revenue']"));
		return annualRevenueTxt;
	}

	public WebElement getPriorityDropdown() {
		priorityDropdown = getDriver().findElement(By.xpath("//div[@name='priority']"));
		return priorityDropdown;
	}

	public WebElement getStatusDropdown() {
		statusDropdown = getDriver().findElement(By.xpath("//div[@name='status']"));
		return statusDropdown;
	}

	public WebElement getSourceDropdown() {
		// sourceDropdown = getDriver().findElement(By.xpath("//div[@name='source']"));
		return sourceDropdown;
	}

	public WebElement getCategoryDropdown() {
		categoryDropdown = getDriver().findElement(By.xpath("//div[@name='category']"));
		return categoryDropdown;
	}

	public WebElement getVatNumberTxt() {
		vatNumberTxt = getDriver().findElement(By.xpath("//input[@name='vat_number']"));
		return vatNumberTxt;
	}

	public WebElement getIdentifierTxt() {
		identifierTxt = getDriver().findElement(By.xpath("//input[@name='identifier']"));
		return identifierTxt;
	}

	public WebElement getImageTxt() {
		imageTxt = getDriver().findElement(By.xpath("//input[@name='image']"));
		return imageTxt;
	}

	public WebElement getSaveBtn() {
		saveBtn = getDriver().findElement(By.xpath("//button[@class='ui linkedin button']"));
		return saveBtn;
	}

	public WebElement getCancelBtn() {
		cancelBtn = getDriver().findElement(By.xpath("//button[contains(text(),'Cancel')]"));
		return cancelBtn;
	}
}
