package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Initialize DB
        final DBHelperClass dbHelperClass = new DBHelperClass(getApplicationContext());

        SharedPreferences sharedPreferences = getSharedPreferences("GLOBAL_SHARED_PREFERENCES", MODE_PRIVATE);
        String username = sharedPreferences.getString("SAVED_USER", null);
        String password = sharedPreferences.getString("SAVED_PASSWORD", null);

        Intent intent;
        if(username != null && password!= null) {
            intent = new Intent(getApplicationContext(), UserActivity.class);
        } else {
            //StartLoginActivity
            intent = new Intent(getApplicationContext(), LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }
}