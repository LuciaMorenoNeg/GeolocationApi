package com.client;

import com.exception.ApiException;
import com.model.response.CountryInformationResponse;
import com.model.response.CurrencyInformationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CurrencyInformationClient {
    private static final String BASE_URL = "http://data.fixer.io/api/latest?access_key=af6bf6b2ca43883354e525e154d7c909";
    private WebClient client;

    public CurrencyInformationClient() {
         client = WebClient.create(BASE_URL);
    }

    public CurrencyInformationResponse getCurrencyInformation(){
        return client
                .get()
                .uri("")
                .exchangeToMono(response -> {
                    if (!response.statusCode().equals(HttpStatus.OK)) {
                        return Mono.error(new ApiException(response.statusCode().value(), "Error getting country information", null));
                    }
                    return response.bodyToMono(CurrencyInformationResponse.class);
                })
                .block();
    }


}
