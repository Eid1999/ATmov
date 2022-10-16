package com.example.pat_1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Sensors extends Service implements SensorEventListener {

    private static final String DEBUG_TAG = "Sensors";
    private SensorManager sensorManager;
    public Sensor envSense,temp , light, hum;
    public float Slight,Shum,Stemp;

    public static RepoStorage repo = new RepoStorage();
    boolean ext_detected;
    int[] repo_size =  {0, 0, 0};
    long time_millis;
    public static Alarm Horn = new Alarm();

    Context context;
    public static final String FILE_NAME = "Pat_1Storage";

    public int onStartCommand(Intent intent, int flags, int startId) {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        temp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        hum = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        sensorManager.registerListener(this, temp, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, hum, SensorManager.SENSOR_DELAY_NORMAL);

        return flags;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    public final void onSensorChanged(SensorEvent event) {

        float sensorValue = event.values[0];
        Intent intent = new Intent("Sensors");

        // Do something with this sensor data.
        String envInfo;
        int currType=event.sensor.getType();

        // REPOSITORY SECTION -> Handle event time and format it to be stored in the Repository
        time_millis = System.currentTimeMillis() + ((event.timestamp- SystemClock.elapsedRealtimeNanos())/1000000L);
        Timestamp date_time = new Timestamp(time_millis);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(date_time);

        // REPOSITORY SECTION ->
        repo_size[0] = repo.temp_repo.size();
        repo_size[1] = repo.light_repo.size();
        repo_size[2] = repo.humid_repo.size();
        RepoValues new_event = new RepoValues(time, sensorValue, time_millis);
        ext_detected = false;

        switch(currType){
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                Stemp=sensorValue;
                intent.putExtra("Stemp", Stemp);
                intent.putExtra("Class","Temp");


                // REPOSITORY SECTION -> Check if new value is higher than all time high
                if (sensorValue > repo.max_temp.value){
                    repo.max_temp = new_event;
                    ext_detected = true;
                }
                if (sensorValue < repo.min_temp.value){
                    repo.min_temp = new_event;
                    ext_detected = true;
                }

                // REPOSITORY SECTION -> Store new event and delete oldest one
                if (repo_size[0] == 0 || time_millis >= repo.temp_repo.get(repo_size[0] - 1).time_millis + 5000 || ext_detected){

                    //check if the temp alarm is On
                    if (Horn.TempSwitch) {
                        if (sensorValue < Horn.minTemp) { //check if the sensor value surpasses the minTemp threshold defined by the user
                            onAlarm("minT");
                        } else if (sensorValue > Horn.maxTemp){ //check if the sensor value surpasses the maxTemp threshold defined by the user
                            onAlarm("maxT");
                        }
                    }

                    if (repo_size[0] >= 10){
                        repo.temp_repo.remove(0);
                    }

                    repo.temp_repo.add(new_event);
                }


                break;

            case Sensor.TYPE_LIGHT:
                Slight=sensorValue;
                intent.putExtra("Slight", Slight);
                intent.putExtra("Class","Light");



                // REPOSITORY SECTION -> Check if new value is higher than all time high
                if (sensorValue > repo.max_light.value){
                    repo.max_light = new_event;
                    ext_detected = true;
                }
                if (sensorValue < repo.min_light.value){
                    repo.min_light = new_event;
                    ext_detected = true;
                }

                // REPOSITORY SECTION -> Store new event and delete oldest one
                if (repo_size[1] == 0 || time_millis >= repo.light_repo.get(repo_size[1] - 1).time_millis + 5000 || ext_detected){

                    //check if the temp alarm is On
                    if (Horn.LumSwitch) {
                        if (sensorValue < Horn.minLum) { //check if the sensor value surpasses the minLum threshold defined by the user
                            onAlarm("minL");
                        } else if (sensorValue > Horn.maxLum){ //check if the sensor value surpasses the maxLum threshold defined by the user
                            onAlarm("maxL");
                        }
                    }

                    if (repo_size[1] >= 10){
                        repo.light_repo.remove(0);
                    }

                    repo.light_repo.add(new_event);
                }

                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                Shum=sensorValue;
                intent.putExtra("Shum", Shum);
                intent.putExtra("Class","Humidity");


                // REPOSITORY SECTION -> Check if new value is higher than all time high
                if (sensorValue > repo.max_humid.value){
                    repo.max_humid = new_event;
                    ext_detected = true;
                }
                if (sensorValue < repo.min_humid.value){
                    repo.min_humid = new_event;
                    ext_detected = true;
                }

                // REPOSITORY SECTION -> Store new event and delete oldest one
                if (repo_size[2] == 0 || time_millis >= repo.humid_repo.get(repo_size[2] - 1).time_millis + 5000 || ext_detected){

                    //check if the temp alarm is On
                    if (Horn.HumSwitch) {
                        if (sensorValue < Horn.minHum) { //check if the sensor value surpasses the minHum threshold defined by the user
                            onAlarm("minH");
                        } else if (sensorValue > Horn.maxHum){ //check if the sensor value surpasses the maxHum threshold defined by the user
                            onAlarm("maxH");
                        }
                    }

                    if (repo_size[2] >= 10){
                        repo.humid_repo.remove(0);
                    }

                    repo.humid_repo.add(new_event);
                }

                break;
            default: break;


        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }

    //Messages if a sensor value surpasses the threshold value defined by the user
    public void onAlarm (String type) {
        String accuracyMsg = "";
        switch(type){
            case "minT":
                accuracyMsg="Minimum temperature threshold reached!";
                break;
            case "maxT":
                accuracyMsg="Maximum temperature threshold reached!";
                break;
            case "minH":
                accuracyMsg="Minimum humidity threshold reached!";
                break;
            case "maxH":
                accuracyMsg="Maximum humidity threshold reached!";
                break;
            case "minL":
                accuracyMsg="Minimum luminosity threshold reached!";
                break;
            case "maxL":
                accuracyMsg="Maximum luminosity threshold reached!";
                break;
            default:
                break;
        }
        Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
        accuracyToast.show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        String accuracyMsg = "";
        switch(accuracy){
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                accuracyMsg="Sensor has high accuracy";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                accuracyMsg="Sensor has medium accuracy";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                accuracyMsg="Sensor has low accuracy";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                accuracyMsg="Sensor has unreliable accuracy";
                break;
            default:
                break;
        }
        Toast accuracyToast = Toast.makeText(this.getApplicationContext(), accuracyMsg, Toast.LENGTH_SHORT);
        accuracyToast.show();
    }

    public void onDestroy(){
        writeData();
        sensorManager.unregisterListener(this);
        this.stopSelf();
    }

    //if user closes the app write all the data in a file and stop the sensors service
    public void onTaskRemoved (Intent rootIntent) {
        writeData();
        sensorManager.unregisterListener(this);
        this.stopSelf();
    }

    //Method used to write the data into a file
    public void writeData () {
        try  {
            FileOutputStream fos = openFileOutput(FILE_NAME,MODE_PRIVATE);
            ObjectOutputStream Objfos = new ObjectOutputStream (fos);
            Object [] storeList = new Object [2]; //create a list with the objects that we want to save
            storeList [0] = repo;                 //(its better to create a list because now we know exactly how and where the data is saved)
            storeList [1] = Horn;
            Objfos.writeObject(storeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method used to read the data of a file
    public Object readData() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            return new ObjectInputStream(fis).readObject(); //If we can read something return the object read, else return null.
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}




