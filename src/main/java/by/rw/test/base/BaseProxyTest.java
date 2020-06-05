package by.rw.test.base;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getSelenideProxy;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.proxy.SelenideProxyServer;

import by.rw.page.HomePage;

public class BaseProxyTest extends BaseTest
{
	private SelenideProxyServer selenideProxyServer;

	@Override
	public HomePage onHomePage()
	{
		Configuration.proxyEnabled = true;
		open(Configuration.baseUrl);
		selenideProxyServer = getSelenideProxy();
		return page(HomePage.class);
	}

	public SelenideProxyServer getSelenideProxyServer()
	{
		return selenideProxyServer;
	}
}