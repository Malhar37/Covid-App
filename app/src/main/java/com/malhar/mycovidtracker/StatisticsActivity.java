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

        //Retrofit
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

                    //First entry in list is total counts; set it to textviews in cardviews and delete
                    // 1st entry so that it will not be shown in recycler view

                    binding.confirmedTv.setText(res.getStatewise().get(0).getConfirmed());
                    binding.activeTv.setText(res.getStatewise().get(0).getActive());
                    binding.deathTv.setText(res.getStatewise().get(0).getDeaths());
                    binding.recoveredTv.setText(res.getStatewise().get(0).getRecovered());

                    res.getStatewise().remove(0);

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