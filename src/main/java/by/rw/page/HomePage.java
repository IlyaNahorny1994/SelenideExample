package by.rw.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

import java.util.function.Function;

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
	private By langLabel = By.className(properties.getProperty("homePage.langTextClassName"));
	Function<Locale, By> languageItem = lang -> By.xpath(
			String.format(properties.getProperty("homePage.langItemXpath"), lang.getLocaleText()));

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
		fillInFromField(from);
		fillInToField(to);
		fillInWhereField(when);
		return submit();
	}

	@Step("Set language to '{locale}'")
	public HomePage setLanguage(Locale locale)
	{
		boolean isExist = $(languageItem.apply(locale)).is(Condition.exist);
		if(isExist)
		{
			$(langLabel).click();
			$(languageItem.apply(locale)).click();
		}
		return this;
	}
}
