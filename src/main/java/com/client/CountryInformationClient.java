package com.client;

import com.exception.ApiException;
import com.model.response.CountryInformationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;

@Service
public class CountryInformationClient {
    private static final String BASE_URL = "https://restcountries.eu/rest/v2/alpha/";
    private WebClient client;

    public CountryInformationClient() {
        client = WebClient.create(BASE_URL);
    }

    public CountryInformationResponse getCountryInformation(String countryName)  {
        return client
                .get()
                .uri(countryName)
                .exchangeToMono(response -> {
                    if (!response.statusCode().equals(HttpStatus.OK)) {
                        return Mono.error(new ApiException(response.statusCode().value(), "Error getting country information", null));
                    }
                    return response.bodyToMono(CountryInformationResponse.class);
                })
                .block();
    }

}
