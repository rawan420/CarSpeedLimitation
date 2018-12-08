package com.alifarouk.carspeedlimit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SpeedDisplay extends AppCompatActivity {

    protected TextView speedTV;
    protected Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_display);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeedDisplay.this.finish();
            }
        });

        speedTV = findViewById(R.id.sppedTV);
        float speed = getIntent().getExtras().getFloat("speed", 0.0f);

        if (speed > MainActivity.maxSpeed) {

            speedTV.setText("Your Speed: " + speed + "Slowdown !!");
//            Toast.makeText(MainActivity.this, "Your Speed: " + currentSpeed + "Slowdown !!", Toast.LENGTH_LONG).show();

        } else {
            speedTV.setText("Your Speed: " + speed + "You can go on !!");
//            Toast.makeText(MainActivity.this, "Your Speed: " + currentSpeed + "You can go on !!", Toast.LENGTH_LONG).show();

        }



    }

}
