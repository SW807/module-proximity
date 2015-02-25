package dk.aau.cs.psylog.psylog_proximitymodule;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class ProximityListener implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;

    public ProximityListener(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            float x = sensorEvent.values[0];

            Log.i("AccReadings", "x:" + x);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void startSensor() {
        mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void stopSensor() {
        mSensorManager.unregisterListener(this);
    }
}
