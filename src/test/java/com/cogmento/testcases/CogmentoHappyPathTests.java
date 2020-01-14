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

import com.cogmento.common.BrowserUtil;
import com.cogmento.common.Config;
import com.cogmento.common.ExcelUtil;
import com.cogmento.pages.publicAccessPages.HomePage;
import com.cogmento.pages.publicAccessPages.LoginPage;
import com.cogmento.pages.userAccessPages.UserHomePage;
import com.cogmento.pages.userAccessPages.modules.companies.CompanyAddOrEditPage;
import com.cogmento.pages.userAccessPages.modules.companies.CompanyListPage;
import com.cogmento.pages.userAccessPages.modules.companies.CompanyViewPage;

/**
 * @author Harini
 *
 */
public class CogmentoHappyPathTests {
	WebDriver driver;

	@BeforeClass
	@Parameters({"browser"})
	public void setup(String browser) {
		driver = BrowserUtil.getWebDriver(browser);
		driver.get(Config.getProperty("homePageUrl"));
		HomePage homePage = new HomePage(driver);
		homePage.getLoginBtn().click();
		LoginPage loginPage = new LoginPage(driver);
		String username = Config.getProperty("default.username");
		String password = Config.getProperty("default.password");
		loginPage.login(username, password);
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		BrowserUtil.sleep(50000);
		driver.close();
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

	@DataProvider
	public Object[][] newCompanyDataProvider() {
		return ExcelUtil.getTestDataMaps("Companies");
	}
}
