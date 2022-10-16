package com.example.pat_1;

//Alarm class has all the variables we need to save for the alarms
public class Alarm implements java.io.Serializable{

    public float minTemp, maxTemp, minLum, maxLum, minHum, maxHum;
    public boolean TempSwitch, LumSwitch, HumSwitch, energy_saver;

    //Initialize all the variables with default values
    public Alarm() {
        minTemp = -273.1f;
        maxTemp = 100f;
        minLum = 0f;
        maxLum = 40000f;
        minHum = 0f;
        maxHum = 100f;
        TempSwitch = false;
        LumSwitch = false;
        HumSwitch = false;
        energy_saver=false;
    }

}
