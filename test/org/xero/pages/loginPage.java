package org.xero.pages;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;



public class loginPage {
  private SelenideElement userNameInput = $(By.name("userName"));
  private SelenideElement passwordInput = $(By.name("password"));
  private static final String testUser = "jimmyru_1@hotmail.com";
  private static final String testPasswd = "testingl23";

  public void loginUsingTestAccount() {
	Configuration.timeout = 10000;  
    open("https://login.xero.com/");
    userNameInput.setValue(testUser);
    passwordInput.setValue(testPasswd).pressEnter();
  }
}