package org.xero.mainpage;

import com.codeborne.selenide.junit.TextReport;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;
import org.xero.pages.loginPage;
import org.xero.pages.mainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
//import static com.codeborne.selenide.Selenide.$$;

public class mainPageTests {
	mainPage xeroMainPage = new mainPage();
    @Rule
    public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);

	@Before
    public void logIntoXero() {
        new loginPage().loginUsingTestAccount();
    }
    @Test
    public void tryThingsOutMenuTests() {
    	xeroMainPage.getCreateAndSendInvoiceLink().shouldBe(visible);
    	xeroMainPage.getAddOthersDetailsLink().shouldBe(visible);
    }
    @Test
    public void HeaderMenuTests() {
    	xeroMainPage.getUserIconButton().shouldBe(enabled);
    	xeroMainPage.getPayrollMenu().shouldBe(enabled);
        xeroMainPage.getBusinessMenu().shouldBe(enabled);
        xeroMainPage.getAccountingMenu().shouldBe(enabled);
    	xeroMainPage.getPayrollMenu().shouldHave(attribute("data-on", "click"));
    }  
    @After
    public void closeWebDriver() {
    	Selenide.closeWebDriver();
    }

 }
