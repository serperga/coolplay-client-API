package com.fakebook.coopay.coolplayclientAPI.apibeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipientReqResp {

    private Recipient recipient;

    public RecipientReqResp() {
    }

    public RecipientReqResp(Recipient recipient) {
        this.recipient = recipient;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
}