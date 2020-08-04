package org.xero.pages;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class AddBankAccountPage { 
	    SelenideElement DashboardMenu = $(By.linkText("Dashboard"));
	    SelenideElement connectBankAccLink = $(By.linkText("Connect your bank accounts"));
	    SelenideElement accountNameInput = $("[data-automationid='accountName']");
	    SelenideElement accountType = $("[data-automationid='accountType']");
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
		public SelenideElement getDashBoardElement() {
	    	return this.DashboardMenu;
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
	    public SelenideElement getContinueButtonElement() {
	    	return this.backButton;
	    }
	    public SelenideElement getAddAnotherAccountElement() {
	    	return this.addAccount;
	    }
	    public void selectAccountType(String accountType) {
	        this.accountType.click();
	    	String xpathString = String.format("//li[.='%s']", accountType);
	    	$$(By.xpath(xpathString)).findBy(visible).click();
	    }
	    public void validatePageTitleInclude(String bank) {
	    	  String searchString = String.format("Enter your %s account details", bank);
	    	  this.pageTitle.shouldHave(text(searchString));	
	    }	
	    
}