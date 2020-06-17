package by.rw.listener;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import by.rw.logger.Log;

public class CustomActionsListener extends AbstractWebDriverEventListener
{
	private final Log log = new Log(CustomActionsListener.class.getName());

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver)
	{
		log.action(String.format("Clicking by element '%s'", getLocatorInfo(element)));
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver)
	{
		log.action(String.format("Getting text for element '%s'", getLocatorInfo(element)));
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text)
	{
		log.action(String.format("Text is '%s'", text));
	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend)
	{
		if (keysToSend != null)
		{
			log.action(String.format("Typing '%s' value into the element '%s'", Arrays.toString(keysToSend), getLocatorInfo(element)));
		}
	}

	@Override
	public void beforeNavigateTo(String url, WebDriver driver)
	{
		log.info(String.format("Navigating to url: '%s'", url));
	}

	private String getLocatorInfo(WebElement element)
	{
		return element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "");
	}
}
