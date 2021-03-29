package com.service;

import com.client.CountryInformationClient;
import com.client.CurrencyInformationClient;
import com.client.GeolocationClient;
import com.model.response.CountryInformationResponse;
import com.model.response.GeolocationResponse;
import com.model.response.IpInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IpServiceTest {

    @InjectMocks
    private IpService ipService;

    @Mock
    private CountryInformationClient countryInformationClient;

    @Mock
    private CurrencyInformationClient currencyInformationClient;

    @Mock
    private GeolocationClient geolocationClient;

    @Test
    public void testGetIpInformation() throws Exception {
        String ip = "192.168.1.1";

        GeolocationResponse geolocationResponse = new GeolocationResponse();
        geolocationResponse.setCountryName("Uruguay");
        geolocationResponse.setCountryCode("uy");

        when(geolocationClient.getGeolocationClient(eq(ip))).thenReturn(geolocationResponse);

        IpInformation info = ipService.getInformation(ip);

        assertEquals("Uruguay", info.getCountry());
    }
}
