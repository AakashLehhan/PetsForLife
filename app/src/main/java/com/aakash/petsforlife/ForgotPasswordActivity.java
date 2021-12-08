package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText username, password, passwordVerify, numVerify;
    Button changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        username = findViewById(R.id.username);
        password = findViewById(R.id.new_password);
        passwordVerify = findViewById(R.id.password_verify);
        numVerify = findViewById(R.id.last_four_digits);

        changePassword = findViewById(R.id.changePass);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelperClass dbHelperClass = new DBHelperClass(getApplicationContext());
                boolean exists = dbHelperClass.changeUserPassword(username.getText().toString(), password.getText().toString(), numVerify.getText().toString());

                if(exists) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect user details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}