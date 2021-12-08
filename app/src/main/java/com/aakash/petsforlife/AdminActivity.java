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

public class AdminActivity extends AppCompatActivity {
    private static final int NUM_TABS = 5;

    private AdminViewPagerAdapter adminViewPagerAdapter;
    private final Integer[] icons = new Integer[]{R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_search_24, R.drawable.ic_baseline_pets_24, R.drawable.ic_baseline_list_24, R.drawable.ic_baseline_exit_to_app_24};
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

    @Override
    public void onBackPressed() {
        if(viewPager2.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }
    }
}