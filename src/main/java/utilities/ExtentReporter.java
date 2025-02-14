package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	//Declared globally so that we can use the same extent report object
	public static ExtentReports extentReport;
	
	//set to static as we are calling this from Listeners class. Since static, no need to create object, we can directly call class
	public static ExtentReports getExtentReport() {
		
		String path = System.getProperty("user.dir")+"\\reports\\extentreport.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Framework report");
		reporter.config().setReportName("Test Results");
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(reporter);
		extentReport.setSystemInfo("Operating System", "Windows 11");
		extentReport.setSystemInfo("Java version", "21.0.1");
		extentReport.setSystemInfo("Tested By", "Srijit Sutradhar");
		
		//returning extent report object
		return extentReport;
		
	}
	
	/*ExtentReport is not thread safe if tests are run in parallel, as the same extent report object is being passed to every driver
	object, one may pass, one may fail.*/

}
