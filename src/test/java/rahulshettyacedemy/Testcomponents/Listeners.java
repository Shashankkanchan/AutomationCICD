package rahulshettyacedemy.Testcomponents;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacedemy.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener{
	WebDriver driver;
	ExtentTest test;
	ExtentReports extent =ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thrad id will be created
	  }

	@Override
	  public  void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "test passed");
	  }
	@Override
	 public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
	     try {
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver")
					 .get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     //screenshot //attach to results
	     String filePath=null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }

	@Override
	 public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }

	@Override
	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	@Override
	  public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }
	@Override
	  public void onStart(ITestContext context) {
	    // not implemented
	  }

	@Override
	  public void onFinish(ITestContext context) {
	    extent.flush();
	  }
}
