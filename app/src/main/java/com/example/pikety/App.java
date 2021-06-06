package com.example.pikety;

import android.app.Application;
import android.content.Context;

import com.example.pikety.api.PiketSession;

import me.grishka.appkit.utils.V;

public class App extends Application {
    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=getApplicationContext();
        V.setApplicationContext(applicationContext);
        PiketSession.load();
    }
}