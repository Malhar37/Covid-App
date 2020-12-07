package com.malhar.mycovidtracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.malhar.mycovidtracker.R;
import com.malhar.mycovidtracker.fragments.AboutFragment;
import com.malhar.mycovidtracker.fragments.DistrictsFragment;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        switch (getIntent().getStringExtra("FragmentType")){
            case "District" : {
                addOrReplaceFragment(new DistrictsFragment());
                break;
            }

            case "About" : {
                addOrReplaceFragment(new AboutFragment());
                break;
            }
        }

    }

    private void addOrReplaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, "").commit();
    }
}