package com.eduardo.ita.tests;

import com.eduardo.ita.base.BaseWebTest;
import com.eduardo.ita.fixtures.RegisteredUser;
import com.eduardo.ita.fixtures.ServerestUserFixture;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseWebTest {

    @Test
    void shouldLoginWithValidUser() {
        RegisteredUser user = ServerestUserFixture.registeredUser();

        try {
            loginFlow.loginAs(user.email(), user.password());

            loginAssertions.shouldAccessAuthenticatedArea();
        } finally {
            ServerestUserFixture.removeUser(user);
        }
    }

    @Test
    void shouldShowErrorForInvalidUser() {
        loginFlow.loginAs("invalid@test.com", "invalid");

        loginAssertions.shouldShowInvalidLoginMessage();
    }

    @Test
    void shouldValidateRequiredFields() {
        loginFlow.submitWithoutCredentials();

        loginAssertions.shouldValidateRequiredFields();
    }
}
