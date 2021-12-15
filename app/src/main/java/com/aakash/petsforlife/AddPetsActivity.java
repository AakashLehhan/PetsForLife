package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

public class AddPetsActivity extends AppCompatActivity {

    EditText pName, pToken, pType, pDOB, pDesc;
    Button addPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pets);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_SHARED_PREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("CURR_TAB", 2);
        editor.apply();

        pName = findViewById(R.id.petName);
        pToken = findViewById(R.id.petToken);
        pType = findViewById(R.id.petType);
        pDOB = findViewById(R.id.petDOB);
        pDesc = findViewById(R.id.petDesc);

        addPet = findViewById(R.id.addNewPet);
        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) {
                    if(new DBHelperClass(getApplicationContext()).validatePet(pToken.getText().toString())) {
                        RelativeLayout parentLayout = findViewById(R.id.parentLayout);
                        Snackbar snackbar = Snackbar.make(parentLayout, "Mentioned token already exists!", Snackbar.LENGTH_INDEFINITE);
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

                        Intent intent = getIntent();
                        String currUser = intent.getStringExtra("CURR_USER");
                        new DBHelperClass(getApplicationContext()).addPet(currUser,
                                pName.getText().toString(),
                                pToken.getText().toString(),
                                pType.getText().toString(),
                                pDOB.getText().toString(),
                                pDesc.getText().toString());

                        RelativeLayout parentLayout = findViewById(R.id.parentLayout);
                        Snackbar snackbar = Snackbar.make(parentLayout, "Added to the database.", Snackbar.LENGTH_INDEFINITE);
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

    public boolean isValid(){
        boolean valid = true;

        if(pName.length() == 0) {
            pName.setError("This field is required!");
            valid = false;
        }

        if(pToken.length() == 0) {
            pToken.setError("This field is required!");
            valid = false;
        }else if(!pToken.getText().toString().contains("TOK")) {
            pToken.setError("Invalid token format!");
            valid = false;
        }

        if(pType.length() == 0) {
            pType.setError("This field is required!");
            valid = false;
        }

        if(pDOB.length() == 0) {
            pDOB.setError("This field is required!");
            valid = false;
        }

        if(pDesc.length() == 0) {
            pDesc.setError("This field is required!");
            valid = false;
        }

        return valid;
    }
}