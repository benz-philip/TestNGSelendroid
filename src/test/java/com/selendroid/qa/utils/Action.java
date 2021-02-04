package com.selendroid.qa.utils;

import com.aventstack.extentreports.Status;
import com.selendroid.qa.components.Component;
import com.selendroid.qa.reporting.ExtentTestManager;

public class Action {

    public static void click(Component component, String msgPass, String msgFail) {
        try {
            component.click();
            ExtentTestManager.getTest().log(Status.PASS,msgPass);
        }catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL,msgFail);
        }
    }

    public static void enterText(Component component, String text, String msgPass, String msgFail) {
        try {
            component.enterText(text);
            ExtentTestManager.getTest().log(Status.PASS,msgPass);
        }catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL,msgFail);
        }
    }
}
