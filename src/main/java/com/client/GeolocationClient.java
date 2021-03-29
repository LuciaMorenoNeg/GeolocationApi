package com.client;

import com.exception.ApiException;
import com.model.response.GeolocationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeolocationClient {
    private static final String BASE_URL = "https://api.ip2country.info/ip";
    private WebClient client;

    public GeolocationClient() {
        client = WebClient.create(BASE_URL);
    }

    public GeolocationResponse getGeolocationClient(String ip) {
        return client
                .get()
                .uri("?"+ip)
                .exchangeToMono(response -> {
                    if (!response.statusCode().equals(HttpStatus.OK)) {
                        return Mono.error(new ApiException(response.statusCode().value(), "Error getting country information", null));
                    }
                    return response.bodyToMono(GeolocationResponse.class);
                })
                .block();
    }

}
