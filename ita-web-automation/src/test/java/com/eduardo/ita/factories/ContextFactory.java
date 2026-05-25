package com.eduardo.ita.factories;

import com.eduardo.ita.config.EnvironmentConfig;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;

public final class ContextFactory {

    private ContextFactory() {
    }

    public static BrowserContext createContext(Browser browser) {
        return browser.newContext(
                new Browser.NewContextOptions()
                        .setBaseURL(EnvironmentConfig.baseUrl())
                        .setViewportSize(1366, 768)
        );
    }
}