package com.market.backend.util;

public class GoodResponse {
    private String message;

    public GoodResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
