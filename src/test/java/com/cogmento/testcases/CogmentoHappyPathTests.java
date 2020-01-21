/**
 * 
 */
package com.cogmento.testcases;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.cogmento.pageObjects.publicAccessPages.HomePage;
import com.cogmento.pageObjects.publicAccessPages.LoginPage;
import com.cogmento.pageObjects.userAccessPages.UserHomePage;
import com.cogmento.pageObjects.userAccessPages.modules.companies.CompanyAddOrEditPage;
import com.cogmento.pageObjects.userAccessPages.modules.companies.CompanyListPage;
import com.cogmento.pageObjects.userAccessPages.modules.companies.CompanyViewPage;
import com.util.BrowserUtil;
import com.util.ConfigUtil;
import com.util.ExcelUtil;

/**
 * @author Harini
 *
 */
public class CogmentoHappyPathTests {
	WebDriver driver;

	@DataProvider
	public Object[][] newCompanyDataProvider() {
		return ExcelUtil.getTestDataMaps("Companies");
	}

	@BeforeClass
	@Parameters({ "browser", "homePageUrl" })
	public void setup(String browser, String homePageUrl) {
		System.out.println(homePageUrl);
		driver = BrowserUtil.getWebDriver(browser);
		driver.get(homePageUrl);
		HomePage homePage = new HomePage(driver);
		homePage.getLoginBtn().click();

		LoginPage loginPage = new LoginPage(driver);
		String username = ConfigUtil.getProperty("default.username");
		String password = ConfigUtil.getProperty("default.password");
		loginPage.login(username, password);
	}

	@Test(dataProvider = "newCompanyDataProvider")
	public void addCompanyTest(Map<String, String> newCompany) {
		UserHomePage userHomePage = new UserHomePage(driver);
		userHomePage.getCompaniesLnk().click();

		CompanyListPage companyListPage = new CompanyListPage(driver);
		companyListPage.getNewCompanyBtn().click();

		CompanyAddOrEditPage companyAddOrEditPage = new CompanyAddOrEditPage(driver);
		companyAddOrEditPage.addCompany(newCompany);

		CompanyViewPage companyViewPage = new CompanyViewPage(driver);
		assertEquals(companyViewPage.getName(), newCompany.get("Name"));
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		BrowserUtil.sleep(5000);
		driver.close();
	}

}
