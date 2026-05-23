package com.eduardo.ita.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class HomePage {

    private final Page page;

    public HomePage(Page page) {
        this.page = page;
    }

    public boolean isLoaded() {
        try {
            page.waitForURL("**/home", new Page.WaitForURLOptions().setTimeout(5000));
            return true;
        } catch (PlaywrightException exception) {
            return false;
        }
    }
}
