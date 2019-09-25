package com.ks.supersync.service.activationservice;

import com.ks.supersync.model.supersync.Result;

public interface ActivationService {

    Result enrollUser(String webIdentifier, String webShop, String apiKey, String currency, String language);
}