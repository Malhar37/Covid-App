package com.malhar.mycovidtracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.malhar.mycovidtracker.R;
import com.malhar.mycovidtracker.fragments.AboutFragment;
import com.malhar.mycovidtracker.fragments.DistrictsFragment;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        /*
           Get value of key 'FragmentType' passed by intent
         */
        switch (getIntent().getStringExtra("FragmentType")){
            case "District" : {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,new DistrictsFragment(), "").commit();
                break;
            }

            case "About" : {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,new AboutFragment(), "").commit();
                break;
            }
        }

    }

}