package dk.aau.cs.psylog.psylog_proximitymodule;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import dk.aau.cs.psylog.module_lib.SuperService;

public class ProxService extends SuperService {
    @Override
    public void onCreate(){
        super.onCreate();
        sensor = new ProximityListener(this);
    }
}
