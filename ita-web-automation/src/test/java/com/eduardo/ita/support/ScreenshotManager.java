package com.eduardo.ita.support;

import com.microsoft.playwright.Page;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotManager {

    private ScreenshotManager() {
    }

    public static void takeScreenshot(Page page, String testName) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String fileName = testName + "_" + timestamp + ".png";

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Path.of("target/screenshots/" + fileName))
                .setFullPage(true));
    }
}