package com.selendroid.qa.setup;



import com.selendroid.qa.reporting.ExtentManager;
import com.selendroid.qa.reporting.ExtentTestManager;
import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.annotations.*;

import static com.selendroid.qa.appium.DriverWrapper.quitDriver;


@Log4j2
public class ServiceHooks implements ITestListener {

    public void onStart(ITestContext context) {
        ExtentManager.getInstance();
        log.info("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(ITestContext context) {
        log.info("*** Test Suite " + context.getName() + " ending ***");
        ExtentManager.getInstance().flush();
    }

    public void onFinishTest(ITestContext context) {
        log.info("*** Test Case " + context.getName() + " ending ***");
        quitDriver();
    }

    @BeforeSuite
    public void beforeSuite(ITestContext context) {
        log.info("*** Test Suite " + context.getName() + " started ***");
        ExtentManager.getInstance();
    }

    @BeforeTest
    public void beforeTest(ITestContext context) {
        log.info("*** Test " + context.getName() + " started ***");
        ExtentTestManager.startTest(context.getName());
    }

    @AfterTest
    public void afterTest(ITestContext context) {
        log.info("*** Test " + context.getName() + " ending ***");
        ExtentTestManager.endTest();
    }

    @AfterSuite
    public void afterSuite(ITestContext context) {
        log.info("*** Test Suite " + context.getName() + " ending ***");
        ExtentManager.getInstance().flush();
    }

}
