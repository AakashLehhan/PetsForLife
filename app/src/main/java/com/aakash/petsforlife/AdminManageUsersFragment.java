package com.aakash.petsforlife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdminManageUsersFragment extends Fragment {

    public AdminManageUsersFragment() {
        // Required empty public constructor
    }

    public static AdminManageUsersFragment newInstance() {
        return new AdminManageUsersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_all_users_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        TextView textView = (TextView) view.findViewById(R.id.notEnoughData);

        DBHelperClass dbHelperClass = new DBHelperClass(getContext());
        List<Entity> list = dbHelperClass.getAllUsers();

        if(list.isEmpty()) { textView.setVisibility(View.VISIBLE); }
        else { textView.setVisibility(View.INVISIBLE); }

        AdminRecyclerViewAdapter adminRecyclerViewAdapter = new AdminRecyclerViewAdapter(getContext(), list, "users");
        recyclerView.setAdapter(adminRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}