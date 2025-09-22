package com.ecologicstudios;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiCaller {

    public static String callSignupApi(String id, String username, String password) throws IOException, InterruptedException {
        // Build the URL with query params
        String url = String.format(
            "http://localhost:8080/signup?id=%s&username=%s&password=%s",
            id, username, password
        );

        // Create HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Build the GET request
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        // Send request and get response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Return JSON response as string
        return response.body();
    }

    public static void main(String[] args) {
        try {
            String json = callSignupApi("213", "zaki", "secret");
            System.out.println("Response: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
