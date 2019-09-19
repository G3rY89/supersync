package com.ks.supersync.controller;

import com.ks.supersync.model.supersync.Result;
import com.ks.supersync.service.activationservice.ActivationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ActivationController {

    @Autowired
    ActivationService activationService;

    @RequestMapping(value = "/activate_webshop", method = RequestMethod.POST, produces = "application/xml")
    public Result activateWebshop(@RequestHeader("WebIdentifier") String webIdentifier, @RequestHeader("Webshop") String webshop, @RequestHeader("ApiKey") String apiKey){
            return activationService.enrollUser(webIdentifier, webshop, apiKey);
    } 
}