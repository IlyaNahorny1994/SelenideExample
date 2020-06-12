package by.rw.page;

import static by.rw.framework.selenide.CustomCondition.valueInAttribute;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import by.rw.model.ScheduleInfoModel;
import io.qameta.allure.Step;

public class PassengerServicesPage extends BasePage
{

	Function<String, By> filterRadioButton = text -> By.xpath(
			String.format(properties.getProperty("passengerServices.radioButtonXpath"), text));
	private By electronicRegistrationCheckBox = By.xpath(properties.getProperty("passengerServices.electronicRegistrationCheckBoxXpath"));
	private By electronicRegistrationParentCheckBox = By.xpath(
			properties.getProperty("passengerServices.electronicRegistrationCheckBoxParentXpath"));
	private By scheduleItems = By.xpath(properties.getProperty("passengerServices.scheduleItemsXpath"));
	private By scheduleItemButton = By.xpath(properties.getProperty("passengerServices.scheduleItemButtonXpath"));
	private By scheduleItemTrainName = By.className(properties.getProperty("passengerServices.scheduleItemTrainNameClsNm"));
	private By scheduleItemDepartureTime = By.xpath(properties.getProperty("passengerServices.scheduleItemDepartureTimeXpath"));
	private By scheduleItemInWayTime = By.xpath(properties.getProperty("passengerServices.scheduleItemInWayTimeXpath"));
	private By dateFilterItems = By.xpath(properties.getProperty("passengerServices.dateFilterItemsXpath"));

	@Step("RadioButton '{rbName}' should be checked")
	public PassengerServicesPage shouldBeCheckedRadioButton(String rbName)
	{
		$(filterRadioButton.apply(rbName)).shouldHave(valueInAttribute("class", "active"));
		return this;
	}

	@Step("Check Electronic Registration checkbox")
	public PassengerServicesPage checkElectronicRegistrationCheckBox()
	{
		$(electronicRegistrationCheckBox).click();
		return this;
	}

	@Step("Electronic Registration checkbox should be checked")
	public PassengerServicesPage electronicRegistrationCheckBoxShouldBeChecked()
	{
		$(electronicRegistrationParentCheckBox).shouldHave(valueInAttribute("class", "checked"));
		return this;
	}

	@Step("All schedule items contains '{buttonName}' button")
	public PassengerServicesPage allScheduleItemsContainsButton(String buttonName)
	{
		$$(scheduleItems).forEach(item -> item.$(scheduleItemButton)
				.shouldHave(text(buttonName))
				.shouldHave(attribute("href", "javascript:void(0);")));
		return this;
	}

	public List<ScheduleInfoModel> getAllScheduleItems()
	{
		List<ScheduleInfoModel> items = $$(scheduleItems)
				.stream()
				.map(item -> {
					SelenideElement trainNameElem = item.$(scheduleItemTrainName);
					SelenideElement departureTimeElem = item.$(scheduleItemDepartureTime);
					SelenideElement inWayTimeElem = item.$(scheduleItemInWayTime);
					return new ScheduleInfoModel(trainNameElem.text(), departureTimeElem.text(), inWayTimeElem.text());
				})
				.collect(Collectors.toList());
		return items;
	}

	@Step("Count of date filters should have size = {expSize}")
	public PassengerServicesPage countOfDateFiltersShouldHaveSize(int expSize)
	{
		$$(dateFilterItems).shouldHave(size(expSize));
		return this;
	}
}