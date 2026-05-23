package com.eduardo.ita.fixtures;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ServerestUserFixture {

    private static final String API_BASE_URL = System.getProperty("serverest.apiUrl", "https://serverest.dev");
    private static final String PASSWORD = "Teste@123";
    private static final Pattern USER_ID_PATTERN = Pattern.compile("\"_id\"\\s*:\\s*\"([^\"]+)\"");

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private ServerestUserFixture() {
    }

    public static RegisteredUser registeredUser() {
        String email = "ita." + Instant.now().toEpochMilli() + "@test.com";
        String body = """
                {
                  "nome": "ITA Test User",
                  "email": "%s",
                  "password": "%s",
                  "administrador": "true"
                }
                """.formatted(email, PASSWORD);

        HttpResponse<String> response = send(
                HttpRequest.newBuilder()
                        .uri(URI.create(API_BASE_URL + "/usuarios"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(body))
                        .build()
        );

        if (response.statusCode() != 201) {
            throw new IllegalStateException("Não foi possível cadastrar usuário de teste. Status: "
                    + response.statusCode() + ". Body: " + response.body());
        }

        return new RegisteredUser(extractUserId(response.body()), email, PASSWORD);
    }

    public static void removeUser(RegisteredUser user) {
        if (user == null || user.id() == null || user.id().isBlank()) {
            return;
        }

        send(
                HttpRequest.newBuilder()
                        .uri(URI.create(API_BASE_URL + "/usuarios/" + user.id()))
                        .DELETE()
                        .build()
        );
    }

    private static HttpResponse<String> send(HttpRequest request) {
        try {
            return HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException exception) {
            throw new IllegalStateException("Falha de comunicação com a API ServeRest.", exception);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Cadastro de usuário de teste interrompido.", exception);
        }
    }

    private static String extractUserId(String body) {
        Matcher matcher = USER_ID_PATTERN.matcher(body);

        if (!matcher.find()) {
            throw new IllegalStateException("Resposta de cadastro não retornou _id. Body: " + body);
        }

        return matcher.group(1);
    }
}
