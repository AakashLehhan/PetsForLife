package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

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

                if(isValid()) {
                    LinearLayout parentLayout = findViewById(R.id.parentLayout);
                    Snackbar snackbar;
                    if(exists) {
                        snackbar = Snackbar.make(parentLayout, "Password changed!", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });
                    } else {
                        snackbar = Snackbar.make(parentLayout, "Incorrect details!", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                    }
                    snackbar.setBackgroundTint(getResources().getColor(R.color.black_shade_1));
                    snackbar.setTextColor(getResources().getColor(R.color.grey_shade));
                    snackbar.show();
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

    }

    public boolean isValid() {
        boolean valid = true;
        Pattern specialChar = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern upperCase = Pattern.compile("[A-Z ]");
        Pattern lowerCase = Pattern.compile("[a-z ]");
        Pattern digitCase = Pattern.compile("[0-9 ]");

        if(username.length() == 0) {
            username.setError("This field is required!");
            valid = false;
        }

        if (password.length() == 0) {
            password.setError("This field is required!");
            valid = false;
        } else if (password.length() < 8 || password.length() > 20) {
            password.setError("Password should be of length (8 - 20)");
            valid = false;
        } else if (!specialChar.matcher(password.getText().toString()).find() || !lowerCase.matcher(password.getText().toString()).find() || !upperCase.matcher(password.getText().toString()).find() || !digitCase.matcher(password.getText().toString()).find()){
            password.setError("Please follow the criteria mentioned!");
            valid = false;
        } else if(!(password.getText().toString().equals(passwordVerify.getText().toString()))) {
            passwordVerify.setError("Passwords do not match!");
            valid = false;
        }

        if (numVerify.length() == 0) {
            numVerify.setError("This field is required!");
            valid = false;
        } else if (numVerify.length() < 4 || numVerify.length() > 4) {
            numVerify.setError("Enter last 4 digits only!");
        }
        return valid;
    }
}