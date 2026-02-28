package arijitsinha.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	public static ExtentReports getExtentObject() {
		String path=System.getProperty("user.dir")+"\\reports\\report.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("AutomateQA Test Report");
		reporter.config().setDocumentTitle("Test Report");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Arijit");
		return extent;
	}
}
