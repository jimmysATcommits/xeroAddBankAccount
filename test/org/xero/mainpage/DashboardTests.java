package org.xero.mainpage;

import com.codeborne.selenide.junit.TextReport;
import org.junit.Rule;
//import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.After;
import org.junit.rules.TestRule;
import org.xero.pages.loginPage;
import org.xero.pages.mainPage;
import org.xero.pages.dashBoardPage;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.Selenide;


public class DashboardTests {
	mainPage xeroMainPage = new mainPage();
	dashBoardPage dashBoardPage = new dashBoardPage();
    @Rule
    public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);

	@Before
    public void logIntoXero() {
        new loginPage().loginUsingTestAccount();
    }
 
    @Ignore
    public void bankWidgetTests() {    	
    	
    	xeroMainPage.getDashBoardLinkElement().click();    	
    	xeroMainPage.getPayrollMenu().shouldBe(enabled);
        xeroMainPage.getBusinessMenu().shouldBe(enabled);
        xeroMainPage.getAccountingMenu().shouldBe(enabled); 
    	dashBoardPage.getPayrollMenu().shouldHave(attribute("data-on", "click"));
    	dashBoardPage.getBankAccountNameElement("Test consultant").shouldBe(enabled);
    }  
    @After
    public void closeWebDriver() {
    	Selenide.closeWebDriver();
    }

 }
