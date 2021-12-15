package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

public class AddTipsActivity extends AppCompatActivity {

    EditText title, description;
    Button addPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tips);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_SHARED_PREFERENCES", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("CURR_TAB", 0);
        editor.apply();

        title = findViewById(R.id.postTitle);
        description = findViewById(R.id.postDesc);
        addPost = findViewById(R.id.addPost);

        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) {
                    if(new DBHelperClass(getApplicationContext()).addPost(title.getText().toString(), description.getText().toString())) {
                        LinearLayout parentLayout = findViewById(R.id.parentLayout);
                        Snackbar snackbar = Snackbar.make(parentLayout, "New post created!", Snackbar.LENGTH_INDEFINITE);
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
                    LinearLayout parentLayout = findViewById(R.id.parentLayout);
                    Snackbar snackbar = Snackbar.make(parentLayout, "Please fix the issues first!", Snackbar.LENGTH_INDEFINITE);
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

        if(title.length() == 0) {
            title.setError("This field is required!");
            valid = false;
        }

        if(description.length() == 0) {
            description.setError("This field is required!");
            valid = false;
        }

        return valid;
    }
}