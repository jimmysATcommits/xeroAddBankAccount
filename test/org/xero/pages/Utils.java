package org.xero.pages;

public class Utils {
    public void logMessage(String msg) {
    	final String ANSI_YELLOW = "\u001B[33m";
    	final String ANSI_RESET = "\u001B[0m";
    	System.out.println(ANSI_YELLOW + msg + ANSI_RESET);
    }
}
