package org.xero.dashboard.setup_bank_account;

import com.codeborne.selenide.junit.TextReport;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.rules.TestRule;
import org.openqa.selenium.By;
import org.xero.pages.loginPage;
import org.xero.pages.AddBankAccountPage;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import static com.codeborne.selenide.Condition.*;

public class AddANZBankAccountTest extends AddBankAccountPage{
	private final String BankName = "ANZ (NZ)";
	AddBankAccountPage anzAccountPage = new AddBankAccountPage(BankName);
	
    @Rule
    public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);

    @Before
    public void logIntoXero() {
    	new loginPage().loginUsingTestAccount();
    }
    @Test
    public void userCanAdd_differentANZ_AccountType() {                
    	anzAccountPage.getDashBoardElement().click();
    	anzAccountPage.getConnectBankAccLinkElement().click();
        anzAccountPage.getAddBank_connection().click();
        anzAccountPage.getAccountNameLabelElement().shouldBe(visible);
        anzAccountPage.getAccountTypeLabelElement().shouldBe(visible);
        anzAccountPage.validatePageTitleInclude(BankName);
        anzAccountPage.getAccountNameInputElement().find("input").setValue("Test Company");
        anzAccountPage.selectAccountType("Everyday (day-to-day)");
        anzAccountPage.getAccountNumberInputElement().setValue("01-7878-0900986-01");
        anzAccountPage.selectAccountType("Loan");
        anzAccountPage.getAccountNumberInputElement().setValue("01-7878-0900986-02");
        anzAccountPage.selectAccountType("Credit Card");
        anzAccountPage.getCreditCardNumberLabelElement().shouldBe(visible);
        anzAccountPage.getAccountNumberInputElement().setValue("06-7878-0900986-03");
        anzAccountPage.selectAccountType("Other");
        anzAccountPage.getAccountNumberInputElement().setValue("11-7878-0900985-03");
        anzAccountPage.selectAccountType("Term Deposit");
        anzAccountPage.getAccountNumberInputElement().setValue("01-7878-0900983-00");
        anzAccountPage.getCurrencyInputElement().shouldBe(enabled);
     }

    @Test
    public void UserCanAdd_AndRemoveBankAccount() {
    	anzAccountPage.getDashBoardElement().click();
    	anzAccountPage.getConnectBankAccLinkElement().click();
        anzAccountPage.getAddBank_connection().click();
        anzAccountPage.getAccountNameLabelElement().shouldBe(visible);
        anzAccountPage.getAccountTypeLabelElement().shouldBe(visible);
    	anzAccountPage.getAddAnotherAccountElement().shouldBe(enabled);
    	anzAccountPage.getAddAnotherAccountElement().click();
    	//account name label
    	$$(By.xpath("//span[text()='Account Name']")).shouldHave(sizeGreaterThan(1));
    	anzAccountPage.getRemoveButtonElement().click();
    	$$(By.xpath("//span[text()='Account Name']")).shouldHave(size(1));
    }
    
    @Test
    public void Error_WhenAccountInfoIsMissing(){
    	anzAccountPage.getDashBoardElement().click();
    	anzAccountPage.getConnectBankAccLinkElement().click();
        anzAccountPage.getAddBank_connection().click();
        anzAccountPage.getAccountNameLabelElement().shouldBe(visible);
        anzAccountPage.getAccountTypeLabelElement().shouldBe(visible);
        anzAccountPage.getAccountTypeElement().click();
        anzAccountPage.getAccountNameInputElement().find("input").click(); // missing account name
        $(By.xpath("//li[.='Account Name required']")).shouldBe(visible);
        anzAccountPage.selectAccountType("Loan");
        anzAccountPage.getAccountNameInputElement().find("input").click();
        anzAccountPage.getAccountNumberInputElement().click();
        anzAccountPage.getAccountNameInputElement().find("input").click();
        anzAccountPage.getAccountNumberInputElement().click();
        $(By.xpath("//li[.='Account Number required']")).shouldBe(visible);
        anzAccountPage.getBackButtonElement().shouldBe(enabled);
        anzAccountPage.getBackButtonElement().click();
        anzAccountPage.getAccountNameLabelElement().shouldNotBe(visible);
     }    
 
    @After
    public void closeWebDriver() {
    	Selenide.closeWebDriver();
    }

}