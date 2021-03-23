package com.service;

import com.client.CurrencyInformationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpService {
    private final CurrencyInformationClient currencyInformationClient;

    @Autowired
    public IpService(CurrencyInformationClient currencyInformationClient) {
        this.currencyInformationClient = currencyInformationClient;
    }
}
