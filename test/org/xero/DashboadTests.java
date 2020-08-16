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
import org.xero.pages.dashBoardPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
//import static com.codeborne.selenide.Selenide.$$;

public class DashboadTests {
	mainPage xeroMainPage = new mainPage();
	dashBoardPage dashBoardPage = new dashBoardPage();
    @Rule
    public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);

	@Before
    public void logIntoXero() {
        new loginPage().loginUsingTestAccount();
    }
 
    @Test
    public void bankWidgetTests() {    	
    	
    	/*xeroMainPage.getDashBoardLinkElement().click();    	
    	xeroMainPage.getPayrollMenu().shouldBe(enabled);
        xeroMainPage.getBusinessMenu().shouldBe(enabled);
        xeroMainPage.getAccountingMenu().shouldBe(enabled); 
    	dashBoardPage.getPayrollMenu().shouldHave(attribute("data-on", "click"));*/
    	dashBoardPage.getBankAccountNameElement("Test consultant").shouldBe(enabled);
    }  
    @After
    public void closeWebDriver() {
    	Selenide.closeWebDriver();
    }

 }
