package com.example.pikety;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.pikety.api.*;
import com.example.pikety.fragments.*;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;

import me.grishka.appkit.FragmentStackActivity;
import me.grishka.appkit.api.Callback;
import me.grishka.appkit.api.ErrorResponse;

public class MainActivity extends FragmentStackActivity {

    private static final int PERMISSION_RESULT = 270;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        if (PiketSession.isLoggedIn()) {
            showFragment(new HomeFragment());
        } else {
            showFragment(new LoginFragment());
        }
    }
}
