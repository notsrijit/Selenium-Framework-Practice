package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

//Multi level inheritance
public class Listeners extends Base implements ITestListener {
	
	WebDriver driver;
	
	//Since getExtentReport was static, no need to create object before calling
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	
	/*ExtentReport is not thread safe if tests are run in parallel, as the same extent report object is being passed to every driver
	object, one may pass, one may fail.*/
	//Making it (ExtentTest) thread safe
	
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		
		//Starting the report
		//createTest() returns an ExtentTest object. We are capturing it
		extentTest = extentReport.createTest(result.getName()+" execution started");
		
		//Making it (ExtentTest) thread safe
		extentTestThread.set(extentTest);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		//Logging the report
//		extentTest.log(Status.PASS, result.getName()+" got passed");
		extentTestThread.get().log(Status.PASS, result.getName()+" got passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		//Logging failure - logs the exact reason of failure
//		extentTest.fail(result.getThrowable());
		extentTestThread.get().fail(result.getThrowable());
		
		//result always has the name of the test being executed
		String testMethodName = result.getName();
		
		//the instance of the driver object from the current class being executed is available in result
		//result will be typecasted to WebDriver to get driver object
		//getting the exact instance of occurence to take the Screenshot		
		try {
			
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//sending the method name and driver as parameters to base class
		try {
			
			String screenshotPath = takeScreenshot(testMethodName, driver);
			//Putting the screenshot in Extent Report
			extentTestThread.get().addScreenCaptureFromPath(screenshotPath, testMethodName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();

	}
	
	

}
