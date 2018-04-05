package com.fakebook.coopay.coolplayclientAPI.apibeans;

import org.springframework.beans.factory.annotation.Value;

public class User {

    //@Value("${coolpay-username}")
    private String username ="RicardoH";

    //@Value("${coolpay-apikey}")
    private String apiKey="942B9A7505B687FF";

    private String token;

    public String getUsername() {
        return username;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getToken() {
        return token;
    }

    public void updateToken(String token){
        this.token = token;
    }
}
