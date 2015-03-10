package dk.aau.cs.psylog.proximity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.util.Log;
import dk.aau.cs.psylog.module_lib.ISensor;

public class ProximityListener implements SensorEventListener, ISensor {

    private SensorManager mSensorManager;
    private Sensor mSensor;

    private int sensorDelay;

    private ContentResolver resolver;

    public ProximityListener(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        resolver = context.getContentResolver();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float x = sensorEvent.values[0];
            Uri uri = Uri.parse("content://dk.aau.cs.psylog.psylog" + "/proximity");
            ContentValues values = new ContentValues();
            values.put("inProximity", (int)x);
            resolver.insert(uri, values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void startSensor() {
        mSensorManager.unregisterListener(this);
        mSensorManager.registerListener(this,mSensor,sensorDelay);
    }

    public void stopSensor() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void sensorParameters(Intent intent) {
        sensorDelay = intent.getIntExtra("sensorDelay",SensorManager.SENSOR_DELAY_NORMAL);
    }
}
