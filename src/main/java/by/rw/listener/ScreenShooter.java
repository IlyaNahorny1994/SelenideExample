package by.rw.listener;

import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;

import by.rw.logger.Log;
import by.rw.util.AllureAttachment;

public class ScreenShooter extends ExitCodeListener
{
	private final Log log = new Log(ScreenShooter.class.getName());

	@Override
	public void onTestFailure(ITestResult result)
	{
		super.onTestFailure(result);
//		log.takeScreenshot(LogLevel.ERROR, "Error on step execution");
//		log.log(((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES), "Msg!!!!!!!!!!!!");
//		log.info("-----------onTestFailure----------------!!!!!!!!!!!!!!!!!!!!!!!!!");
//		log.takeScreenshot(LogLevel.ERROR, "Msg!!!!!!!!!!!!!!!!");
		AllureAttachment.takeScreenshot();
	}
}
