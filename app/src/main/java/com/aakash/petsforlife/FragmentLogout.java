package com.aakash.petsforlife;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FragmentLogout extends Fragment {

    public FragmentLogout() {
        // Required empty public constructor
    }

    public static FragmentLogout newInstance() {
        return new FragmentLogout();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.logout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("GLOBAL_SHARED_PREFERENCES", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("SAVED_USERNAME", null);
                editor.putString("SAVED_PASSWORD", null);
                editor.apply();

                SharedPreferences sharedPreferences_2 = getActivity().getSharedPreferences("USER_SHARED_PREFERENCES", MODE_PRIVATE);
                SharedPreferences.Editor editor_2 = sharedPreferences_2.edit();
                editor_2.putInt("CURR_TAB", 0);
                editor_2.apply();

                getActivity().finish();
            }
        });

        return view;
    }
}