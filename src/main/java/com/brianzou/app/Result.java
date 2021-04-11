package com.brianzou.app;

public class Result {
    private int sequence;
    private String id;
    private String response;

    public Result() {

    }

    public Result(int sequence, String id, String response) {
        this.sequence = sequence;
        this.id = id;
        this.response = response;
    }

    @Override
    public String toString() {
        return "\nsequence: " + sequence +
                ", itemId: " + id +
                ", response: " + response;
    }

}
