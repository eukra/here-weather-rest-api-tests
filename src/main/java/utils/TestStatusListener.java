package utils;

import org.joda.time.DateTime;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by Kravchenko on 7/24/2017.
 */
public class TestStatusListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test " + iTestResult.getMethod().getMethodName() + " is finished with FAILURE!!!");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("\nTest " + iTestResult.getMethod().getMethodName() + " is started.");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test " + iTestResult.getMethod().getMethodName() + " is finished");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test " + iTestResult.getMethod().getMethodName() + " is SKIPPED!!!");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("\nTests running on " + new DateTime(iTestContext.getStartDate().getTime()).minusHours(3).toString("yyyy-MM-dd HH:mm"));
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Test suite has been finished");
    }
}
