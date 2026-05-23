package com.eduardo.ita.base;

import com.eduardo.ita.assertions.LoginAssertions;
import com.eduardo.ita.flows.LoginFlow;
import com.eduardo.ita.pages.HomePage;
import com.eduardo.ita.pages.LoginPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseWebTest {

    private static final String DEFAULT_BASE_URL = "https://front.serverest.dev";

    private Playwright playwright;
    private Browser browser;
    protected Page page;
    protected LoginFlow loginFlow;
    protected LoginAssertions loginAssertions;

    @BeforeEach
    void setUpBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless()));
        page = browser.newPage();

        LoginPage loginPage = new LoginPage(page, baseUrl());
        HomePage homePage = new HomePage(page);

        loginFlow = new LoginFlow(loginPage);
        loginAssertions = new LoginAssertions(loginPage, homePage);
    }

    @AfterEach
    void tearDownBrowser() {
        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }

    private String baseUrl() {
        return System.getProperty(
                "app.baseUrl",
                System.getenv().getOrDefault("APP_BASE_URL", DEFAULT_BASE_URL)
        );
    }

    private boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "true"));
    }
}
