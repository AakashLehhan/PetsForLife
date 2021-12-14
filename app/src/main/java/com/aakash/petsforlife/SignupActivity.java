package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private EditText name, username, email, contact, password, passwordVerify;
    Button newSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        password = findViewById(R.id.password);
        passwordVerify = findViewById(R.id.password_verify);

        newSignup = findViewById(R.id.new_signup);
        newSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) {
                    Entity entity = new Entity(name.getText().toString(), username.getText().toString(), email.getText().toString(), Long.parseLong(contact.getText().toString()), password.getText().toString());

                    final DBHelperClass dbHelperClass = new DBHelperClass(getApplicationContext());
                    boolean exists = dbHelperClass.createUser(entity);

                    if(exists) {
                        RelativeLayout parentLayout = findViewById(R.id.parentLayout);
                        Snackbar snackbar = Snackbar.make(parentLayout, "User already exists!", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.setBackgroundTint(getResources().getColor(R.color.black_shade_1));
                        snackbar.setTextColor(getResources().getColor(R.color.grey_shade));
                        snackbar.show();
                    } else {
                        RelativeLayout parentLayout = findViewById(R.id.parentLayout);
                        Snackbar snackbar = Snackbar.make(parentLayout, "New user created", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                                finish();
                            }
                        });
                        snackbar.setBackgroundTint(getResources().getColor(R.color.black_shade_1));
                        snackbar.setTextColor(getResources().getColor(R.color.grey_shade));
                        snackbar.show();
                    }
                } else {
                    RelativeLayout parentLayout = findViewById(R.id.parentLayout);
                    Snackbar snackbar = Snackbar.make(parentLayout, "Please correct the fields highlighted!", Snackbar.LENGTH_INDEFINITE);
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


        /*----- This is to finish the signup and for returning to previous activity -----*/
        TextView userLogin = findViewById(R.id.user_login);
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public boolean isValid(){
        boolean valid = true;
        Pattern specialChar = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern upperCase = Pattern.compile("[A-Z ]");
        Pattern lowerCase = Pattern.compile("[a-z ]");
        Pattern digitCase = Pattern.compile("[0-9 ]");

        if(name.length() == 0) {
            name.setError("This field is required!");
            valid = false;
        }

        if(username.length() == 0) {
            username.setError("This field is required!");
            valid = false;
        } else if(username.getText().toString().contains(" ") || specialChar.matcher(username.getText().toString()).find() || upperCase.matcher(username.getText().toString()).find()) {
            username.setError("Enter a valid username!");
            valid = false;
        }

        if(email.length() == 0) {
            email.setError("This field is required!");
            valid = false;
        } else if (!email.getText().toString().contains("@") || !email.getText().toString().contains(".") || !email.getText().toString().contains("mail")) {
            email.setError("Enter a valid email!");
            valid = false;
        }

        if(contact.length() == 0) {
            contact.setError("This field is required!");
            valid = false;
        } else if (contact.length() > 10 || contact.length() < 10) {
            contact.setError("Enter a valid number!");
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
        return valid;
    }
}