
package com.example.myandroid.app;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

public class MyIntentService extends IntentService {

    public MyIntentService(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        // TODO Auto-generated method stub
        super.setIntentRedelivery(enabled);
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return super.onBind(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // TODO Auto-generated method stub
        long endTime = System.currentTimeMillis() + 5 * 1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
        }
    }

}
