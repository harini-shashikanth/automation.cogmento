/**
 * 
 */
package com.cogmento.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * @author Harini
 *
 */
public class BrowserUtil {
	/**
	 * 
	 */
	private static String userHomeDir;
	private static String chromeRelativePath;
	private static String firefoxRelativePath;
	private static String ieRelativePath;
	private static String edgeRelativePath;
	private static long pageTimeOutSec;

	private static long implicitWaitMilliSec;

	private static boolean enableScreenshot;
	private static String screenshotRelativeDirectory;
	static {
		userHomeDir = FileUtils.getUserDirectory().getAbsolutePath();
		firefoxRelativePath = Config.getProperty("firefoxRelativePath");
		chromeRelativePath = Config.getProperty("chromeRelativePath");
		ieRelativePath = Config.getProperty("ieRelativePath");
		edgeRelativePath = Config.getProperty("edgeRelativePath");
		System.setProperty("webdriver.gecko.driver", userHomeDir + firefoxRelativePath);
		System.setProperty("webdriver.chrome.driver", userHomeDir + chromeRelativePath);
		System.setProperty("webdriver.ie.driver", userHomeDir + ieRelativePath);
		System.setProperty("webdriver.edge.driver", userHomeDir + edgeRelativePath);
		pageTimeOutSec = Long.parseLong(Config.getProperty("pageTimeOutSec"));
		implicitWaitMilliSec = Long.parseLong(Config.getProperty("implicitWaitMilliSec"));
		if (Config.getProperty("enableScreenshot").toLowerCase().equals("true")) {
			enableScreenshot = true;
		}
		screenshotRelativeDirectory = Config.getProperty("screenshotRelativeDirectory");
	}

