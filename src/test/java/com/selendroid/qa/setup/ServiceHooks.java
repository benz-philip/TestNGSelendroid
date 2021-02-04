package com.selendroid.qa.setup;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.BeforeClass;

import java.io.File;

@Log4j2
public class ServiceHooks {

    static ExtentTest test;
    static ExtentReports report;

    @BeforeClass
    public static void startTest() {

    }

}
