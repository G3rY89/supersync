package com.ks.supersync.service.processservice;

import com.ks.supersync.model.supersync.SyncProcesses;

public interface ProcessService {

    SyncProcesses getProcesses(String webIdentifier, String webPassword, String apiKey); 

}