	public static WebDriver getWebDriver(String browserName) {
		WebDriver driver = null;
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "ie":
			driver = getIeDriver();
			break;
		case "edge":
			driver = getEdgeDriver();
			break;
		default:
			driver = new ChromeDriver();
		}
		initializeBrowserConfig(driver);
		driver = convertToEventFiringWebDriver(driver);
		return driver;
	}

	private static void initializeBrowserConfig(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(pageTimeOutSec, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(implicitWaitMilliSec, TimeUnit.MILLISECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}

	private static WebDriver convertToEventFiringWebDriver(WebDriver driver) {
		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		eventFiringWebDriver.register(new WebDriverEventListener() {
			@Override
			public void onException(Throwable throwable, WebDriver eventFiringWebDriver) {
			}

			@Override
			public void beforeSwitchToWindow(String windowName, WebDriver eventFiringWebDriver) {
			}

			@Override
			public void beforeScript(String script, WebDriver eventFiringWebDriver) {
			}

			@Override
			public void beforeNavigateTo(String url, WebDriver eventFiringWebDriver) {
			}

			@Override
			public void beforeNavigateRefresh(WebDriver eventFiringWebDriver) {
			}

			@Override
			public void beforeNavigateForward(WebDriver eventFiringWebDriver) {
			}

			@Override
			public void beforeNavigateBack(WebDriver eventFiringWebDriver) {
			}

			@Override
			public void beforeGetText(WebElement element, WebDriver eventFiringWebDriver) {
			}

			@Override
			public <X> void beforeGetScreenshotAs(OutputType<X> target) {
			}

			@Override
			public void beforeFindBy(By by, WebElement element, WebDriver eventFiringWebDriver) {
			}

			@Override
			public void beforeClickOn(WebElement element, WebDriver eventFiringWebDriver) {
				// TODO
				// BrowserUtil.takeScreenshot(eventFiringWebDriver,
				// eventFiringWebDriver.getCurrentUrl());
			}

			@Override
			public void beforeChangeValueOf(WebElement element, WebDriver eventFiringWebDriver, CharSequence[] keysToSend) {
			}

			@Override
			public void beforeAlertDismiss(WebDriver eventFiringWebDriver) {
			}

			@Override
			public void beforeAlertAccept(WebDriver eventFiringWebDriver) {
			}

			@Override
			public void afterSwitchToWindow(String windowName, WebDriver eventFiringWebDriver) {
			}

			@Override
			public void afterScript(String script, WebDriver eventFiringWebDriver) {
			}

			@Override
			public void afterNavigateTo(String url, WebDriver eventFiringWebDriver) {
				BrowserUtil.takeScreenshot(eventFiringWebDriver, driver.getCurrentUrl());
			}

			@Override
			public void afterNavigateRefresh(WebDriver eventFiringWebDriver) {
			}

			@Override
			public void afterNavigateForward(WebDriver eventFiringWebDriver) {
			}

			@Override
			public void afterNavigateBack(WebDriver eventFiringWebDriver) {
			}

			@Override
			public void afterGetText(WebElement element, WebDriver eventFiringWebDriver, String text) {
			}

			@Override
			public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
			}

			@Override
			public void afterFindBy(By by, WebElement element, WebDriver eventFiringWebDriver) {
			}

			@Override
			public void afterClickOn(WebElement element, WebDriver eventFiringWebDriver) {
				BrowserUtil.sleep(1000);
				BrowserUtil.takeScreenshot(eventFiringWebDriver, eventFiringWebDriver.getCurrentUrl());
			}

			@Override
			public void afterChangeValueOf(WebElement element, WebDriver eventFiringWebDriver, CharSequence[] keysToSend) {
				BrowserUtil.takeScreenshot(eventFiringWebDriver, eventFiringWebDriver.getCurrentUrl());
			}

			@Override
			public void afterAlertDismiss(WebDriver eventFiringWebDriver) {
			}

			@Override
			public void afterAlertAccept(WebDriver eventFiringWebDriver) {
			}
		});
		return eventFiringWebDriver;
	}

	public static void sleep(long sleepMilliSec) {
		try {
			Thread.sleep(sleepMilliSec);
		} catch (InterruptedException e) {
		}
	}

	private static WebDriver getIeDriver() {
		/*
		 * IE needs additional settings to work properly:
		 * 
		 * 1. add the capability to ignore zoom settings - through code
		 * 
		 * 2. Cookies are not deleted when driver.manage().deleteAllCookies() is
		 * invoked. Workaround is adopted below to add the capability to ensure clean
		 * session
		 * 
		 * 3. MANUAL: set the add the domain freecrm.com to the list of trusted sites in
		 * IE. Open IE and manually to the list of trusted sites under IE -> Internet
		 * Options -> Security -> Trusted Sites --> Sites
		 * 
		 * 4. Also use the 32 bit version of IE even if running on 64bit OS/machine. The
		 * sendkeys() method is super slow with the 64 bit driver. It are normal in 32
		 * bit version of IE
		 */
		InternetExplorerOptions ieOptions = new InternetExplorerOptions();
		ieOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		ieOptions.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		WebDriver driver = new InternetExplorerDriver(ieOptions);
		return driver;
	}

	private static WebDriver getEdgeDriver() {
		/*
		 * Edge needs additional settings to work properly. Cookies are not deleted when
		 * driver.manage().deleteAllCookies() is invoked. So the workaround below is
		 * adopted to open browser in private mode.
		 */
		EdgeOptions edgeOptions = new EdgeOptions();
		edgeOptions.setCapability("InPrivate", true);
		WebDriver driver = new EdgeDriver(edgeOptions);
		return driver;
	}

	public static void takeScreenshot(WebDriver driver, String url) {
		if (enableScreenshot) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			String screenShotFileName = userHomeDir + screenshotRelativeDirectory + generateFileNameFromUrl(url) + "_" + simpleDateFormat.format(new Date()) + ".png";
			File screenshotFile = new File(screenShotFileName);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				// although it is copy function, the source file is removed by the OS since it
				// is
				// in Temp directory
				FileUtils.copyFile(scrFile, screenshotFile);
			} catch (IOException e) {
				System.out.println("Unable to take screenshot for " + url);
				e.printStackTrace();
			}
		}
	}

	private static String generateFileNameFromUrl(String url) {
		String uniqueName = url.replace("http://", "").replace("https://", "").replace("/", ".").replace(":", ".");
		if (uniqueName.endsWith(".")) {
			uniqueName = uniqueName.substring(0, uniqueName.length() - 1);
		}
		return uniqueName;
	}

	public static String getBrowserName(WebDriver driver) {
		Capabilities cap = ((EventFiringWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		return browserName;
	}
}
