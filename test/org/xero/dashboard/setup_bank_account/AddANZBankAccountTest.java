package org.xero.dashboard.setup_bank_account;

import com.codeborne.selenide.junit.TextReport;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.xero.pages.loginPage;
import org.xero.pages.mainPage;
import org.xero.pages.AddBankAccountPage;
import org.xero.pages.ConnectWithBankPage;
import org.xero.pages.dashBoardPage;
import org.xero.pages.Utils;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.*;

@RunWith(DataProviderRunner.class)
public class AddANZBankAccountTest extends AddBankAccountPage{
	private final String BankName = "ANZ (NZ)";
	private final int DASH_BOARD_WAIT_TIME = 15000;
	AddBankAccountPage anzAccountPage = new AddBankAccountPage(BankName);
	ConnectWithBankPage connectWithBankPage = new ConnectWithBankPage();
	mainPage xeroMainPage = new mainPage();
	Utils utilFunction = new Utils();
	dashBoardPage dashBoardPage = new dashBoardPage();
    @Rule
    public TestRule report = new TextReport().onFailedTest(true).onSucceededTest(true);

    @Before
    public void logIntoXero() {
        new loginPage().loginUsingTestAccount();
    }
   
    @DataProvider
    public static Object[][] TestAccountSet1() {    	 
        Object[][] data = new Object[][]{
        	    //accountName, accountType, accountNumber, currency
                {"0000 ", "Everyday (day-to-day)" , "01-7878-0900986-01", "NZD - New Zealand Dollar"},
                {"New test account name","Loan",  "01-7878-0900986-05", "NZD - New Zealand Dollar"},
                {"Test credit card","Credit Card",  "2345", "NZD - New Zealand Dollar"},
                {"周润发", "Term Deposit", "06-7878-0900986-aa", "NZD - New Zealand Dollar"},
                {"????????test account", "Other", ",,,-7878-0900986-05", "NZD - New Zealand Dollar"},
        };
        return data;
    }

    @DataProvider
    public static Object[][] TestAccountSet2() {    	 
        Object[][] data = new Object[][]{
        	    //accountName, accountType, accountNumber, currency
                {"brand new test account3","Loan",  "01-7676-0900986-05", "NZD - New Zealand Dollar"}
        };
        return data;
    }
    @Test
    @UseDataProvider("TestAccountSet1")
    public void userCanAdd_Accounts_usingDataProvider(String accName, String accType, String accNumber, String currency) {
    	boolean continueToAddAccount = false;
    	boolean testForUniqueName    = false;
    	xeroMainPage.getDashBoardLinkElement().waitUntil(enabled, DASH_BOARD_WAIT_TIME);
    	xeroMainPage.getDashBoardLinkElement().click();
    	String pageSource = WebDriverRunner.getWebDriver().getPageSource();
    	utilFunction.logMessage("test account name   - " + accName);
    	utilFunction.logMessage("test account number - " + accNumber);
    	utilFunction.logMessage("test account type   - " + accType);
    	utilFunction.logMessage("test currency       - " + currency);
        if (pageSource.contains(accName)) {
        	utilFunction.logMessage("Dashboard already included Test account - " + accName);
        	testForUniqueName = true;
        } else
          	continueToAddAccount =  true; //account not saved yet, continue to save it.
    	anzAccountPage.getConnectBankAccLinkElement().click();
        anzAccountPage.getAddBank_connection().click();
        anzAccountPage.getAccountNameLabelElement().shouldBe(visible);
        anzAccountPage.getAccountTypeLabelElement().shouldBe(visible);
        anzAccountPage.validatePageTitleInclude(BankName);
        anzAccountPage.getAccountNameInputElement().setValue(accName);
        anzAccountPage.selectAccountType(accType);
        if (accType.equals("Credit Card")) {
        	anzAccountPage.getCreditCardInput().shouldHave(value("XXXX - XXXX - XXXX -"));
        	anzAccountPage.getCreditCardInput().shouldNotBe(enabled);
        	anzAccountPage.getAccountNumberInputElement().shouldHave(attribute("maxlength", "4"));
        	anzAccountPage.getContinueButtonElement().click();
        	anzAccountPage.getMsgLast4digitsRequiredElement().shouldBe(visible);
        	anzAccountPage.getAccountNumberInputElement().setValue(accNumber);  
        }	
        else {
            anzAccountPage.getAccountNumberInputElement().setValue(accNumber);
        }    
        anzAccountPage.getCurrencyInputElement().shouldBe(enabled);
        anzAccountPage.selectCurrency(currency);
        if (continueToAddAccount) {        	
        	anzAccountPage.getContinueButtonElement().click();
        	utilFunction.logMessage("clicked continue to add the account");
        	connectWithBankPage.getConnectBankHeading().shouldBe(visible);
        	connectWithBankPage.getAccountNameElement(accName).shouldBe(visible);
        	connectWithBankPage.getAccountNumberElement(accNumber).shouldBe(visible);
        	connectWithBankPage.getDownloadFormButton().shouldBe(enabled);
        	connectWithBankPage.getIgotAformButton().shouldBe(enabled);        	
        }         
        if (testForUniqueName) { 
        	anzAccountPage.getContinueButtonElement().click();       
        	anzAccountPage.getMsgUniqueNameRequiredElement().shouldBe(visible);
        }	
     }

