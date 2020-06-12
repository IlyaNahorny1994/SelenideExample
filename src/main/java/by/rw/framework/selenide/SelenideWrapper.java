package by.rw.framework.selenide;

import com.codeborne.selenide.Selenide;

import by.rw.framework.logger.Log;

public class SelenideWrapper
{
	private final static Log log = new Log(SelenideWrapper.class.getName());

	public static void open()
	{
//		log.info("Open browser");
		Selenide.open();
	}

	public static <PageObjectClass> PageObjectClass open(String relativeOrAbsoluteUrl, Class<PageObjectClass> pageObjectClassClass) {
		log.info("Navigate to url: {}", relativeOrAbsoluteUrl);
		return Selenide.open(relativeOrAbsoluteUrl, pageObjectClassClass);
	}
}
