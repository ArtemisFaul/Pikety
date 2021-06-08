package com.example.pikety.api;
import android.content.Context;
import android.content.SharedPreferences;
import com.example.pikety.App;
import com.example.pikety.api.model.*;
import java.util.UUID;

public class PiketSession {

    public static String deviceID, userID, userToken;
    public static User self;

    public static void load() {
        SharedPreferences prefs = prefs();
        deviceID = prefs.getString("device_id", null);
        userID = prefs.getString("user_id", null);
        userToken = prefs.getString("user_token", null);
        if (deviceID == null) {
            deviceID = UUID.randomUUID().toString().toUpperCase();
            write();
        }
    }

    public static void write() {
        prefs().edit()
                .putString("device_id", deviceID)
                .putString("user_id", userID)
                .putString("user_token", userToken)
                .apply();
    }

    public static boolean isLoggedIn() {
        return userID != null;
    }

    private static SharedPreferences prefs() {
        return App.applicationContext.getSharedPreferences("session", Context.MODE_PRIVATE);
    }
}
