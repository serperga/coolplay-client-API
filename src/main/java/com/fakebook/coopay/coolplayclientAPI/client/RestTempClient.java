package com.fakebook.coopay.coolplayclientAPI.client;

import com.fakebook.coopay.coolplayclientAPI.apibeans.*;
import com.fakebook.coopay.coolplayclientAPI.exception.CoolpayApiErrorException;
import com.fakebook.coopay.coolplayclientAPI.exception.CoolpayLoginErrorException;
import com.fakebook.coopay.coolplayclientAPI.exception.CoolpayTokenErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RestTempClient implements RestTempClientAPI{

    //@Value("${coolpay-host}")
    private String host = RestTempEndPoints.HOST.getEndpoint();

    private RestTemplate restTemplate;

    @Autowired
    public RestTempClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String login(String username, String apiKey){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        final HttpEntity<LoginReq> request = new HttpEntity<>(new LoginReq(username, apiKey), headers);;
        try {
            final ResponseEntity<LoginResp> loginResponse = restTemplate.postForEntity(
                    host + RestTempEndPoints.LOGIN_API.getEndpoint(),
                    request,
                    LoginResp.class);
            return loginResponse.getBody().getToken();
        } catch (Exception e) {
            throw new CoolpayLoginErrorException("Invalid username or ApiKey ", e);
        }
    }

    @Override
    public List<Recipient> getRecipientList(String authorizationToken){
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", "Bearer " + authorizationToken);
        final HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        try {
            final ResponseEntity<RecipientListResp> result = restTemplate.exchange(
                    host + RestTempEndPoints.RECIPIENT_API.getEndpoint(),
                    HttpMethod.GET,
                    entity,
                    RecipientListResp.class);
            return result.getBody().getRecipients();
        } catch (Exception e){
            if (tokenError(e)){
                throw new CoolpayTokenErrorException(e);
            } else {
                throw new CoolpayApiErrorException(String.format("Getting recipient error. Token: %s, name: %s", authorizationToken), e);
            }
        }
    }

    private boolean tokenError(Exception e) {
        if(e instanceof HttpClientErrorException){
            final HttpStatus statusCode = ((HttpClientErrorException) e).getStatusCode();
            if(statusCode.equals(HttpStatus.UNAUTHORIZED)){
                return true;
            }
        }
        return false;
    }

    public Recipient createRecipient(String authorizationToken, String name) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", "Bearer " + authorizationToken);
        final HttpEntity<RecipientReqResp> request = new HttpEntity<>(new RecipientReqResp(new Recipient(name)), headers);
        try {
            final ResponseEntity<RecipientReqResp> result = restTemplate.postForEntity(
                    host + RestTempEndPoints.RECIPIENT_API.getEndpoint(),
                    request,
                    RecipientReqResp.class);
            return result.getBody().getRecipient();
        } catch (Exception e) {
            if (tokenError(e)) {
                throw new CoolpayTokenErrorException(e);
            } else {
                throw new CoolpayApiErrorException(String.format("Adding recipient error. Token:%s, name: %s", authorizationToken, name), e);
            }
        }
    }

    //@Override
    public Payment createPayment(String authorizationToken, String recipientId, BigDecimal paymentValue, String currency){
        final PaymentReqResp paymentRequestResponse = new PaymentReqResp(new Payment(paymentValue,
                currency,
                recipientId));
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authorizationToken);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        final HttpEntity<PaymentReqResp> requestPayment = new HttpEntity<>(paymentRequestResponse,
                headers);
        try {
            final ResponseEntity<PaymentReqResp> result = restTemplate.postForEntity(
                    host + RestTempEndPoints.PAYMENTS_API.getEndpoint(),
                    requestPayment,
                    PaymentReqResp.class);
            return result.getBody().getPayment();
        } catch (Exception e){
            if (tokenError(e)){
                throw new CoolpayTokenErrorException(e);
            } else {
                throw new CoolpayApiErrorException(String.format("Adding payment error. Token: %s, recipientId: %s, value: %s, currency: %s", authorizationToken, recipientId, paymentValue, currency), e);
            }
        }
    }

    @Override
    public List<Payment> getPaymentList(String authorizationToken) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Authorization", "Bearer " + authorizationToken);
        final HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        try {
            final ResponseEntity<PaymentListResp> result = restTemplate.exchange(
                    host + RestTempEndPoints.PAYMENTS_API.getEndpoint(),
                    HttpMethod.GET,
                    entity,
                    PaymentListResp.class);
            return result.getBody().getPayments();
        } catch (Exception e){
            if (tokenError(e)){
                throw new CoolpayTokenErrorException(e);
            } else {
                throw new CoolpayApiErrorException(String.format("Getting payments error. Token: %s", authorizationToken), e);
            }
        }
    }

}
