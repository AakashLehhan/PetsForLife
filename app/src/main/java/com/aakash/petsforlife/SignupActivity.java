package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private EditText name, username, email, contact, password;
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

        newSignup = findViewById(R.id.new_signup);
        newSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add add = new Add(name.getText().toString(), username.getText().toString(), email.getText().toString(), Long.parseLong(contact.getText().toString()), password.getText().toString());

                final DBHelperClass dbHelperClass = new DBHelperClass(getApplicationContext());
                boolean exists = dbHelperClass.createUser(add);

                if(exists) {
                    Toast.makeText(getApplicationContext(), "user already exists", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "New user created", Toast.LENGTH_SHORT).show();
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
}