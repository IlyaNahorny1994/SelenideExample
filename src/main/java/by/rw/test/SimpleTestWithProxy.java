package by.rw.test;

import org.testng.annotations.Test;

import by.rw.page.HomePage;
import by.rw.page.PassengerServicesPage;
import by.rw.test.base.BaseProxyTest;

public class SimpleTestWithProxy extends BaseProxyTest
{
	private static final String harTemplate = "rw.by/";

	@Test
	void verifyCountOfPngImg()
	{
		onHomePage()
				.fillInFromField("Минск-Пассажирский")
				.fillInToField("Молодечно")
				.fillInWhereField("07.06.2020")
				.getTrafficManager(getSelenideProxyServer().getProxy()).setHar(harTemplate, HomePage.class)
				.submit()
				.getTrafficManager(getSelenideProxyServer().getProxy())
				.countOfRequestsWithTemplateShouldBeMoreThan(".png", 5, PassengerServicesPage.class);
	}
}