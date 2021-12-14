package com.aakash.petsforlife;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdminViewPagerAdapter extends FragmentStateAdapter {

    private final int numTabs;

    AdminViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int numTabs){
        super(fragmentActivity);
        this.numTabs = numTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return AdminDashboard.newInstance("First Fragment", position);
            }
            case 1: {
                return TrackerFragment.newInstance();
            }
            case 2: {
                return AdminManagePetsFragment.newInstance();
            }
            case 3: {
                return AdminManageUsersFragment.newInstance();
            }
            case 4: {
                return AdminManageMedicalServicesFragment.newInstance();
            }
            case 5: {
                return FragmentLogout.newInstance();
            }
            default: {
                return AdminDashboard.newInstance("First Fragment", 0);
            }
        }
    }

    @Override
    public int getItemCount() {
        return numTabs;
    }
}
