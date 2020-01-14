package com.cogmento.pages.userAccessPages.modules.companies;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cogmento.common.BrowserUtil;
import com.cogmento.common.ExcelUtil;
import com.cogmento.pages.userAccessPages.common.UserPageTemplate;

public class CompanyAddOrEditPage extends UserPageTemplate {

	@FindBy(xpath = "//input[@name='name'][0]")
	WebElement labelElement;

	@FindBy(xpath = "//button[contains(text(),'Cancel')]")
	WebElement cancelElement;

	@FindBy(xpath = "//button[@class='ui linkedin button']")
	WebElement saveElement;

	@FindBy(xpath = "//input[@name='name'][1]")
	WebElement nameElement;

	@FindBy(xpath = "//button[@class='ui small fluid positive toggle button']")
	WebElement accessElement;

	@FindBy(xpath = "//div[@class='twelve wide field']")
	WebElement usersAllowedAccessElement;

	@FindBy(xpath = "//input[@name='url']")
	WebElement websiteElement;

	@FindBy(xpath = "//button[contains(text(),'Fetch')]")
	WebElement fetchElement;

	@FindBy(xpath = "//input[@placeholder='Street Address']")
	WebElement streetAddressElement;

	@FindBy(xpath = "//input[@placeholder='City']")
	WebElement cityElement;

	@FindBy(xpath = "//input[@placeholder='State / County']")
	WebElement stateElement;

	@FindBy(xpath = "//input[@placeholder='Zip Code']")
	WebElement zipcodeElement;

	@FindBy(xpath = "//div[@name='country']")
	WebElement countryElement;

	@FindBy(xpath = "//div[@name='hint']")
	WebElement phoneCountryElement;

	@FindBy(xpath = "//input[@placeholder='Number']")
	WebElement phoneNumberElement;

	@FindBy(xpath = "//input[@placeholder='Home, Work, Mobile...']")
	WebElement phoneTypeElement;

	@FindBy(xpath = "//label[@for='tags']")
	WebElement tagsElement;

	@FindBy(xpath = "//textarea[@name='description']")
	WebElement descriptionElement;

	@FindBy(xpath = "//div[@class='three fields']")
	List<WebElement> socialChannelBlockElements;

	@FindBy(xpath = "//input[@name='industry']")
	WebElement industryElement;

	@FindBy(xpath = "//input[@name='num_employees']")
	WebElement numberOfEmployeesElement;

	@FindBy(xpath = "//input[@name='symbol']")
	WebElement stockSymbolElement;

	@FindBy(xpath = "//input[@name='annual_revenue']")
	WebElement annualRevenueElement;

	@FindBy(xpath = "//div[@name='priority']")
	WebElement priorityElement;

	@FindBy(xpath = "//div[@name='status']")
	WebElement statusElement;

	@FindBy(xpath = "//div[@name='source']")
	WebElement sourceElement;

	@FindBy(xpath = "//div[@name='category']")
	WebElement categoryElement;

	@FindBy(xpath = "//input[@name='vat_number']")
	WebElement vatNumberElement;

	@FindBy(xpath = "//input[@name='identifier']")
	WebElement identifierElement;

	@FindBy(xpath = "//input[@name='image']")
	WebElement imageElement;

	// variables to hold form element values
	String name;
	String access;
	String usersAllowedAccess;
	String website;
	String streetAddress;
	String city;
	String state;
	String zipcode;
	String country;
	String phoneCountry;
	String phoneNumber;
	String phoneType;
	String tags;
	String description;
	String twitterHandle;
	String faceBookProfileLink;
	String linkedInProfileLink;
	String industry;
	String numberOfEmployees;
	String stockSymbol;
	String annualRevenue;
	String priority;
	String status;
	String source;
	String category;
	String vatNumber;
	String identifier;
	String imageFileUpload;

