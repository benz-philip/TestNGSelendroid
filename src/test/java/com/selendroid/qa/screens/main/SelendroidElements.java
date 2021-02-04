package com.selendroid.qa.screens.main;

import com.selendroid.qa.components.BaseElements;
import com.selendroid.qa.components.ComponentTags;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class SelendroidElements extends BaseElements {

    @AndroidFindBy(accessibility = ComponentTags.ACCESSIBILITY_BUTTON_TITLE)
    private WebElement title;
}
