package com.example.fajrifadli.aplikasiabsensi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminSigninActivity extends AppCompatActivity {
    EditText passwordBox;
    String password;
    Button loginButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_signin);
        passwordBox = findViewById(R.id.passwordBox);
        loginButton = findViewById(R.id.button2);
        password = passwordBox.getText().toString();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password == "abracadabra"){
                    Intent i = new Intent(AdminSigninActivity.this, AdminPanelActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
