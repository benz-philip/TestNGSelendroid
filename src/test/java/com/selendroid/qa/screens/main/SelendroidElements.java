package com.selendroid.qa.screens.main;

import com.selendroid.qa.components.BaseElements;
import com.selendroid.qa.components.ComponentTags;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SelendroidElements extends BaseElements {

    @AndroidFindBy(id = "android:id/title")
    private WebElement title;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_EN_BUTTON)
    private WebElement enBtn;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_CHROME_BTN)
    private WebElement browserBtn;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_REG_BTN)
    private WebElement regBtn;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_TXT_FIELD)
    private WebElement textBox;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_WAIT_BTN)
    private WebElement progressBarBtn;

    @AndroidFindBy(id = "io.selendroid.testapp:id/input_adds_check_box")
    private WebElement checkBox;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_TXT_VIEW_BTN)
    private WebElement dispTextBtn;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_TOAST_BTN)
    private WebElement dispToastBtn;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_POPUP_BTN)
    private WebElement popupBtn;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_EXCPTN_BTN)
    private WebElement excptBtn;

    @AndroidFindBy(id = "io.selendroid.testapp:id/exceptionTestField")
    private WebElement textExcptn;

    @AndroidFindBy(id = "io.selendroid.testapp:id/topLevelElementTest")
    private WebElement dispFcsBtn;

    @AndroidFindBy(id = "android:id/button2")
    private WebElement noBtn;

    @AndroidFindBy(id = "android:id/content")
    private WebElement homeScreen;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Web View Interaction']")
    private WebElement webViewHome;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_WEBVIEW_TXT)
    private WebElement webViewTxt;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='name_input']")
    private WebElement webViewTxtBox;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_VOLVO_DRPDWN)
    private WebElement volvo;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Mercedes']")
    private WebElement mercedes;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_SEND_NAME_BTN)
    private WebElement sendNameBtn;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_SAY_HELLO_TXT)
    private WebElement successTxt;

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc='Your name is:']" +
            "/following-sibling::android.view.View)[1]")
    private WebElement nameTxt;

    @AndroidFindBy(xpath = "(//android.view.View[@content-desc='Your prefered car is:']" +
            "/following-sibling::android.view.View)[1]")
    private WebElement carTxt;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_CLICK_HERE)
    private WebElement clickHere;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Welcome to register a new User']")
    private WebElement regTitle;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputName")
    private WebElement dfltName;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement dflLang;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputUsername")
    private WebElement instUserName;

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_REG_EMAIL)
    private WebElement instEmail;

    @AndroidFindBy(id = "io.selendroid.testapp:id/inputPassword")
    private WebElement instPwd;

    @AndroidFindBy(id = "io.selendroid.testapp:id/input_adds")
    private WebElement accptAdd;

    @AndroidFindBy(id = "io.selendroid.testapp:id/btnRegisterUser")
    private WebElement regUser;

    @AndroidFindBy(id = "io.selendroid.testapp:id/buttonRegisterUser")
    private WebElement fnlRegUser;
}
