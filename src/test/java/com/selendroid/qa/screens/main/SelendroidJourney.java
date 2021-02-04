package com.selendroid.qa.screens.main;

import com.selendroid.qa.components.BaseJourney;
import com.selendroid.qa.components.Component;
import com.selendroid.qa.components.Elements;
import com.selendroid.qa.enums.VerifyType;
import com.selendroid.qa.reporting.ExtentTestManager;
import com.selendroid.qa.utils.Action;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.selendroid.qa.utils.TestUtil.hideKeyboard;
import static com.selendroid.qa.appium.DriverWrapper.quitDriver;

@Elements(elements = SelendroidElements.class)
public class SelendroidJourney extends BaseJourney {

    private final String titleText = "selendroid-test-app";
    private final String webViewTitle = "Web View Interaction";
    private final String helloText = "Hello, can you please tell me your name?";
    private final String helloSuccessText = "This is my way of saying hello";
    private final String myName = "Sanjay Singh";
    private final String myCar = "mercedes";
    private final String welcomeTxt = "Welcome to register a new User";
    private final String defaultName = "Mr. Burns";
    private final String defaultLang = "Ruby";

    @BeforeMethod
    public void beforeMethod() {
        this.getDriver().launchApp();
    }

    @Test(priority = 1)
    public void verifyTitleAndElements() {
        ExtentTestManager.startTest("Verifying Title and Elements on Screen");

        Component title = this.getComponent("title");
        ExtentTestManager.report(title.verify(VerifyType.TEXT,titleText),
                "Title <b>"+ titleText +"</b> verified Successfully",
                "Title verification failed Actual: "+ title.getText() +" Expected: " + titleText);

        Component enBtn = this.getComponent("enBtn");
        ExtentTestManager.report(enBtn.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>EN Button</b> verified Successfully",
                "<b>EN Button</b> is not present");

        Component browserBtn = this.getComponent("browserBtn");
        ExtentTestManager.report(browserBtn.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>Browser Button</b> verified Successfully",
                "<b>Browser Button</b> is not present");

        Component regBtn = this.getComponent("regBtn");
        ExtentTestManager.report(regBtn.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>Registration Button</b> verified Successfully",
                "<b>Registration Button</b> is not present");

        Component textBox = this.getComponent("textBox");
        ExtentTestManager.report(textBox.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>TextBox</b> verified Successfully",
                "<b>TextBox</b> is not present");

        Component progressBarBtn = this.getComponent("progressBarBtn");
        ExtentTestManager.report(progressBarBtn.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>Progress Bar Button</b> verified Successfully",
                "<b>Progress Bar Button</b> is not present");

        Component checkBox = this.getComponent("checkBox");
        ExtentTestManager.report(checkBox.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>CheckBox</b> verified Successfully",
                "<b>CheckBox</b> is not present");

        Component dispTextBtn = this.getComponent("dispTextBtn");
        ExtentTestManager.report(dispTextBtn.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>Display Text Button</b> verified Successfully",
                "<b>Display Text Button</b> is not present");

        Component dispToastBtn = this.getComponent("dispToastBtn");
        ExtentTestManager.report(dispToastBtn.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>Display Toast Button</b> verified Successfully",
                "<b>Display Toast Button</b> is not present");

        Component popupBtn = this.getComponent("popupBtn");
        ExtentTestManager.report(popupBtn.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>Pop Up Button</b> verified Successfully",
                "<b>Pop Up Button</b> is not present");

        Component excptBtn = this.getComponent("excptBtn");
        ExtentTestManager.report(excptBtn.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>Exception Button</b> verified Successfully",
                "<b>Exception Button</b> is not present");

        Component textExcptn = this.getComponent("textExcptn");
        ExtentTestManager.report(textExcptn.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>Text Box for Exception</b> verified Successfully",
                "<b>Text Box for Exception</b> is not present");

        Component dispFcsBtn = this.getComponent("dispFcsBtn");
        ExtentTestManager.report(dispFcsBtn.verify(VerifyType.DISPLAYED,  "true"),
                "Presence of <b>Display Focus Button</b> verified Successfully",
                "<b>Display Focus Button</b> is not present");

        ExtentTestManager.endTest();
    }

