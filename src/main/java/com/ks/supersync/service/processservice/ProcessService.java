package com.ks.supersync.service.processservice;

import com.ks.supersync.model.supersync.Processes;

public interface ProcessService {

    Processes getProcesses(String webIdentifier, String webPassword); 

}