package com.karkowski.client.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karkowski.client.model.MoveDTO;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * @author Michal on 12.05.2021
 */

public class RequestMakerHelper {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String SERVER_URL = "http://localhost:8080/v1/boards/";

    public static HttpRequest createBoardRequest() {
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create(SERVER_URL))
                .build();
    }

    public static HttpRequest createGetBoardRequest(final String boardId) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(SERVER_URL+boardId))
                .build();
    }

    public static HttpRequest createJoinTableRequest(final String boardId) {
        return HttpRequest.newBuilder()
                .method("PATCH", HttpRequest.BodyPublishers.noBody())
                .header("Content-Type","application/json")
                .uri(URI.create(SERVER_URL+boardId+"/join"))
                .build();
    }

    public static HttpRequest createCheckGameStatusRequest(String boardId) {
        return HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(SERVER_URL+boardId+"/gameEnded"))
                .build();
    }

    public static HttpRequest createMakeMoveRequest(String boardId, final MoveDTO moveDTO) throws JsonProcessingException {
        return HttpRequest.newBuilder()
                .method("PATCH", HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(moveDTO)))
                .header("Content-Type","application/json")
                .uri(URI.create(SERVER_URL+boardId+"/makeMove"))
                .build();
    }
}