	// constructor
	public CompanyAddOrEditPage(WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void addCompany(Map<String, String> company) {
		setName(company.get("Name"));
		setWebsite(company.get("Website"));
		setStreetAddress(company.get("Address - Street Address"));
		setCity(company.get("Address - City"));
		setState(company.get("Address - State/County"));
		setZipcode(company.get("Address - Zip Code"));
		setCountry(company.get("Address - Country"));
		setPhoneCountry(company.get("Phone - Country"));
		setPhoneNumber(company.get("Phone - Number"));
		setPhoneType(company.get("Phone - Type"));
		setTags(company.get("Tags"));
		setDescription(company.get("Description"));
		setTwitterHandle(company.get("Social Channels - Twitter Handle"));
		setFaceBookProfileLink(company.get("Social Channels - Facebook Profile Link"));
		setLinkedInProfileLink(company.get("Social Channels - LinkedIn Profile Link"));
		setIndustry(company.get("Industry"));
		setNumberOfEmployees(company.get("Num. of Employees"));
		setStockSymbol(company.get("Stock Symbol"));
		setAnnualRevenue(company.get("Annual Revenue"));
		setPriority(company.get("Priority"));
		setStatus(company.get("Status"));
		setSource(company.get("Source"));
		setCategory(company.get("Category"));
		setVatNumber(company.get("VAT Number"));
		setIdentifier(company.get("Identifier"));
		setImageFileUpload(company.get("exe file suffix for Image Upload"));
		BrowserUtil.sleep(10000);// give some time for OS control to execute and upload image
		saveElement.click();
	}

	public void setName(String name) {
		nameElement.clear();
		nameElement.sendKeys(name);
	}

	public void setAccess(String access) {
		String existingValue;
		if (accessElement.getText().contains("Public")) {
			existingValue = "Public";
		} else {
			existingValue = "Private";
		}
		if (!access.equalsIgnoreCase(existingValue)) {
			accessElement.click();// this will toggle between public and private
		}
	}

	public void setUsersAllowedAccess(String usersAllowedAccess) {
		if (accessElement.getText().contains("Private")) {
			// TODO
		}
	}

	public void setWebsite(String website) {
		websiteElement.clear();
		websiteElement.sendKeys(website);
	}

	public void setStreetAddress(String streetAddress) {
		streetAddressElement.clear();
		streetAddressElement.sendKeys(streetAddress);
	}

	public void setCity(String city) {
		cityElement.clear();
		cityElement.sendKeys(city);
	}

	public void setState(String state) {
		stateElement.clear();
		stateElement.sendKeys(state);
	}

	public void setZipcode(String zipcode) {
		zipcodeElement.clear();
		zipcodeElement.sendKeys(zipcode);
	}

	public void setCountry(String country) {
		countryElement.findElement(By.xpath(".//input[@class='search']")).sendKeys(country);
		String countryXpath = ".//span[text()='" + country + "']";
		countryElement.findElement(By.xpath(countryXpath)).click();
	}

	public void setPhoneCountry(String phoneCountry) {
		phoneCountryElement.findElement(By.xpath(".//input[@class='search']")).sendKeys(phoneCountry);
		String phoneCountryXpath = ".//span[contains(text(),'" + phoneCountry + "')]";
		phoneCountryElement.findElement(By.xpath(phoneCountryXpath)).click();
	}

	public void setPhoneNumber(String phoneNumber) {
		phoneNumberElement.clear();
		phoneNumberElement.sendKeys(phoneNumber);
	}

	public void setPhoneType(String phoneType) {
		phoneTypeElement.clear();
		phoneTypeElement.sendKeys(phoneType);
	}

	public void setTags(String tags) {
		tagsElement.click();
		for (String tag : tags.split(";")) {
			WebElement e;
			e = tagsElement.findElement(By.xpath(".//input[@class='search']"));
			e.sendKeys(tag); //
			List<WebElement> options = tagsElement.findElements(By.xpath(".//span[@class='text']"));
			int i = options.size();
			e = options.get(i - 1); // select the last entry
			e.click();// click the last option
		}
	}

	public void setDescription(String description) {
		descriptionElement.clear();
		descriptionElement.sendKeys(description);
	}

	public void setTwitterHandle(String twitterHandle) {
		// check if the twitter element is already present.
		// If yes, overwrite the value.
		// If no, add it and populate the value.
		boolean twitterPresent = false;
		int size;

		for (WebElement socialChannelBlockElement : socialChannelBlockElements) {
			size = socialChannelBlockElement.findElements(By.xpath(".//div[contains(text(),'Twitter')]")).size();

			if (size > 0) {
				twitterPresent = true;
			}

			if (twitterPresent) {
				socialChannelBlockElement.findElement(By.xpath(".//input")).clear();
				socialChannelBlockElement.findElement(By.xpath(".//input")).sendKeys(twitterHandle);
				break;
			}
		}
		if (!twitterPresent) {
			int numberOfBlocks = socialChannelBlockElements.size();
			WebElement lastBlock = socialChannelBlockElements.get(numberOfBlocks - 1);
			lastBlock.findElement(By.xpath(".//div[@class='text']")).click();

			numberOfBlocks = socialChannelBlockElements.size(); // it should have increased by 1
			WebElement newBlock = socialChannelBlockElements.get(numberOfBlocks - 1);
			newBlock.findElement(By.xpath(".//span[contains(text(),'Twitter')]")).click();
			newBlock.findElement(By.xpath(".//input")).sendKeys(twitterHandle);
		}
	}

	public void setFaceBookProfileLink(String faceBookProfileLink) {
		// check if the facebook element is already present.
		// If yes, overwrite the value.
		// If no, add it and populate the value.
		boolean facebookPresent = false;
		int size;

		for (WebElement socialChannelBlockElement : socialChannelBlockElements) {
			size = socialChannelBlockElement.findElements(By.xpath(".//div[contains(text(),'Facebook')]")).size();

			if (size > 0) {
				facebookPresent = true;
			}

			if (facebookPresent) {
				socialChannelBlockElement.findElement(By.xpath(".//input")).clear();
				socialChannelBlockElement.findElement(By.xpath(".//input")).sendKeys(faceBookProfileLink);
				break;
			}
		}
		if (!facebookPresent) {
			int numberOfBlocks = socialChannelBlockElements.size();
			WebElement lastBlock = socialChannelBlockElements.get(numberOfBlocks - 1);
			lastBlock.findElement(By.xpath(".//i[@class='add icon']")).click();

			numberOfBlocks = socialChannelBlockElements.size(); // it should have increased by 1
			WebElement newBlock = socialChannelBlockElements.get(numberOfBlocks - 1);
			newBlock.findElement(By.xpath(".//div[@class='text']")).click();
			newBlock.findElement(By.xpath(".//span[contains(text(),'Facebook')]")).click();
			newBlock.findElement(By.xpath(".//input")).sendKeys(faceBookProfileLink);
		}

	}

	public void setLinkedInProfileLink(String linkedInProfileLink) {
		// check if the LinkedIn element is already present.
		// If yes, overwrite the value.
		// If no, add it and populate the value.
		boolean linkedInPresent = false;
		int size;

		for (WebElement socialChannelBlockElement : socialChannelBlockElements) {
			size = socialChannelBlockElement.findElements(By.xpath(".//div[contains(text(),'LinkedIn')]")).size();

			if (size > 0) {
				linkedInPresent = true;
			}

			if (linkedInPresent) {
				socialChannelBlockElement.findElement(By.xpath(".//input")).clear();
				socialChannelBlockElement.findElement(By.xpath(".//input")).sendKeys(linkedInProfileLink);
				break;
			}
		}
		if (!linkedInPresent) {
			int numberOfBlocks = socialChannelBlockElements.size();
			WebElement lastBlock = socialChannelBlockElements.get(numberOfBlocks - 1);
			lastBlock.findElement(By.xpath(".//i[@class='add icon']")).click();

			numberOfBlocks = socialChannelBlockElements.size(); // it should have increased by 1
			WebElement newBlock = socialChannelBlockElements.get(numberOfBlocks - 1);
			newBlock.findElement(By.xpath(".//div[@class='text']")).click();
			newBlock.findElement(By.xpath(".//span[contains(text(),'LinkedIn')]")).click();
			newBlock.findElement(By.xpath(".//input")).sendKeys(linkedInProfileLink);
		}
	}

	public void setIndustry(String industry) {
		industryElement.clear();
		industryElement.sendKeys(industry);
	}

	public void setNumberOfEmployees(String numberOfEmployees) {
		numberOfEmployeesElement.clear();
		numberOfEmployeesElement.sendKeys(numberOfEmployees);
	}

	public void setStockSymbol(String stockSymbol) {
		stockSymbolElement.clear();
		stockSymbolElement.sendKeys(stockSymbol);
	}

	public void setAnnualRevenue(String annualRevenue) {
		annualRevenueElement.clear();
		annualRevenueElement.sendKeys(annualRevenue);
	}

	public void setPriority(String priority) {
		priorityElement.click();
		String priorityXpath = ".//span[contains(text(),'" + priority + "')]";
		priorityElement.findElement(By.xpath(priorityXpath)).click();
	}

	public void setStatus(String status) {
		statusElement.click();
		String statusXpath = ".//span[contains(text(),'" + status + "')]";
		statusElement.findElement(By.xpath(statusXpath)).click();
	}

	public void setSource(String source) {
		sourceElement.click();
		String sourceXpath = ".//span[contains(text(),'" + source + "')]";
		sourceElement.findElement(By.xpath(sourceXpath)).click();
	}

	public void setCategory(String category) {
		categoryElement.click();
		String categoryXpath = ".//span[contains(text(), '" + category + "')]";
		categoryElement.findElement(By.xpath(categoryXpath)).click();
	}

	public void setVatNumber(String vatNumber) {
		vatNumberElement.clear();
		vatNumberElement.sendKeys(vatNumber);
	}

	public void setIdentifier(String identifier) {
		identifierElement.clear();
		identifierElement.sendKeys(identifier);
	}

	public void setImageFileUpload(String imageFileForUpload) {
		imageElement.click();
		String browserName = BrowserUtil.getBrowserName(getDriver());
		String exeAbsolutePath = ExcelUtil.getAbsolutePath("exe/" + browserName + "/" + imageFileForUpload);
		try {
			Runtime.getRuntime().exec(exeAbsolutePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
