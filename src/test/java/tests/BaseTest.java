package tests;

import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.config.RequestConfig;
import org.example.utils.TestConstant;
import org.example.utils.TestReports;
import org.example.utils.enums.LogsRestAssured;
import org.testng.annotations.*;


import java.lang.reflect.Method;

public class BaseTest {

    protected TestReports reports = TestReports.getInstance();

    @BeforeSuite
    public void setUp() {
        // Inicialización del reporte HTML
        reports.configReport();
    }

    @BeforeMethod
    public void startTest(Method method) {
        // Crear un nuevo test para cada método
        reports.createTest(method.getName());
        reports.infoStep("Config Request");
        RequestConfig.baseUri(TestConstant.URL,"/api");
        RequestConfig.contentType("application/json");
        RequestConfig.contentType("multipart/form-data");
        RequestConfig.enableLogs(LogsRestAssured.DISABLE_ALL);
    }

    @AfterMethod
    public void endTest() {
        reports.flush();
    }
}

