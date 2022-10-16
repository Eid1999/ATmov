package com.example.pat_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ExitService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);

        //if user closes the app write all the data in a file
        super.onTaskRemoved(rootIntent);
        writeData();

        //stop service
        this.stopSelf();
    }

    //Method used to write the data into a file
    public void writeData() {
        try  {
            FileOutputStream fos = openFileOutput(MainActivity.FILE_NAME,MODE_PRIVATE);
            ObjectOutputStream Objfos = new ObjectOutputStream (fos);
            Object [] storeList = new Object [2]; //create a list with the objects that we want to save
            storeList [0] = MainActivity.repo;                 //(its better to create a list because now we know exactly how and where the data is saved)
            storeList [1] = MainActivity.Horn;
            Objfos.writeObject(storeList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