    @Test(priority = 2)
    public void verifyHomeScreenNavigation() {
        ExtentTestManager.startTest("Verify homescreen after tapping no button");

        Component enBtn = this.getComponent("enBtn");
        Action.click(enBtn,"ET Button clicked successfully",
                "Unable to click ET Button");

        Component noBtn = this.getComponent("noBtn");
        Action.click(noBtn,"No,no clicked successfully",
                "Unable to click No,no");

        Component homeScreen = this.getComponent("homeScreen");
        ExtentTestManager.report(homeScreen.verify(VerifyType.DISPLAYED,"true"),
                "<b>Home Screen</b> navigated Successfully",
                "Unable to navigate to <b>Home Screen</b>");

        ExtentTestManager.endTest();
    }

    @Test(priority = 3)
    public void verifyChromePreferredCar() {

        ExtentTestManager.startTest("Verifying navigating through preferred car");

        Component chromeBtn = this.getComponent("browserBtn");
        Action.click(chromeBtn,"Chrome Button clicked successfully",
                "Unable to click Chrome Button");

        Component webViewHome = this.getComponent("webViewHome");
        ExtentTestManager.report(webViewHome.verify(VerifyType.TEXT,  webViewTitle),
                "Title <b>"+ webViewTitle +"</b> verified Successfully",
                "Title verification failed Actual: "+ webViewHome.getText() +" Expected: " + webViewTitle);

        Component webViewTxt = this.getComponent("webViewTxt");
        String actualHelloText = webViewTxt.getElement().getAttribute("content-desc");
        ExtentTestManager.report(helloText.equals(actualHelloText),
                "Text <b>"+ helloText +"</b> verified successfully on Web View",
                "Web View welcome text verification failed Actual: "+ actualHelloText
                        +" Expected: " + helloText);

        Component webViewTxtBox = this.getComponent("webViewTxtBox");
        Action.enterText(webViewTxtBox, myName, "Name successfully inserted in TextBox",
                "Unable to insert name in web view text box");

        Component volvo = this.getComponent("volvo");
        Action.click(volvo,"Successfully clicked preferred car drop down",
                "Unable to click on preferred car drop down");

        Component mercedes = this.getComponent("mercedes");
        Action.click(mercedes,"Successfully selected <b>Mercedes</b> as preferred car",
                "Unable to select <b>Mercedes</b> as preferred car");

        Component sendNameBtn = this.getComponent("sendNameBtn");
        Action.click(sendNameBtn,"Successfully clicked <b>Send me your name</b>",
                "Unable to click <b>Send me your name</b>");

        Component successTxt = this.getComponent("successTxt");
        String actHelloSuccessTxt = successTxt.getElement().getAttribute("content-desc");
        ExtentTestManager.report(helloSuccessText.equals(actHelloSuccessTxt),
                "Text <b>"+ helloSuccessText +"</b> verified Successfully on success screen",
                "Success Text verification failed Actual: "+ actHelloSuccessTxt
                        +" Expected: " + helloSuccessText);

        Component nameTxt = this.getComponent("nameTxt");
        String actualName = nameTxt.getElement().getAttribute("content-desc");
        ExtentTestManager.report(actualName.contains(myName),
                "Name <b>"+ myName +"</b> verified Successfully",
                "Name verification failed Actual: "+ actualName
                        +" Expected: " + myName);

        Component carTxt = this.getComponent("carTxt");
        String actualCarName = carTxt.getElement().getAttribute("content-desc");
        ExtentTestManager.report(actualCarName.contains(myCar),
                "Car <b>"+ myCar +"</b> verified Successfully",
                "Car verification failed Actual: "+ actualCarName
                        +" Expected: " + myCar);

        Component clickHere = this.getComponent("clickHere");
        Action.click(clickHere,"Successfully clicked click here link",
                "Unable to click on click here link");

        volvo = this.getComponent("volvo");
        ExtentTestManager.report("Volvo".equals(volvo.getElement().getAttribute("content-desc")),
                "Verify <b>Volvo</b> as selected car",
                "Default car verification failed Actual: "+ volvo.getText()
                        +" Expected: volvo");

        ExtentTestManager.endTest();

    }

