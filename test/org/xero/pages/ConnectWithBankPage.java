package org.xero.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection; 
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;



public class ConnectWithBankPage { 
	    SelenideElement connectBankPageHeading = $("[class='xui-pageheading--title']");
	    ElementsCollection accountNames   = $$("[class='bankaccounts-account--name']");
	    ElementsCollection accountNumbers = $$("[class='bankaccounts-account--number']");
	    SelenideElement DownloadFormButton = $("button[data-automationid='connectbank-buttonDownloadForm']");
	    SelenideElement IhaveFormButton = $("[data-automationid='connectbank-buttonIHaveAForm']");
        
	    public ConnectWithBankPage() {	    	
	    }
		public SelenideElement getConnectBankHeading() {
	    	return this.connectBankPageHeading;
	    }	    
	    public SelenideElement getAccountNameElement(String accName) {
	    	return accountNames.find(text(accName));
	    }
	    public SelenideElement getAccountNumberElement(String accNumber) {
	    	return this.accountNumbers.find(text(accNumber.replace("-", "")));
	    }
	    public SelenideElement getDownloadFormButton() {
	    	return this.DownloadFormButton;
	    }
	    public SelenideElement getIgotAformButton() {
	    	return this.IhaveFormButton;
	    }
	    
}