package com.fakebook.coopay.coolplayclientAPI.apibeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginReq {
    private final String username;
    private final String apikey;

    public LoginReq(String username, String apikey) {
        this.username = username;
        this.apikey = apikey;
    }

    public String getUsername() {
        return username;
    }

    public String getApikey() {
        return apikey;
    }
}
