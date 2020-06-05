package by.rw.listener;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import by.rw.util.CustomSimpleReport;

public class CustomReportText implements IInvokedMethodListener
{
	protected CustomSimpleReport report = new CustomSimpleReport();

	public CustomReportText() {
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		this.report.start();
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		this.report.finish(testResult.getName());
	}
}