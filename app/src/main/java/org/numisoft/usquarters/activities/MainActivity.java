package org.numisoft.usquarters.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.numisoft.usquarters.R;
import org.numisoft.usquarters.adapters.PageViewAdapter;
import org.numisoft.usquarters.models.Theme;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle drawerToggle;
    DrawerLayout drawerLayout;
    PageViewAdapter pagerAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.the_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("America The Beautiful");

// Tabs
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.addTab(tabLayout.newTab().setText("ALL"));
//        tabLayout.addTab(tabLayout.newTab().setText("HAVE"));
        tabLayout.addTab(tabLayout.newTab().setText("NEED"));
        tabLayout.addTab(tabLayout.newTab().setText("SWAP"));
        tabLayout.addTab(tabLayout.newTab().setText("UNC-"));

// Pager
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new PageViewAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount(), Theme.PARKS_P);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

// Drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

// Navigation
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.addHeaderView(View.inflate(this, R.layout.nav_header, null));
//        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_menu_1:
                pagerAdapter = new PageViewAdapter(getSupportFragmentManager(),
                        tabLayout.getTabCount(), Theme.STATES_P);
                viewPager.setAdapter(pagerAdapter);
                break;
            case R.id.nav_menu_3:
                pagerAdapter = new PageViewAdapter(getSupportFragmentManager(),
                        tabLayout.getTabCount(), Theme.PARKS_P);
                viewPager.setAdapter(pagerAdapter);
                break;
            case R.id.nav_menu_7:
                pagerAdapter = new PageViewAdapter(getSupportFragmentManager(),
                        tabLayout.getTabCount(), Theme.PRESIDENTS_P);
                viewPager.setAdapter(pagerAdapter);
                break;
        }

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        return false;
    }

}
