package com.shavneva.billingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BillingServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServerApplication.class, args);
    }

}
