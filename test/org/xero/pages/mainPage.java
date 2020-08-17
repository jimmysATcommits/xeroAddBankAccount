package org.xero.pages;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;


public class mainPage {
    public mainPage() {	    	
    }
    SelenideElement userIconButton = $("[data-automationid='xrh-addon-user-iconbutton']");
    SelenideElement DashboardMenu = $(By.linkText("Dashboard"));
    SelenideElement BusinessMenu  = $(By.xpath("//button[text()='Business']"));
    SelenideElement AccountingMenu   = $(By.xpath("//button[text()='Accounting']"));
    SelenideElement PaynowButton     = $(By.xpath("//span[text()='Pay now']"));
    SelenideElement PayrollMenu      = $(By.xpath("//button[text()='Payroll']"));
    SelenideElement createAndSendInvoiceLink = $(By.linkText("Create and send an invoice"));
    SelenideElement addOthersDetailsLink = $(By.linkText("Add the details of someone you do business with"));

    //bank feed related elememts in Dashboard
    public SelenideElement getUserIconButton() {
 	   return this.userIconButton;
    }
    public SelenideElement getDashBoardLinkElement() {
  	   return this.DashboardMenu;
    }
    public SelenideElement getPaynowButton() {
 	   return this.PaynowButton;
    }
    public SelenideElement getPayrollMenu() {
  	   return this.PayrollMenu;
     }
    public SelenideElement getBusinessMenu() {
        return this.BusinessMenu;
    }
    public SelenideElement getAccountingMenu() {
        return this.AccountingMenu;
    }
    public SelenideElement getCreateAndSendInvoiceLink() {
   	   return this.createAndSendInvoiceLink;
    }
    public SelenideElement getAddOthersDetailsLink() {
    	   return this.addOthersDetailsLink;
    }
}