package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.example.config.RequestConfig;
import org.example.utils.enums.LogsRestAssured;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {

    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setUp() {
        // Inicialización del reporte HTML
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void startTest(Method method) {
        // Crear un nuevo test para cada método
        test = extent.createTest(method.getName());
        RequestConfig.baseUri("https://reqres.in", "/api");
        RequestConfig.enableLogs(LogsRestAssured.BODY);
        RequestConfig.contentType("application/json");
    }

    @AfterMethod
    public void endTest() {
        extent.flush();
    }

    @AfterSuite
    public void tearDown() {
        // Guardar los resultados después de cada prueba
        extent.flush();
    }
}

