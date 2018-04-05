package com.fakebook.coopay.coolplayclientAPI.apibeans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentReqResp {

    private Payment payment;

    public PaymentReqResp() {
    }

    public PaymentReqResp(Payment payment) {
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
