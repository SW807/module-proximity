package dk.aau.cs.psylog.psylog_proximitymodule;

import dk.aau.cs.psylog.module_lib.SuperService;

public class Service extends SuperService {
    @Override
    public void setSensor() {
        sensor = new ProximityListener(this);
    }
}