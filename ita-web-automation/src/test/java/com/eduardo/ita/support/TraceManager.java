package com.eduardo.ita.support;

import com.microsoft.playwright.BrowserContext;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TraceManager {

    private TraceManager() {
    }

    public static void startTrace(BrowserContext context) {

        context.tracing().start(
                new BrowserContext.Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );
    }

    public static void stopTrace(BrowserContext context, String testName) {

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String fileName = testName + "_" + timestamp + ".zip";

        context.tracing().stop(
                new BrowserContext.Tracing.StopOptions()
                        .setPath(Path.of("target/traces/" + fileName))
        );
    }
}