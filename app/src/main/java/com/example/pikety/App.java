package com.example.pikety;

import android.app.Application;
import android.content.Context;

import com.example.pikety.api.PiketSession;
import com.yandex.mapkit.MapKitFactory;

public class App extends Application {
    public static Context applicationContext;
    private final String MAPKIT_API_KEY = "8051749a-5eef-4d2d-9a78-5e09bdaaa54a";
    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext=getApplicationContext();
        PiketSession.load();
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);
    }
}
