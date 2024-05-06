package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.classes.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsServiceProvider {
    private final RestTemplate restTemplate;
    private final String smsSenderServerUrl = "http://localhost:8081/api";

    @Autowired
    public SmsServiceProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendSms(String phoneNumber, String message){
        String url = smsSenderServerUrl + "/send-sms";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SmsRequest smsRequest = new SmsRequest(phoneNumber, message);
        HttpEntity<SmsRequest> request = new HttpEntity<>(smsRequest, headers);

        ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("SMS successfully sent");
        } else {
            System.out.println("Failed to send SMS");
        }

    }
}
