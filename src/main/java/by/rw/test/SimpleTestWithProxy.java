package by.rw.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.annotations.Test;

import by.rw.page.HomePage;
import by.rw.page.PassengerServicesPage;
import by.rw.framework.base.test.BaseProxyTest;

public class SimpleTestWithProxy extends BaseProxyTest
{
	private static final String harTemplate = "rw.by/";
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@Test
	void verifyCountOfPngImg()
	{
		onHomePage()
				.fillInFromField(getTestDataProperties().getProperty("text.minskPasazyrski"))
				.fillInToField(getTestDataProperties().getProperty("text.maladziecna"))
				.fillInWhereField(dtf.format(LocalDateTime.now().plusDays(5)))
				.getTrafficManager(getSelenideProxyServer().getProxy()).setHar(harTemplate, HomePage.class)
				.submit()
				.getTrafficManager(getSelenideProxyServer().getProxy())
				.countOfRequestsWithTemplateShouldBeMoreThan(".png", 5, PassengerServicesPage.class);
	}
}