package by.rw.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.page;

import org.openqa.selenium.By;

public class HomePage extends BasePage
{
	private By fromInput = By.id(properties.getProperty("homePage.fromID"));
	private By toInput = By.id(properties.getProperty("homePage.toID"));
	private By dateInput = By.id(properties.getProperty("homePage.dateID"));
	private By activeDateCell = By.xpath(properties.getProperty("homePage.activeDateXpath"));
	private By submitBtn = By.xpath(properties.getProperty("homePage.submitXpath"));

	public HomePage fillInFromField(String value)
	{
		$(fromInput).val(value);
		return this;
	}

	public HomePage fillInToField(String value)
	{
		$(toInput).val(value);
		return this;
	}

	public HomePage fillInWhereField(String value)
	{
		$(dateInput).val(value);
		$(activeDateCell).click();
//		executeJavaScript("$(arguments[0]).val(arguments[1])", $(dateInput), value);
		return this;
	}

	public PassengerServicesPage submit()
	{
		$(submitBtn).click();
		return page(PassengerServicesPage.class);
	}
}
