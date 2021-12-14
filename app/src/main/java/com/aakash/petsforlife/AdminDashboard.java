package com.aakash.petsforlife;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

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

        TextView switchOne = (TextView) view.findViewById(R.id.view_pets);
        switchOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
                tabLayout.getTabAt(2).select();
            }
        });

        TextView switchTwo = (TextView) view.findViewById(R.id.view_users);
        switchTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
                tabLayout.getTabAt(3).select();
            }
        });

        TextView switchThree = (TextView) view.findViewById(R.id.view_vets);
        switchThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
                tabLayout.getTabAt(4).select();
            }
        });

        FloatingActionButton addPostButton = (FloatingActionButton) view.findViewById(R.id.addPostButton);
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTipsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}