    @Test(priority = 4)
    public void verifyRegisterUserScreen() {
        ExtentTestManager.startTest("Verifying Register User Screen");

        Component regBtn = this.getComponent("regBtn");
        Action.click(regBtn,"Successfully clicked register user button",
                "Unable to click on register user button");

        Component regTitle = this.getComponent("regTitle");
        ExtentTestManager.report(regTitle.verify(VerifyType.TEXT,welcomeTxt),
                "Welcome Text <b>"+ welcomeTxt +"</b> verified Successfully",
                "Welcome Text verification failed Actual: "+ regTitle.getText() +" Expected: " + welcomeTxt);

        hideKeyboard(this.getDriver());

        Component dfltName = this.getComponent("dfltName");
        ExtentTestManager.report(dfltName.verify(VerifyType.TEXT,defaultName),
                "Default pre-filled name <b>"+ defaultName +"</b> verified Successfully",
                "Default pre-filled name verification failed Actual: "+ dfltName.getText() +" Expected: " + defaultName);

        Component dflLang = this.getComponent("dflLang");
        ExtentTestManager.report(dflLang.verify(VerifyType.TEXT,defaultLang),
                "Default programming language as <b>"+ defaultLang +"</b> verified Successfully",
                "Default programming language verification failed Actual: "+ dflLang.getText() +" Expected: " + defaultLang);

        Component instUserName = this.getComponent("instUserName");
        Action.enterText(instUserName, "sanjaysingh", "Username <bsanjaysingh</b> successfully inserted",
                "Unable to insert username <b>sanjaysingh</b>");

        hideKeyboard(this.getDriver());

        Component instEmail = this.getComponent("instEmail");
        Action.enterText(instEmail, "sanjaysingh@gmail.com", "email <bsanjaysingh@gmail.com</b> successfully inserted",
                "Unable to insert email <b>sanjaysingh@gmail.com</b>");

        hideKeyboard(this.getDriver());

        Component instPwd = this.getComponent("instPwd");
        Action.enterText(instPwd, "sanjaysingh", "password <bsanjaysingh</b> successfully inserted",
                "Unable to insert password <b>sanjaysingh</b>");

        hideKeyboard(this.getDriver());

        Component accptAdd = this.getComponent("accptAdd");
        Action.click(accptAdd, "CheckBox <b>Accept Adds</b> successfully  clicked",
                "Unable to click checkbox <b>Accept Adds</b>");

        Component regUser = this.getComponent("regUser");
        Action.click(regUser, "<b>Register User</b> Button clicked successfully",
                "Unable to click <b>Register User</b> Button ");

        Component fnlRegUser = this.getComponent("fnlRegUser");
        Action.click(fnlRegUser, "<b>Register User</b> Button on final screen clicked successfully",
                "Unable to click <b>Register User</b> Button on final screen");

        Component homeScreen = this.getComponent("homeScreen");
        ExtentTestManager.report(homeScreen.verify(VerifyType.DISPLAYED,"true"),
                "<b>Home Screen</b> navigated Successfully",
                "Unable to navigate to <b>Home Screen</b>");

        ExtentTestManager.endTest();
    }

    @AfterMethod
    public void afterMethod() {
        this.getDriver().closeApp();
    }
}
