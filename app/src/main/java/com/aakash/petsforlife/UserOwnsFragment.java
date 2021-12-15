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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UserOwnsFragment extends Fragment {

    public UserOwnsFragment() {
        // Required empty public constructor
    }

    public static UserOwnsFragment newInstance(String currentUser) {
        UserOwnsFragment userOwnsFragment = new UserOwnsFragment();

        Bundle bundle = new Bundle();
        bundle.putString("username", currentUser);
        userOwnsFragment.setArguments(bundle);

        return userOwnsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_owns, container, false);
        String currUser = getArguments().getString("username");

        FloatingActionButton addPetsButton = (FloatingActionButton) view.findViewById(R.id.addPetsButton);
        addPetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPetsActivity.class);
                intent.putExtra("CURR_USER", currUser);
                startActivity(intent);
            }
        });

        List<Entity> list = new DBHelperClass(getContext()).getAllUserPets(currUser);
        RecyclerView petsList = (RecyclerView) view.findViewById(R.id.petsListView);
        TextView IsDataAvailable = (TextView) view.findViewById(R.id.isDataAvailable);
        if(list.isEmpty()) {
            IsDataAvailable.setVisibility(View.VISIBLE);
        } else {
            IsDataAvailable.setVisibility(View.INVISIBLE);
            UserRecyclerViewAdapter userRecyclerViewAdapter = new UserRecyclerViewAdapter(getContext(), list, "pets");
            petsList.setAdapter(userRecyclerViewAdapter);
            petsList.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        return view;
    }
}