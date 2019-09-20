package com.ks.supersync.service.processservice;

import com.ks.supersync.model.supersync.Processes;
import com.ks.supersync.repository.ProcessRepository;
import com.ks.supersync.repository.SuperSyncUserRepository;

import org.springframework.stereotype.Service;

@Service
public class ProcessServiceImpl implements ProcessService {

    private SuperSyncUserRepository superSyncUserRepository;
    private ProcessRepository processRepository;

    public ProcessServiceImpl(SuperSyncUserRepository service, ProcessRepository service1){
        this.processRepository = service1;
        this.superSyncUserRepository = service;
    }

    @Override
    public Processes getProcesses(String webIdentifier, String webPassword) {
        
        if(superSyncUserRepository.findByWebIdentifierAndWebPassword(webIdentifier, webPassword) != null){
            return processRepository.findByapiKey(webIdentifier);
        }
        return null;
    }

}