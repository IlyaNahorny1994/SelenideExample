package by.rw.util;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.qameta.allure.Attachment;

public class AllureAttachment
{
	@Attachment
	public static byte[] takeScreenshot()
	{
		return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment
	public static String attachText(String text)
	{
		return text;
	}
}