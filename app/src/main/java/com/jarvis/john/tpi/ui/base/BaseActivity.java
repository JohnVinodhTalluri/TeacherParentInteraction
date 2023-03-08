package com.jarvis.john.tpi.ui.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.jarvis.john.tpi.R;

public class BaseActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    protected Activity mActivity;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setupCurrentActivity(Activity activity) {
        this.mActivity = activity;
    }

    public Activity getCurrentActivity() {
        if (this.mActivity != null)
            return this.mActivity;
        return null;
    }

    public void setupDrawerLayout() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(BaseActivity.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(BaseActivity.this);
    }

    public void setContentView(View view) {
        DrawerLayout drawerLayout = (DrawerLayout)getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout)drawerLayout.findViewById(R.id.nav_host_frame_layout_content_main);
        activityContainer.addView(view);
        super.setContentView(drawerLayout);
    }

    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            // Handle home action
            return true;
        } else if (id == R.id.nav_gallery) {
            // Handle profile action
            Toast.makeText(getApplicationContext(), "Clicked on Gallery", Toast.LENGTH_LONG).show();
            return true;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}