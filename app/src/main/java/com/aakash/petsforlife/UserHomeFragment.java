package com.aakash.petsforlife;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class UserHomeFragment extends Fragment {

    public UserHomeFragment() {
        // Required empty public constructor
    }

    public static UserHomeFragment newInstance(String currentUser) {
        UserHomeFragment userHomeFragment = new UserHomeFragment();

        Bundle bundle = new Bundle();
        bundle.putString("username", currentUser);
        userHomeFragment.setArguments(bundle);

        return userHomeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        String currUser = getArguments().getString("username");
        TextView welcomeUserNote = (TextView) view.findViewById(R.id.currentUserName);
        welcomeUserNote.setText("Welcome, "+ currUser + "!");

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.postsListRecyclerView);
        TextView textView = (TextView) view.findViewById(R.id.listEndIndicator);

        DBHelperClass dbHelperClass = new DBHelperClass(getContext());
        List<Entity> list = dbHelperClass.getAllPosts();
        Toast.makeText(getContext(), "Current tips size: " + list.size(), Toast.LENGTH_SHORT).show();
        if(list.isEmpty()) {
            textView.setText("Not enough data to display.\nClick on the button below to contribute.");
        } else {
            UserRecyclerViewAdapter userRecyclerViewAdapter = new UserRecyclerViewAdapter(getContext(), list, "posts");
            recyclerView.setAdapter(userRecyclerViewAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

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