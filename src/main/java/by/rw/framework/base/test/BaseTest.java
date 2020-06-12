package by.rw.framework.base.test;

import static by.rw.framework.selenide.SelenideWrapper.open;
import static com.codeborne.selenide.Selenide.closeWebDriver;

import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.codeborne.selenide.Configuration;

import by.rw.listener.ScreenShooter;
import by.rw.framework.logger.Log;
import by.rw.page.HomePage;
import by.rw.util.Locale;
import by.rw.util.PropertyReader;

//@Listeners({ ScreenShooter.class, CustomReportText.class})
@Listeners({ ScreenShooter.class})
public class BaseTest
{
	private Properties configProperties = PropertyReader.readPropertiesFile("config.properties");
	private String lang = configProperties.getProperty("language");

	public Properties getTestDataProperties()
	{
		return testDataProperties;
	}

	public Properties testDataProperties = PropertyReader.readPropertiesFile(String.format("locale/%s.locale.properties", lang));

	public final Log logger = new Log(BaseTest.class.getName());

	@BeforeSuite
	public void setUp()
	{
		Configuration.browser = configProperties.getProperty("browser");
		Configuration.timeout = Long.parseLong(configProperties.getProperty("timeout"));
		Configuration.startMaximized = true;
		Configuration.baseUrl = configProperties.getProperty("url");
		Configuration.reportsFolder = "target/test-result/reports";
		Configuration.savePageSource = false;
		Configuration.screenshots = false;

//		System.setProperty("chromeoptions.prefs", String.format("intl.accept_languages=%s", lang));
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
}