package com.aakash.petsforlife;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserProfileFragment extends Fragment {

    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance(String currentUser) {
        UserProfileFragment userProfileFragment = new UserProfileFragment();

        Bundle bundle = new Bundle();
        bundle.putString("username", currentUser);
        userProfileFragment.setArguments(bundle);

        return userProfileFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        String currentUser = getArguments().getString("username");
        Entity entity = new DBHelperClass(getContext()).getUserDetails(currentUser);

        TextView fullName = (TextView) view.findViewById(R.id.currUserFullName);
        fullName.setText(entity.getName());

        TextView username = (TextView) view.findViewById(R.id.currUserUsername);
        username.setText(entity.getUsername());

        TextView contact = (TextView) view.findViewById(R.id.currUserContact);
        contact.setText(String.valueOf(entity.getContact()));

        TextView email = (TextView) view.findViewById(R.id.currUserEmail);
        email.setText(entity.getEmail());

        return view;
    }
}