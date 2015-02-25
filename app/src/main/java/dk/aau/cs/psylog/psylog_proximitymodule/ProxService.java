package dk.aau.cs.psylog.psylog_proximitymodule;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ProxService extends Service {

    private ProximityListener proximityListener;

    @Override
    public int onStartCommand(Intent intent, int flag, int startid)
    {
        proximityListener.startSensor();

        //Skal være START_STICKY hvis servicen skal køre hele tiden, selv hvis den bliver dræbt. START_NOT_STICKY hjælper når man programmere.
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        proximityListener = new ProximityListener(this);
    }

    @Override
    public void onDestroy(){
        //Make sure to stop the sensors that have started
        proximityListener.stopSensor();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
