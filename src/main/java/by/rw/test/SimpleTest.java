package by.rw.test;

import static com.codeborne.selenide.Selenide.page;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.rw.framework.base.test.BaseTest;
import by.rw.model.ScheduleInfoModel;
import by.rw.page.PassengerServicesPage;

public class SimpleTest extends BaseTest
{
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@BeforeMethod
	public void beforeTestMethod()
	{
		onHomePage()
				.searchWay(getTestDataProperties().getProperty("text.minskPasazyrski"),
						getTestDataProperties().getProperty("text.maladziecna"),
						dtf.format(LocalDateTime.now().plusDays(5)));
	}

	@Test
	public void verifyOneWayRadioButtonIsCheckedByDefault()
	{
		page(PassengerServicesPage.class)
				.shouldBeCheckedRadioButton(getTestDataProperties().getProperty("radiobutton.oneWay"));
	}

	@Test
	public void verifyFilterElectronicRegistrationWorksProperly()
	{
		page(PassengerServicesPage.class)
				.checkElectronicRegistrationCheckBox()
				.electronicRegistrationCheckBoxShouldBeChecked()
				.allScheduleItemsContainsButton(getTestDataProperties().getProperty("button.chooseSeats"));
	}

	@Test
	public void verifyCountOfDateFilters()
	{
		page(PassengerServicesPage.class)
				.countOfDateFiltersShouldHaveSize(5);
	}

	@Test
	public void collectTrainsInformation()
	{
		List<ScheduleInfoModel> items =
				page(PassengerServicesPage.class)
						.getAllScheduleItems();
	}
}