package by.rw.listener;

import org.testng.ITestResult;
import org.testng.reporters.ExitCodeListener;

import by.rw.util.AllureAttachment;

public class ScreenShooter extends ExitCodeListener
{
	@Override
	public void onTestFailure(ITestResult result)
	{
		super.onTestFailure(result);
		AllureAttachment.takeScreenshot();
	}
}
