package com.aakash.petsforlife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserVetAppointmentFragment extends Fragment {

    public UserVetAppointmentFragment() {
        // Required empty public constructor
    }

    public static UserVetAppointmentFragment newInstance() {
        return new UserVetAppointmentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_vet_appointment, container, false);
    }
}