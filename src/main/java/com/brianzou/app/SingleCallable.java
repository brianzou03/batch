package com.brianzou.app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.concurrent.Callable;

public class SingleCallable implements Callable<Result> {
    public static final String BASE_URL = "https://eluv.io/items/";
    HttpClient httpClient;
    private int sequence;
    private String ID;


    @Override
    public Result call() {
        String token = Base64.getEncoder().encodeToString(ID.getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL + ID))
                .setHeader("Authorization", token)
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(String.format("sequence: %d, id: %s, token: %s", sequence, ID, token));

            Result result = new Result(sequence, ID, response.body());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Result(sequence, ID, "error");

    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
