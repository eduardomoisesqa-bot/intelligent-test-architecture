package com.eduardo.ita.flows;

import com.eduardo.ita.pages.LoginPage;

public class LoginFlow {

    private final LoginPage loginPage;

    public LoginFlow(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    public void loginAs(String email, String password) {
        loginPage.open();
        loginPage.fillEmail(email);
        loginPage.fillPassword(password);
        loginPage.submitLogin();
    }

    public void submitWithoutCredentials() {
        loginPage.open();
        loginPage.submitLogin();
    }
}
