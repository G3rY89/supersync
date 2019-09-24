package com.ks.supersync.controller;

import com.ks.supersync.model.supersync.Processes;
import com.ks.supersync.model.supersync.SyncProcesses;
import com.ks.supersync.service.processservice.ProcessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ProcessController{

    @Autowired
    ProcessService processService;

    @RequestMapping(value = "/processes", method = RequestMethod.GET, produces = "application/xml")
    public SyncProcesses getProcesses(@RequestHeader("webIdentifier") String webIdentifier, @RequestHeader("webPassword") String webPassword,  @RequestHeader("apiKey") String apiKey){
            return processService.getProcesses(webIdentifier, webPassword, apiKey);
    } 
}