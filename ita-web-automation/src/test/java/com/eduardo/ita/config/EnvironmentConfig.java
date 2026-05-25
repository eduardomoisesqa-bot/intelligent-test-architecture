package com.eduardo.ita.config;

public final class EnvironmentConfig {
    private  EnvironmentConfig(){

    }
    public static String BaseUrl(){
        return  System.getProperty(
                "base.url",
                "https://front.serverest.dev/"
        );

    }
    public static String apiUrl() {
        return System.getProperty(
                "api.url",
                "https://serverest.dev"
        );
    }

    public static String browser() {
        return System.getProperty(
                "browser",
                "chromium"
        );
    }

    public static boolean headless() {
        return Boolean.parseBoolean(
                System.getProperty("headless", "false")
        );
    }

    public static int timeout() {
        return Integer.parseInt(
                System.getProperty("timeout", "10000")
        );
    }

}
