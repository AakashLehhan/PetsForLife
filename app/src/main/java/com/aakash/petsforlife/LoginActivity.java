package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    Button login;
    CheckBox stayLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        stayLogged = findViewById(R.id.stay_logged);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelperClass dbHelperClass = new DBHelperClass(getApplicationContext());
                if(dbHelperClass.matchUser(username.getText().toString(), password.getText().toString())) {
                    if(stayLogged.isChecked()) {
                        SharedPreferences sharedPreferences = getSharedPreferences("GLOBAL_SHARED_PREFERENCES", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("SAVED_USER", username.getText().toString());
                        editor.putString("SAVED_PASSWORD", password.getText().toString());
                        editor.apply();
                    }

                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);

                    finish();
                } else {
                    LinearLayout parentLayout = findViewById(R.id.parentLayout);
                    Snackbar snackbar = Snackbar.make(parentLayout, "Incorrect details!", Snackbar.LENGTH_INDEFINITE);
                    snackbar.setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    snackbar.setBackgroundTint(getResources().getColor(R.color.black_shade_1));
                    snackbar.setTextColor(getResources().getColor(R.color.grey_shade));
                    snackbar.show();
                }
            }
        });

        /*----- Link for admin login -----*/
        TextView adminLogin = findViewById(R.id.admin_login);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(intent);
            }
        });

        /*----- Link to new sign up -----*/
        TextView signUp = findViewById(R.id.new_signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        /*----- Forgot Password -----*/
        ImageView forgotPassword = findViewById(R.id.forgot_password);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}