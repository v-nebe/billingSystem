package com.shavneva.billingserver.service.impl.businesslogic;

import com.shavneva.billingserver.externals.SmsRequest;
import com.shavneva.billingserver.service.ISmsServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsServiceProvider implements ISmsServiceProvider {
    private final RestTemplate restTemplate;
    private final String smsSenderServerUrl;
    private final String sendSmsEndpoint;

    @Autowired
    public SmsServiceProvider(RestTemplate restTemplate, @Value("${application.sms.sender.server.url}") String smsSenderServerUrl,
                              @Value("${application.sms.sender.server.send-sms-endpoint}") String sendSmsEndpoint) {
        this.restTemplate = restTemplate;
        this.smsSenderServerUrl = smsSenderServerUrl;
        this.sendSmsEndpoint = sendSmsEndpoint;
    }

    @Override
    public void sendSms(String phoneNumber, String message){
        String url = smsSenderServerUrl + sendSmsEndpoint;

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
