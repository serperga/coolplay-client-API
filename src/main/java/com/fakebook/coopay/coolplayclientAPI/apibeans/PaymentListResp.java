package com.fakebook.coopay.coolplayclientAPI.apibeans;

import java.util.List;

public class PaymentListResp {

    private List<Payment> payments;

    public PaymentListResp() {
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}