package com.aakash.petsforlife;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class UserViewPagerAdapter extends FragmentStateAdapter {

    private int NUM_TABS;

    public UserViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int NUM_TABS) {
        super(fragmentActivity);
        this.NUM_TABS = NUM_TABS;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return UserHomeFragment.newInstance();
            }
            case 1: {
                return TrackerFragment.newInstance();
            }
            case 2: {
                return UserOwnsFragment.newInstance();
            }
            case 3: {
                return UserVetAppointmentFragment.newInstance();
            }
            case 4: {
                return UserProfileFragment.newInstance();
            }
            case 5: {
                return FragmentLogout.newInstance();
            }
            default: {
                return UserHomeFragment.newInstance();
            }
        }
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
