package com.client;

import org.springframework.web.reactive.function.client.WebClient;

public class GeolocationClient {
    private static final String BASE_URL = "https://api.ip2country.info/ip?";
    private WebClient client;

    public GeolocationClient() {
        client = WebClient.create(BASE_URL);
    }

    public String getGeolocationClient(String ip) {
        client = WebClient.create(BASE_URL+ip);
        return client
                .get()
                .uri("")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
