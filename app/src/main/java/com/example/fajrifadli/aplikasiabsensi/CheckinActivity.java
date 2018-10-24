package com.example.fajrifadli.aplikasiabsensi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.model.LatLng;
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
    LocationManager locationManager;
    Context mContext;
    double latitude;
    double longitude;
    float[] distances;
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
        mContext=this;
        locationManager=(LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates( LocationManager.GPS_PROVIDER,
                    2000,
                    10, locationListenerGPS);
        } catch (SecurityException s){
            isLocationEnabled();
        }
//        buttonAbsen = findViewById(R.id.buttonAbsen);
//        buttonAbsen.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(distances[0] < 500) {
////                try {
////                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
////                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
////                    longitude = location.getLongitude();
////                    latitude = location.getLatitude();
////                } catch (SecurityException e){
////                    Toast.makeText(getApplicationContext(), "Please allow GPS access", Toast.LENGTH_LONG).show();
////                }
////                LatLng currentLoc = new LatLng(latitude, longitude);
////                LatLng kantor = new LatLng(-6.3543025, 106.8441696);
////                distances = new float[1];
////                Location.distanceBetween(kantor.latitude, kantor.longitude,
////                        currentLoc.latitude, currentLoc.longitude,
////                        distances);
//                    mDatabase.child(name).child(timeMonth).child("Waktu Datang").setValue(timeHour);
//                    Toast.makeText(getApplicationContext(), "Absen Sukses", Toast.LENGTH_LONG).show();
//                    //Toast.makeText(getApplicationContext(), "Distance: " + distances[0], Toast.LENGTH_LONG).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Anda belum berada di kantor", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
    LocationListener locationListenerGPS=new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            latitude=location.getLatitude();
            longitude=location.getLongitude();
            LatLng kantor = new LatLng(-6.3545159, 106.8416384);
            LatLng currentLoc = new LatLng(latitude, longitude);
            distances = new float[1];
            Location.distanceBetween(kantor.latitude, kantor.longitude,
                    currentLoc.latitude, currentLoc.longitude,
                    distances);
            buttonAbsen = findViewById(R.id.buttonAbsen);
            buttonAbsen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(distances[0] < 500) {
//                try {
//                    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                    longitude = location.getLongitude();
//                    latitude = location.getLatitude();
//                } catch (SecurityException e){
//                    Toast.makeText(getApplicationContext(), "Please allow GPS access", Toast.LENGTH_LONG).show();
//                }
//                LatLng currentLoc = new LatLng(latitude, longitude);
//                LatLng kantor = new LatLng(-6.3543025, 106.8441696);
//                distances = new float[1];
//                Location.distanceBetween(kantor.latitude, kantor.longitude,
//                        currentLoc.latitude, currentLoc.longitude,
//                        distances);
                        mDatabase.child(name).child(timeMonth).child("Waktu Datang").setValue(timeHour);
                        Toast.makeText(getApplicationContext(), "Absen Sukses", Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), "Distance: " + distances[0], Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Anda belum berada di kantor", Toast.LENGTH_LONG).show();
                    }
                }
            });
//            Toast.makeText(mContext, "Distance: " + distances[0], Toast.LENGTH_LONG).show();
//            String msg="New Latitude: "+latitude + "New Longitude: "+longitude;
//            Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    protected void onResume(){
        super.onResume();
        isLocationEnabled();
    }

    private void isLocationEnabled() {

        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Enable Location");
            alertDialog.setMessage("Your locations setting is not enabled. Please enabled it in settings menu.");
            alertDialog.setPositiveButton("Location Settings", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            AlertDialog alert=alertDialog.create();
            alert.show();
        }
        else{
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Confirm Location");
            alertDialog.setMessage("Your Location is enabled, please enjoy");
            alertDialog.setNegativeButton("Back to interface",new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            AlertDialog alert=alertDialog.create();
            alert.show();
        }
    }
}
