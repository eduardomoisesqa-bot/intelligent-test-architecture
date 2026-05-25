package com.eduardo.ita.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class EnvironmentConfig {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                     EnvironmentConfig.class
                             .getClassLoader()
                             .getResourceAsStream("config.properties")) {

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar config.properties", e);
        }
    }

    private EnvironmentConfig() {
    }

    public static String baseUrl() {
        return System.getProperty(
                "base.url",
                properties.getProperty("base.url")
        );
    }

    public static String apiUrl() {
        return System.getProperty(
                "api.url",
                properties.getProperty("api.url")
        );
    }

    public static String browser() {
        return System.getProperty(
                "browser",
                properties.getProperty("browser")
        );
    }

    public static boolean headless() {
        return Boolean.parseBoolean(
                System.getProperty(
                        "headless",
                        properties.getProperty("headless")
                )
        );
    }

    public static int timeout() {
        return Integer.parseInt(
                System.getProperty(
                        "timeout",
                        properties.getProperty("timeout")
                )
        );
    }
}