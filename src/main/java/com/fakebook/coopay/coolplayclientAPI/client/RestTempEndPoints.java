package com.fakebook.coopay.coolplayclientAPI.client;

public enum RestTempEndPoints {
    HOST("https://coolpay.herokuapp.com"),
    LOGIN_API("/api/login"),
    RECIPIENT_API("/api/recipients"),
    PAYMENTS_API("/api/payments");

    private String endpoint;

    RestTempEndPoints(String endpoint){
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
