package com.eduardo.ita.factories;

import com.eduardo.ita.config.EnvironmentConfig;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public final class BrowserFactory {

    private BrowserFactory() {
    }

    public static Browser createBrowser(Playwright playwright) {
        String browser = EnvironmentConfig.browser().toLowerCase();

        return switch (browser) {
            case "firefox" -> playwright.firefox()
                    .launch(new BrowserType.LaunchOptions()
                            .setHeadless(EnvironmentConfig.headless()));

            case "webkit" -> playwright.webkit()
                    .launch(new BrowserType.LaunchOptions()
                            .setHeadless(EnvironmentConfig.headless()));

            default -> playwright.chromium()
                    .launch(new BrowserType.LaunchOptions()
                            .setHeadless(EnvironmentConfig.headless()));
        };
    }
}