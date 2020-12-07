package com.malhar.mycovidtracker.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malhar.mycovidtracker.R;
import com.malhar.mycovidtracker.databinding.FragmentAboutBinding;
import com.malhar.mycovidtracker.databinding.FragmentDistrictsBinding;

public class AboutFragment extends Fragment {

    FragmentAboutBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //binding
        binding = FragmentAboutBinding.inflate(inflater, container, false);
        setup();
        return binding.getRoot();
    }

    private void setup() {
        binding.statelinkbutton.setOnClickListener(v -> {
            String url = "https://api.covid19india.org/data.json";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
        binding.districtlinkbutton.setOnClickListener(v -> {
            String url="https://api.covid19india.org/v2/state_district_wise.json";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });

        binding.alldetailsTV.setText("https://api.covid19india.org");
        Linkify.addLinks(binding.alldetailsTV, Linkify.WEB_URLS);
    }
}