package by.rw.test.base;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;

import by.rw.listener.CustomActionsListener;
import by.rw.listener.ScreenShooter;
import by.rw.page.HomePage;
import by.rw.util.Locale;
import by.rw.util.PropertyReader;

//@Listeners({ ScreenShooter.class, CustomReportText.class})
@Listeners({ ScreenShooter.class})
public class BaseTest
{
	private Properties configProperties = PropertyReader.readPropertiesFile("config.properties");
	private String lang = configProperties.getProperty("language");
	private Properties testDataProperties = PropertyReader.readPropertiesFile(String.format("locale/%s.locale.properties", lang));

	@BeforeSuite
	public void setUp()
	{
		//for Selenoid
//		Configuration.remote = "http://localhost:4444/wd/hub";
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("enableVNC", true);
//		Configuration.browserCapabilities = capabilities;

		Configuration.browser = configProperties.getProperty("browser");
		Configuration.timeout = Long.parseLong(configProperties.getProperty("timeout"));
		Configuration.startMaximized = true;
		Configuration.baseUrl = configProperties.getProperty("url");
		Configuration.reportsFolder = "target/test-result/reports";
		Configuration.savePageSource = false;
		Configuration.screenshots = false;
		WebDriverRunner.addListener(new CustomActionsListener());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass()
	{
		closeWebDriver();
	}

	public HomePage onHomePage()
	{
		Locale locale = Locale.getLocaleByText(lang);
		return open(Configuration.baseUrl, HomePage.class).setLanguage(locale);
	}

	public Properties getTestDataProperties()
	{
		return testDataProperties;
	}
}