package org.xero.pages;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection; 
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class AddBankAccountPage { 
	    SelenideElement connectBankAccLink = $(By.linkText("Connect your bank accounts"));
	    SelenideElement accountNameInput = $("[data-automationid='accountName']").find("input");
	    SelenideElement accountType = $("[data-automationid='accountType']");
	    SelenideElement currencyCombo = $("div[id^='currencyCombo'][id$='trigger-picker']");
	    SelenideElement addAccount = $("[data-automationid='addAccount']");
	    SelenideElement removeAccountButton = $("[data-automationid='removeAccountButton']");
	    SelenideElement accountNumberInput  = $$(("input[id^='accountnumber']")).findBy(visible);
	    SelenideElement accountNameLabel = $(By.xpath("//span[text()='Account Name']"));
	    SelenideElement accountTypeLabel = $(By.xpath("//span[text()='Account Type']"));
	    SelenideElement creditCardNumberLabel = $(By.xpath("//label[text()='Credit Card Number']"));
	    SelenideElement currency = $("[data-automationid='currency']");
	    SelenideElement backButton = $("[data-automationid='backButton']");
	    SelenideElement continueButton = $("[data-automationid='continueButton']");
	    SelenideElement addBank_link; // which bank in add Bank Accounts page
	    SelenideElement pageTitle = $(".ba-page-title");
	    ElementsCollection accountNameLabels = $$(By.xpath("//span[text()='Account Name']"));
	    SelenideElement msgAccountNameRequired = $(By.xpath("//li[.='Account Name required']"));
	    SelenideElement msgAccountNumberRequired = $(By.xpath("//li[.='Account Number required']"));
	    SelenideElement msgLast4DigitsRequired = $(By.xpath("//li[.='Last 4 digits required']"));
	    SelenideElement msgPleaseEnterUniqueName = $(By.xpath("//*[.='Please enter a unique Name']"));
	    
	    SelenideElement creditCardInput = $("[value='XXXX - XXXX - XXXX -']");
	    String bank;
        
	    public AddBankAccountPage() {	    	
	    }
		public AddBankAccountPage(String bankName) {
			this.bank = bankName;
			String xpathString = String.format("//li[.='%s']", bankName);
	    	this.addBank_link =  $(By.xpath(xpathString));
	    }
		
		

	    public SelenideElement getAddBank_connection() {
	    	return this.addBank_link;
	    }	    
	    public SelenideElement getConnectBankAccLinkElement() {
	    	return this.connectBankAccLink;
	    }
	    public SelenideElement getAccountNameInputElement() {
	    	return this.accountNameInput;
	    }
	    public SelenideElement getAccountNameLabelElement() {
	    	return this.accountNameLabel;
	    }	    
	    public ElementsCollection getAccountNameLabels() {
	    	return this.accountNameLabels;
	    }
	    public SelenideElement getAccountTypeLabelElement() {
	    	return this.accountTypeLabel;
	    }
	    public SelenideElement getCreditCardNumberLabelElement() {
	    	return this.creditCardNumberLabel;
	    }
	    public SelenideElement getAccountTypeElement() {
	    	return this.accountType;
	    }
	    public SelenideElement getAccountNumberInputElement() {
	    	return this.accountNumberInput;
	    }
	    public SelenideElement getCurrencyInputElement() {
	    	return this.currency;
	    }
	    public SelenideElement getBackButtonElement() {
	    	return this.backButton;
	    }
	    public SelenideElement getRemoveButtonElement() {
	    	return this.removeAccountButton;
	    }
	    public SelenideElement getMsgAccNameRequiredElement() {
	    	return this.msgAccountNameRequired;
	    }
	    public SelenideElement getMsgAccNumberRequiredElement() {
	    	return this.msgAccountNumberRequired;
	    }
	    public SelenideElement getMsgUniqueNameRequiredElement() {
	    	return this.msgPleaseEnterUniqueName;
	    }
	    public SelenideElement getMsgLast4digitsRequiredElement() {
	    	return this.msgLast4DigitsRequired;
	    }
	    public SelenideElement getContinueButtonElement() {
	    	return this.continueButton;
	    }
	    public SelenideElement getAddAnotherAccountElement() {
	    	return this.addAccount;
	    }
	    public SelenideElement getCreditCardInput() {
	    	return this.creditCardInput;
	    }
	    public void selectAccountType(String accountType) {
	        this.accountType.click();
	    	String xpathString = String.format("//li[.='%s']", accountType);
	    	$$(By.xpath(xpathString)).findBy(visible).click();
	    }
	    public void selectCurrency(String currency) {
	    	$$("[id^='currencyCombo']").findBy(visible).click();
	    	String xpathString = String.format("//*[.='%s']", currency);
	    	$$(By.xpath(xpathString)).findBy(visible).click();
	    }
	    public void validatePageTitleInclude(String bank) {
	    	  String searchString = String.format("Enter your %s account details", bank);
	    	  this.pageTitle.shouldHave(text(searchString));	
	    }	
	    
}