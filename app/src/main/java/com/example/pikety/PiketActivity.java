package com.example.pikety;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pikety.api.model.Picket;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

import java.util.stream.Collectors;

public class PiketActivity extends AppCompatActivity {
    private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Picket picket = (Picket) getIntent().getParcelableExtra(
        Picket.class.getCanonicalName());
        setContentView(R.layout.piket);
        super.onCreate(savedInstanceState);
        mapView = (MapView)findViewById(R.id.mapview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(picket.name);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        ((TextView) findViewById(R.id.description))
                .setText(picket.description);
        ((TextView) findViewById(R.id.position_address))
                .setText(picket.positionAddress);
        ((TextView) findViewById(R.id.company_name))
                .setText(picket.companyName);
        ((TextView) findViewById(R.id.workers))
                .setText(picket.workers.stream().map(w-> w.name).collect(Collectors.joining("\n")));
        mapView.getMap().move(
                new CameraPosition(new Point(picket.latitude, picket.longitude), 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
    }

    @Override
    protected void onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}