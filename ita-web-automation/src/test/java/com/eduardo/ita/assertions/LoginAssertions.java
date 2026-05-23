package com.eduardo.ita.assertions;

import com.eduardo.ita.pages.HomePage;
import com.eduardo.ita.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAssertions {

    private final LoginPage loginPage;
    private final HomePage homePage;

    public LoginAssertions(LoginPage loginPage, HomePage homePage) {
        this.loginPage = loginPage;
        this.homePage = homePage;
    }

    public void shouldAccessAuthenticatedArea() {
        assertTrue(
                homePage.isLoaded(),
                "Usuário válido deveria acessar a área autenticada."
        );
    }

    public void shouldShowInvalidLoginMessage() {
        assertTrue(
                loginPage.isErrorMessageVisible(),
                "Usuário inválido deveria visualizar mensagem de erro."
        );
    }

    public void shouldValidateRequiredFields() {
        assertTrue(
                loginPage.hasRequiredFieldsValidation(),
                "Campos obrigatórios deveriam apresentar validação."
        );
    }
}
