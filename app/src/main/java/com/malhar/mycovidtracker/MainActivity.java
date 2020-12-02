package com.malhar.mycovidtracker;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.malhar.mycovidtracker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
//    private Button button;
//    Toolbar toolbar;
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;
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

        //button = findViewById(R.id.button);
        // toolbar = findViewById(R.id.toolbar);
        //drawerLayout = findViewById(R.id.drawer);
        //navigationView = findViewById(R.id.nav_view);

        binding.button.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, StatisticsActivity.class))
        );

    }
}