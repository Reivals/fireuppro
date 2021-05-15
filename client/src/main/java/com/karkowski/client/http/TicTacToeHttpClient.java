package com.karkowski.client.http;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Michal on 12.05.2021
 */

public class TicTacToeHttpClient {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static <T> HttpResponse<T> makeRequest(final HttpRequest request, final Class<T> clazz) throws IOException, InterruptedException {
        return client.send(request, new JsonBodyHandler<>(clazz));
    }

    public static <T> T makeRequestGetBody(final HttpRequest request, final Class<T> clazz) throws IOException, InterruptedException {
        return TicTacToeHttpClient.makeRequest(request, clazz).body();
    }
}
