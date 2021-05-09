package com.malhar.mycovidtracker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.malhar.mycovidtracker.R;
import com.malhar.mycovidtracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setting toolbar
        setSupportActionBar(binding.toolbar);

        binding.navView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawer,
                binding.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        binding.drawer.addDrawerListener(toggle);
        binding.navView.setNavigationItemSelectedListener(this);

        binding.button.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, StatisticsActivity.class))
        );

    }

    @Override
    public void onBackPressed() {
        /*
          If drawer is open then close it on back pressed
          Else close activity
         */
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home: {
                binding.drawer.closeDrawer(GravityCompat.START);
                break;
            }
            case R.id.website: {
                /*
                  Open mentioned website
                 */
                Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("https://covid19indiadata.herokuapp.com/"));
                startActivity(myWebLink);
                break;
            }
            case R.id.nav_about: {
                /*
                  Open about fragment
                 */
                Intent intent = new Intent(getApplicationContext(), BaseActivity.class);
                intent.putExtra("FragmentType", "About");
                startActivity(intent);
                break;
            }
            case R.id.nav_feedback: {
                try {
                    /*
                      Send feedback email to mentioned email IDs
                     */
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"atharvsk11@gmail.com",
                            "loharmalhar@gmail.com", "manavahuja3@gmail.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}