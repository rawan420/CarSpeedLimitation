package com.alifarouk.carspeedlimit;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {

    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected EditText maxSpeedET;
    protected Button okBtn;

    protected Location previousLocation;
    protected static float maxSpeed = 0.0f;
    protected Long previousTime = 0L;
    protected float currentSpeed = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                boolean hasPrevious = true;
                if (previousLocation == null) {
                    hasPrevious = false;
                }

                Long currentTime = System.currentTimeMillis();
                if (hasPrevious) {
                    Long elapsedTime = (currentTime - previousTime);
                    currentSpeed = location.distanceTo(previousLocation) / elapsedTime;
                    currentSpeed = (currentSpeed* 18 )/5;

                    Intent intent = new Intent(MainActivity.this, SpeedDisplay.class);
                    intent.putExtra("speed", currentSpeed);
                    startActivity(intent);
                }

                previousLocation = location;
                previousTime = currentTime;
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };

        maxSpeedET = findViewById(R.id.maxSpeedET);

        okBtn = findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!maxSpeedET.getText().toString().equals("")) {
                    maxSpeed = Float.parseFloat(maxSpeedET.getText().toString());

                    if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{(android.Manifest.permission.ACCESS_FINE_LOCATION)}, 1);
                    } else {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2, 2f, locationListener);
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Please, Enter the max speed !!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults.length > 0) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2, 2f, locationListener);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
