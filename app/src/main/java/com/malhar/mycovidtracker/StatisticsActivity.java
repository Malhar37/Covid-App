package com.malhar.mycovidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.malhar.mycovidtracker.activities.BaseActivity;
import com.malhar.mycovidtracker.adapter.StateAdapter;
import com.malhar.mycovidtracker.databinding.ActivityStatisticsBinding;
import com.malhar.mycovidtracker.dataclasses.StateResponse;
import com.malhar.mycovidtracker.interfaces.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsActivity extends AppCompatActivity {

    ActivityStatisticsBinding binding;
    StateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // binding
        binding = ActivityStatisticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.activeCard.setOnClickListener(v -> startActivity(new Intent(this, BaseActivity.class)));

        setUpUi();
    }

    private void setUpUi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<StateResponse> call = apiService.getStates();

        call.enqueue(new Callback<StateResponse>() {
            @Override
            public void onResponse(Call<StateResponse> call, Response<StateResponse> response) {
                if (response.isSuccessful()) {
                    StateResponse res = response.body();
                    System.out.println(res.getStatewise().get(1).getState());
                    adapter = new StateAdapter(StatisticsActivity.this,res.getStatewise());
                    binding.rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<StateResponse> call, Throwable t) {

            }
        });
    }
}