package org.xero.pages;

import org.openqa.selenium.By;


import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection; 
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class dashBoardPage extends mainPage{
    public dashBoardPage() {	  
    	super();
    }
    ElementsCollection bankWidgets = $$("[data-automationid='bankWidget']");
    SelenideElement bankWidget = $("a[data-automationid='bankWidget']");
    //bank feed related elememts in Dashboard
    public SelenideElement getBankAccountNameElement(String accountName) {
       String xpathString = String.format("//*[.='%s']", accountName);
 	   return $(By.xpath(xpathString));  	   
    }
}