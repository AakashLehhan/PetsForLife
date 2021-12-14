package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class AdminLogin extends AppCompatActivity {

    List<Entity> list = new ArrayList<>();

    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        /*----- This is to finish the admin login and for returning to previous activity -----*/
        TextView userLogin = findViewById(R.id.user_login);
        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*----- Login Check -----*/
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelperClass dbHelperClass = new DBHelperClass(getApplicationContext());
                if(dbHelperClass.matchAdmin(username.getText().toString(), password.getText().toString())){
                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
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

    }

}