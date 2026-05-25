package com.eduardo.ita.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage {

    private static final String LOGIN_PATH = "/login";

    private final Page page;
    private final String baseUrl;

    public LoginPage(Page page, String baseUrl) {
        this.page = page;
        this.baseUrl = baseUrl.replaceAll("/+$", "");
    }

    private Locator emailInput() {
        return page.getByTestId("email");
    }

    private Locator passwordInput() {
        return page.getByTestId("senha");
    }

    private Locator loginButton() {
        return page.getByTestId("entrar");
    }

    private Locator alertMessage() {
        return page.locator("[role='alert'], .alert, .alert-danger");
    }

    public void open() {
        page.navigate(baseUrl + LOGIN_PATH);
        emailInput().waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );
    }

    public void fillEmail(String email) {
        emailInput().fill(email);
    }

    public void fillPassword(String password) {
        passwordInput().fill(password);
    }

    public void submitLogin() {
        loginButton().click();
    }

    public boolean isErrorMessageVisible() {
        return isAlertVisible();
    }

    public boolean hasRequiredFieldsValidation() {
        return hasNativeRequiredValidation() || isAlertVisible();
    }

    public String emailValidationMessage() {
        return String.valueOf(emailInput().evaluate("element => element.validationMessage"));
    }

    public String passwordValidationMessage() {
        return String.valueOf(passwordInput().evaluate("element => element.validationMessage"));
    }

    private boolean hasNativeRequiredValidation() {
        return !emailValidationMessage().isBlank() || !passwordValidationMessage().isBlank();
    }

    private boolean isAlertVisible() {
        try {
            alertMessage().first().waitFor(
                    new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE)
                            .setTimeout(5000)
            );
            return true;
        } catch (PlaywrightException exception) {
            return false;
        }
    }
}
