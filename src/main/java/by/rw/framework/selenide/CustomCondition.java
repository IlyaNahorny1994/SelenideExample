package by.rw.framework.selenide;

import org.openqa.selenium.WebElement;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Driver;

public class CustomCondition
{
	public static Condition valueInAttribute(final String attributeName, final String value) {
		return new Condition(String.format("value '%s' in attribute '%s'", value, attributeName)) {
			@Override
			public boolean apply(Driver driver, WebElement element) {
				return element.getAttribute(attributeName).contains(value);
			}
		};
	}
}
