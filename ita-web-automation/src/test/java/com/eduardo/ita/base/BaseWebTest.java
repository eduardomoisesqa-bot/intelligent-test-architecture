package com.eduardo.ita.base;

import com.eduardo.ita.assertions.LoginAssertions;
import com.eduardo.ita.config.EnvironmentConfig;
import com.eduardo.ita.factories.BrowserFactory;
import com.eduardo.ita.factories.ContextFactory;
import com.eduardo.ita.fixtures.ServerestUserFixture;
import com.eduardo.ita.flows.LoginFlow;
import com.eduardo.ita.pages.HomePage;
import com.eduardo.ita.pages.LoginPage;
import com.eduardo.ita.support.TraceManager;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseWebTest {

    private Playwright playwright;
    private Browser browser;

    protected Page page;
    protected LoginFlow loginFlow;
    protected LoginAssertions loginAssertions;
    protected ServerestUserFixture serverestUserFixture;
    protected BrowserContext context;

    @BeforeEach
    void setUpBrowser() {
        TraceManager.startTrace(context);
        serverestUserFixture = new ServerestUserFixture();

        playwright = Playwright.create();
        browser = BrowserFactory.createBrowser(playwright);


        context = ContextFactory.createContext(browser);
        page = context.newPage();

        page.setDefaultTimeout(EnvironmentConfig.timeout());

        LoginPage loginPage = new LoginPage(page, EnvironmentConfig.baseUrl());
        HomePage homePage = new HomePage(page);

        loginFlow = new LoginFlow(loginPage);
        loginAssertions = new LoginAssertions(loginPage, homePage);
    }

    @AfterEach
    void tearDownBrowser() {
        if (serverestUserFixture != null) {
            serverestUserFixture.cleanup();
        }
        if (context != null){
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
        TraceManager.stopTrace(
                context,
                this.getClass().getSimpleName()
        );
    }
}