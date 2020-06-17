package by.rw.util;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.Assert;

import com.browserup.bup.BrowserUpProxy;

import by.rw.page.BasePage;
import io.qameta.allure.Step;

public class TrafficManager
{
	private BrowserUpProxy proxy;

	public TrafficManager(BrowserUpProxy proxy)
	{
		this.proxy = proxy;
	}

	@Step("Count of requests with '{partOfUrl}' template should be more than '{moreThan}'")
	public <T extends BasePage> T countOfRequestsWithTemplateShouldBeMoreThan(String partOfUrl, int moreThan, Class<T> cls)
	{
		List<String> urlsWithPng = proxy.getHar().getLog().getEntries()
				.stream()
				.filter(item -> item.getRequest().getUrl().contains(partOfUrl))
				.map(item -> item.getRequest().getUrl())
				.collect(Collectors.toList());

		Assert.assertTrue(urlsWithPng.size() > moreThan,
				String.format("Count of url with '%s' template not more than %d. Actual count = %d", partOfUrl, moreThan, urlsWithPng.size()));
		return getCurrentPage(cls);
	}

	@Step("Set har value = '{har}'")
	public <T extends BasePage> T setHar(String har, Class<T> cls)
	{
		proxy.newHar(har);
		return getCurrentPage(cls);
	}

	private  <T extends BasePage> T getCurrentPage(Class<T> cls)
	{
		T result = null;
		try
		{
			result = cls.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
