package com.fakebook.coopay.coolplayclientAPI.apibeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResp {
    private String token;

    public LoginResp() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;}
}
