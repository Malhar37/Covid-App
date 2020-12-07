package com.malhar.mycovidtracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.malhar.mycovidtracker.R;
import com.malhar.mycovidtracker.fragments.DistrictsFragment;

public class BaseActivity extends AppCompatActivity {

    DistrictsFragment districtsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        districtsFragment = new DistrictsFragment();
        addOrReplaceFragment(districtsFragment);
    }

    private void addOrReplaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, "").commit();
    }
}