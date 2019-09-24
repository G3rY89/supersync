package com.ks.supersync.model.supersync;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "SyncProcesses")
public class SyncProcesses {

    @XmlElement(name = "Processes")
    public List<Processes> processes;

    public SyncProcesses(){
        this.processes = new ArrayList<>();
    }
}