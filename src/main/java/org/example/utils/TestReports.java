/**
 * @author Jose Francisco Echavarria
 */
package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.utils.logger.LogController;


public class TestReports {
    private final LogController LOGGER = new LogController(TestReports.class);
    private static TestReports instance = null;
    private ExtentReports extent;
    private ExtentTest extentTest;

    private TestReports() {
    }

    public static TestReports getInstance() {
        if (instance == null) {
            synchronized (TestReports.class) {
                if (instance == null) {
                    instance = new TestReports();
                }
            }
        }
        return instance;
    }

    public void configReport() {
        // Inicialización del reporte HTML
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(TestConstant.REPORT_PATH);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public void createTest(String method) {
        extentTest = extent.createTest(method);
    }

    public void infoStep(String step) {

        this.LOGGER.info(step);
        this.extentTest.info(step);
    }

    public void failStep(String step) {
        this.extentTest.fail(step);
        this.LOGGER.error(step);
    }

    public void passStep(String step) {
        this.extentTest.pass(step);
        this.LOGGER.info(step);
    }

    public void flush() {
        extent.flush();
    }
}
