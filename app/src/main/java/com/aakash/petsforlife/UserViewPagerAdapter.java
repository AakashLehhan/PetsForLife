package com.aakash.petsforlife;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class UserViewPagerAdapter extends FragmentStateAdapter {

    private int NUM_TABS;
    private String curr_user;

    public UserViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int NUM_TABS, String curr_user) {
        super(fragmentActivity);
        this.NUM_TABS = NUM_TABS;
        this.curr_user = curr_user;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return UserHomeFragment.newInstance(curr_user);
            }
            case 1: {
                return TrackerFragment.newInstance(/*curr_user*/);
            }
            case 2: {
                return UserOwnsFragment.newInstance(curr_user);
            }
            case 3: {
                return UserVetAppointmentFragment.newInstance(/*curr_user*/);
            }
            case 4: {
                return UserProfileFragment.newInstance(curr_user);
            }
            case 5: {
                return FragmentLogout.newInstance();
            }
            default: {
                return UserHomeFragment.newInstance(curr_user);
            }
        }
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
