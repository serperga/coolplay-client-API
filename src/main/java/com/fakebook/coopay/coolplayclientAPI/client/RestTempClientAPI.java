package com.fakebook.coopay.coolplayclientAPI.client;

import com.fakebook.coopay.coolplayclientAPI.apibeans.Payment;
import com.fakebook.coopay.coolplayclientAPI.apibeans.Recipient;

import java.math.BigDecimal;
import java.util.List;

public interface RestTempClientAPI {

    String login(String username, String apiKey);

    List<Recipient> getRecipientList(String token);

    Recipient createRecipient(String token, String name);

    List<Payment> getPaymentList(String authorizationToken);

    Payment createPayment(String authorizationToken, String recipientId, BigDecimal paymentValue, String currency);

}
