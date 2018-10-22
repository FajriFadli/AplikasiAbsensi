package com.example.fajrifadli.aplikasiabsensi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckinActivity extends AppCompatActivity {
    float radius = 50;
    Button buttonAbsen;
    GoogleSignInAccount account;
    String name;
    SimpleDateFormat simpledateformat;
    Calendar calendar;
    String date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        account = GoogleSignIn.getLastSignedInAccount(this);
        calendar = Calendar.getInstance();
        simpledateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        date = simpledateformat.format(calendar.getTime());
        if(account != null){
            name = account.getDisplayName();
        }
        buttonAbsen = findViewById(R.id.buttonAbsen);
        buttonAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), name + " " + date, Toast.LENGTH_LONG).show();
            }
        });
    }
}
