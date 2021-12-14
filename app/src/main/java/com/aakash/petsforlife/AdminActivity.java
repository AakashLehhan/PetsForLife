package com.aakash.petsforlife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class AdminActivity extends AppCompatActivity {
    private static final int NUM_TABS = 6;

    private AdminViewPagerAdapter adminViewPagerAdapter;
    private final Integer[] icons = new Integer[]{R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_search_24, R.drawable.ic_baseline_pets_24, R.drawable.ic_baseline_supervisor_account_24, R.drawable.ic_baseline_medical_services_24, R.drawable.ic_baseline_exit_to_app_24};
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);

        adminViewPagerAdapter = new AdminViewPagerAdapter(this, NUM_TABS);
        viewPager2.setAdapter(adminViewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setIcon(icons[position])).attach();
    }

    public void switchTab(int position) {
        Objects.requireNonNull(tabLayout.getTabAt(position)).select();
    }

    @Override
    public void onBackPressed() {
        if(viewPager2.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager2.setCurrentItem(0);
        }
    }
}