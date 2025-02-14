package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	
	//Webdriver declared public so that listener from other package can access this
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver browserCall () throws IOException {
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream (System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		
		String browser = prop.getProperty("browser");
		
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		
		else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		
		else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		
		return driver;
		
	}
	
	//Creating reusable method to take screenshots through listeners (with test name of the concerned test, and exact driver location where its failing)
	//Typecasting driver object to Take screenshot at that point using predefined method
	//Providing Output type as File to create the image file. Storing it in a source file
	//Moving this file to destination using FileUtils. Creating new file at destination
	
	public String takeScreenshot (String testName, WebDriver driver) throws IOException {
		
		File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		FileUtils.copyFile(sourceFile, new File(destinationFile));
		
		return destinationFile;
		
	}
	

}
