package com.aakash.petsforlife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminDashboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminDashboard extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private int mParam2;

    Integer[] colors = new Integer[]{R.color.grey_shade, R.color.black_shade_1, R.color.purple_500};

    public AdminDashboard() {
        // Required empty public constructor
    }

    public static AdminDashboard newInstance(String param1, int param2) {
        AdminDashboard fragment = new AdminDashboard();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_dashboard, container, false);
        //view.setBackgroundColor(colors[mParam2]);
        TextView debug = (TextView) view.findViewById(R.id.testing);
        debug.setText(mParam1);

        return view;
    }
}