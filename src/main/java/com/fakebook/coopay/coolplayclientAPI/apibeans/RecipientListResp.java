package com.fakebook.coopay.coolplayclientAPI.apibeans;

import java.util.List;

public class RecipientListResp {

    private List<Recipient> recipients;

    public RecipientListResp() {
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<Recipient> recipients) {
        this.recipients = recipients;
    }
}