    @Test
    public void User_CanEnterAccountNameUpTo30Characters() {
    	String longAccountName="longAccou012345678901234567890"; //30 characters
     	xeroMainPage.getDashBoardLinkElement().waitUntil(enabled, DASH_BOARD_WAIT_TIME);
    	xeroMainPage.getDashBoardLinkElement().click();
    	anzAccountPage.getConnectBankAccLinkElement().click();
        anzAccountPage.getAddBank_connection().click();
        anzAccountPage.getAccountNameInputElement().setValue(longAccountName + "more characters");
        anzAccountPage.selectAccountType("Term Deposit");
        anzAccountPage.getAccountNameInputElement().shouldHave(value(longAccountName));       
    }

    @Test
    public void User_CanEnterAccountNumberUpTo20Characters() {
    	String longAccountNumber = "12345678901234567890"; //20 characters
     	xeroMainPage.getDashBoardLinkElement().waitUntil(enabled, DASH_BOARD_WAIT_TIME);
    	xeroMainPage.getDashBoardLinkElement().click();
    	anzAccountPage.getConnectBankAccLinkElement().click();
        anzAccountPage.getAddBank_connection().click();
        anzAccountPage.getAccountNameInputElement().setValue("Test Account Name");
        anzAccountPage.selectAccountType("Term Deposit");
        anzAccountPage.getAccountNumberInputElement().setValue(longAccountNumber + "more characters");
        anzAccountPage.getAccountNumberInputElement().shouldHave(value(longAccountNumber));        
    }

    @Test
    public void Error_WhenAccountInfoIsMissing(){
     	xeroMainPage.getDashBoardLinkElement().waitUntil(enabled, DASH_BOARD_WAIT_TIME);
    	xeroMainPage.getDashBoardLinkElement().click();
    	anzAccountPage.getConnectBankAccLinkElement().click();
        anzAccountPage.getAddBank_connection().click();
        anzAccountPage.getAccountNameLabelElement().shouldBe(visible);
        anzAccountPage.getAccountTypeLabelElement().shouldBe(visible);
        anzAccountPage.getContinueButtonElement().click();
        anzAccountPage.getMsgAccNameRequiredElement().shouldBe(visible);
        anzAccountPage.getAccountTypeElement().click();
        anzAccountPage.getAccountNameInputElement().click(); // missing account name
        anzAccountPage.getMsgAccNameRequiredElement().shouldBe(visible);
        anzAccountPage.selectAccountType("Loan");
        anzAccountPage.getAccountNameInputElement().click();
        anzAccountPage.getAccountNumberInputElement().click();
        anzAccountPage.getAccountNameInputElement().click();
        anzAccountPage.getAccountNumberInputElement().click();
        anzAccountPage.getMsgAccNumberRequiredElement().shouldBe(visible);
        anzAccountPage.getBackButtonElement().shouldBe(enabled);
        anzAccountPage.getBackButtonElement().click();
        anzAccountPage.getAccountNameLabelElement().shouldNotBe(visible);
     }    
 
   
    @After
    public void closeWebDriver() {
    	Selenide.closeWebDriver();
    }

}