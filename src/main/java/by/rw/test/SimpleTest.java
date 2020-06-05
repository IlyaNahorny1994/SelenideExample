package by.rw.test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

import java.util.List;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.rw.test.base.BaseTest;
import by.rw.model.ScheduleInfoModel;
import by.rw.page.PassengerServicesPage;

public class SimpleTest extends BaseTest
{
	@BeforeMethod
	public void beforeTestMethod()
	{
		onHomePage()
				.fillInFromField("Минск-Пассажирский")
				.fillInToField("Молодечно")
				.fillInWhereField("07.06.2020")
				.submit();
	}

	@Test
	public void verifyOneWayRadioButtonIsCheckedByDefault()
	{
		page(PassengerServicesPage.class)
				.shouldBeCheckedRadioButton("В одну сторону");
	}

	@Test
	public void verifyFilterElectronicRegistrationWorksProperly()
	{
		page(PassengerServicesPage.class)
				.checkElectronicRegistrationCheckBox()
				.electronicRegistrationCheckBoxShouldBeChecked()
				.allScheduleItemsContainsButton("Выбрать места");
	}

	@Test
	public void verifyCountOfDateFilters()
	{
		$$(By.xpath("//li[contains(@class, 'sch-links__item')]")).shouldHave(size(5));
	}

	@Test
	public void collectTrainsInformation()
	{
		List<ScheduleInfoModel> items =
				page(PassengerServicesPage.class)
						.getAllScheduleItems();
	}
}