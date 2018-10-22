package com.example.fajrifadli.aplikasiabsensi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckinActivity extends AppCompatActivity {
    float radius = 50;
    Button buttonAbsen;
    GoogleSignInAccount account;
    String name;
    SimpleDateFormat hour;
    SimpleDateFormat month;
    Calendar calendar;
    String timeHour;
    String timeMonth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        account = GoogleSignIn.getLastSignedInAccount(this);
        calendar = Calendar.getInstance();
        hour = new SimpleDateFormat("HH:mm:ss");
        month = new SimpleDateFormat("dd-MM-yyyy");
        timeHour = hour.format(calendar.getTime());
        timeMonth = month.format((calendar.getTime()));
        if(account != null){
            name = account.getDisplayName();
        }
        buttonAbsen = findViewById(R.id.buttonAbsen);
        buttonAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.child(name).child(timeMonth).child("Waktu Datang").setValue(timeHour);
                Toast.makeText(getApplicationContext(), "Absen Sukses", Toast.LENGTH_LONG).show();
            }
        });
    }
}
