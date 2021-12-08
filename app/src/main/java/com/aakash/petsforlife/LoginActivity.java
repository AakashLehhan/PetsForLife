package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelperClass dbHelperClass = new DBHelperClass(getApplicationContext());
                if(dbHelperClass.matchUser(username.getText().toString(), password.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Not exist", Toast.LENGTH_SHORT).show();
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