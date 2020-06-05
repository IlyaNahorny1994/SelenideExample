package by.rw.test.base;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.codeborne.selenide.Configuration;

import by.rw.listener.CustomReportText;
import by.rw.listener.ScreenShooter;
import by.rw.page.HomePage;
import by.rw.util.PropertyReader;

@Listeners({ ScreenShooter.class, CustomReportText.class})
public class BaseTest
{
	private Properties properties = PropertyReader.readPropertiesFile("config.properties");

	@BeforeSuite
	public void setUp()
	{
		Configuration.browser = properties.getProperty("browser");
		Configuration.timeout = Long.parseLong(properties.getProperty("timeout"));
		Configuration.startMaximized = true;
		Configuration.baseUrl = properties.getProperty("url");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass()
	{
		closeWebDriver();
	}

	public HomePage onHomePage()
	{
		return open(Configuration.baseUrl, HomePage.class);
	}
}