package com.selendroid.qa.screens.main;

import com.selendroid.qa.components.BaseJourney;
import com.selendroid.qa.components.Component;
import com.selendroid.qa.components.Elements;
import com.selendroid.qa.utils.ActionUtil;
import org.openqa.selenium.Point;
import org.testng.annotations.Test;

@Elements(elements = SelendroidElements.class)
public class SelendroidJourney extends BaseJourney {

    @Test
    public String verifyTitleAndScreen() {
        Component title = this.getComponent("title");
        if (this.isAndroid) {
            Point point = title.getElement().getLocation();
            int x = point.getX();
            int y = point.getY();
            ActionUtil.scroll(x + 100, y - 200, x + 100, y - 400, this.getDriver());
            title = this.getComponent("title");
        }
        String firstBeneficiaryName = title.getText();
        title.click();
        return firstBeneficiaryName;
    }

}
