package com.client;

import com.model.CurrencyInformationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CurrencyInformationClient {
    private static final String BASE_URL = "http://data.fixer.io";
    private WebClient client;

    public CurrencyInformationClient() {
         client = WebClient.create(BASE_URL);
    }

    public CurrencyInformationResponse getCurrencyInformation() {
        return client
                .get()
                .uri("/api/latest?access_key=af6bf6b2ca43883354e525e154d7c909")
                .retrieve()
                .bodyToMono(CurrencyInformationResponse.class)
                .block();
    }
}
