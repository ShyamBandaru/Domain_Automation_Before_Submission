package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.util.Date;

public class ExtentReporter {
    public static ExtentReports reporterobject(){
        Date date = new Date();
        String timestamp = date.toString().replace(" ","_").replace(":","_");

        String path = "./src/Reports/ExtentReports/LinksValidation_"+timestamp +".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("URL links validation Results");
        reporter.config().setDocumentTitle("URL links validation Report");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        return extent;
    }
}
