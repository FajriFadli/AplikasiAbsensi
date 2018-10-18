package com.example.fajrifadli.aplikasiabsensi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CheckinActivity extends AppCompatActivity {
    float radius = 50;
    Button buttonAbsen;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        buttonAbsen = findViewById(R.id.buttonAbsen);
        buttonAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
