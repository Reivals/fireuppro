package com.karkowski.client.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * @author Michal on 12.05.2021
 */

public class JsonBodyHandler<W> implements HttpResponse.BodyHandler<W> {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final Class<W> wClass;

    public JsonBodyHandler(final Class<W> wClass) {
        this.wClass = wClass;
    }

    @Override
    public HttpResponse.BodySubscriber<W> apply(final HttpResponse.ResponseInfo responseInfo) {
        return asJSON(wClass);
    }

    public static <T> HttpResponse.BodySubscriber<T> asJSON(final Class<T> targetType) throws IllegalArgumentException {
        HttpResponse.BodySubscriber<String> upstream = HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8);

        return HttpResponse.BodySubscribers.mapping(
                upstream,
                (final String body) -> {
                    try {
                        return objectMapper.readValue(body, targetType);
                    } catch (final IOException e) {
                        throw new IllegalArgumentException(body);
                    }
                });
    }
}

