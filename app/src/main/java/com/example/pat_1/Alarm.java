package com.example.pat_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Alarm extends Service {
    public Alarm() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}