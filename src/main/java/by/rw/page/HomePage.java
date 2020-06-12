package by.rw.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.page;

import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;

import by.rw.util.Locale;
import io.qameta.allure.Step;

public class HomePage extends BasePage
{
	private By fromInput = By.id(properties.getProperty("homePage.fromID"));
	private By toInput = By.id(properties.getProperty("homePage.toID"));
	private By dateInput = By.id(properties.getProperty("homePage.dateID"));
	private By activeDateCell = By.xpath(properties.getProperty("homePage.activeDateXpath"));
	private By submitBtn = By.xpath(properties.getProperty("homePage.submitXpath"));

	@Step("Fill in 'From' field with '{value}' value")
	public HomePage fillInFromField(String value)
	{
		$(fromInput).val(value);
		return this;
	}

	@Step("Fill in 'To' field with '{value}' value")
	public HomePage fillInToField(String value)
	{
		$(toInput).val(value);
		return this;
	}

	@Step("Fill in 'Where' field with '{value}' value")
	public HomePage fillInWhereField(String value)
	{
		$(dateInput).val(value);
		$(activeDateCell).click();
//		executeJavaScript("$(arguments[0]).val(arguments[1])", $(dateInput), value);
		return this;
	}

	@Step("Click Submit button")
	public PassengerServicesPage submit()
	{
		$(submitBtn).click();
		return page(PassengerServicesPage.class);
	}

	@Step("Search way with following values: {from} - {to} - {when}")
	public PassengerServicesPage searchWay(String from, String to, String when)
	{
		$(fromInput).val(from);
		$(toInput).val(to);
		$(dateInput).val(when);
		$(activeDateCell).click();
		return submit();
	}

	@Step("Set language to '{locale}'")
	public HomePage setLanguage(Locale locale)
	{
		boolean isExist = $(By.xpath(String.format("//*[@class='lang-select']//a[contains(@href, '%s')]", locale.getLocaleText()))).is(Condition.exist);
		if(isExist)
		{
			$(By.className("langText")).click();
			$(By.xpath(String.format("//*[@class='lang-select']//a[contains(@href, '%s')]", locale.getLocaleText()))).click();
		}
		return this;
	}
}
