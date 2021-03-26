package com.client;

import com.model.GeolocationResponse;
import org.springframework.web.reactive.function.client.WebClient;

public class GeolocationClient {
    private static final String BASE_URL = "https://api.ip2country.info/ip?";
    private WebClient client;

    public GeolocationClient() {
        client = WebClient.create(BASE_URL);
    }

    public GeolocationResponse getGeolocationClient(String ip) {
        client = WebClient.create(BASE_URL+ip);
        return client
                .get()
                .uri("")
                .retrieve()
                .bodyToMono(GeolocationResponse.class)
                .block();
    }
}
