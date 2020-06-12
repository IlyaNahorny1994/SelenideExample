package by.rw.framework.logger;

import static com.codeborne.selenide.Selenide.screenshot;

import java.io.File;
import java.nio.file.Path;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.BaseEncoding;

import by.rw.util.RandomUtil;

public class Log
{
	private final Logger logger;
	private static final String SCREENSHOT_MESSAGE = "Screenshot";
	private static final String LOG_MESSAGE_TEMPLATE = "%s; Screenshot is saved in: %s";

	public Log(String className)
	{
		logger = LoggerFactory.getLogger(className);
	}

	public void info(String msg)
	{
		logger.info(msg);
	}

	public void error(String msg)
	{
		logger.error(msg);
	}

	public void info(String msg, Object... args)
	{
		logger.info(msg, args);
	}

	public void log(byte[] bytes, String message)
	{
		logger.info("RP_MESSAGE#BASE64#{}#{}", BaseEncoding.base64().encode(bytes), message);
	}

	public void log(File file, String message)
	{
		logger.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
	}

	//	public void takeScreenshot(LogLevel level, String message, Object... args)
	//	{
	//		String screenshotPath = screenshot(getRandomScreenshotName());
	//		File file = new File(screenshotPath);
	//		log(file, message);
	//	}

	public void takeScreenshot(LogLevel level, String message, Object... args)
	{
		String screenshotPath = screenshot(getRandomScreenshotName());
		sendScreenShotToRP(level, message, screenshotPath, args);
	}

	private void sendScreenShotToRP(LogLevel level, String message, String screenshotPath, Object... args)
	{
		String logMessage = screenshotMessageFormat(message, screenshotPath);
		String rpTemplate = "RP_MESSAGE#FILE#%s#%s";
		switch (level)
		{
			case DEBUG:
				logger.debug(logMessage, args);
				logger.debug(String.format(rpTemplate, screenshotPath, SCREENSHOT_MESSAGE));
				break;
			case INFO:
				logger.info(logMessage, args);
				logger.info(String.format(rpTemplate, screenshotPath, SCREENSHOT_MESSAGE));
				break;
			case ERROR:
				logger.error(logMessage, args);
				logger.error(String.format(rpTemplate, screenshotPath, SCREENSHOT_MESSAGE));
				break;
			default:
				break;
		}
	}

	private String screenshotMessageFormat(String message, String screenshotPath)
	{
		return String.format(LOG_MESSAGE_TEMPLATE, message,
				screenshotPath);
	}

	private String getRandomScreenshotName()
	{
		return String.format("%s_%s", Calendar.getInstance().getTimeInMillis(), RandomUtil.getRandomStr(5));
	}
}
