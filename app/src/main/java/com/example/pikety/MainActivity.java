package com.example.pikety;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.pikety.api.PiketSession;
import com.example.pikety.fragments.HomeFragment;
import com.example.pikety.fragments.LoginFragment;
import com.yandex.mapkit.MapKitFactory;


public class MainActivity extends AppCompatActivity {
    FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fTrans = getFragmentManager().beginTransaction();
        if (PiketSession.isLoggedIn()) {
            fTrans.add(R.id.frgmCont, new HomeFragment());
        } else {
            fTrans.add(R.id.frgmCont, new LoginFragment());
        }
        fTrans.commit();

    }
}