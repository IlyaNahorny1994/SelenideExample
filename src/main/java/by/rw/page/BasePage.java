package by.rw.page;

import java.util.Properties;

import com.browserup.bup.BrowserUpProxy;

import by.rw.util.TrafficManager;
import by.rw.util.PropertyReader;

public class BasePage
{
	protected Properties properties = PropertyReader.readPropertiesFile("locator.properties");

	public TrafficManager getTrafficManager(BrowserUpProxy proxy)
	{
		return new TrafficManager(proxy);
	}
}
