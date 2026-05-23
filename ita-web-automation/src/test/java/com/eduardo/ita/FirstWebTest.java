package com.eduardo.ita;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FirstWebTest {

    @Test
    void shouldOpenPlaywrightWebsite() {
        try (Playwright playwright = Playwright.create()) {
            try (Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(false)
            )) {

                Page page = browser.newPage();
                page.navigate("https://playwright.dev/java/");

                assertTrue(page.title().contains("Playwright"));
            }
        }
    }
}
