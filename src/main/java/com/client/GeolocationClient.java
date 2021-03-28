package com.client;

import com.model.response.GeolocationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
                .retrieve()
                .bodyToMono(GeolocationResponse.class)
                .block();
    }
}
