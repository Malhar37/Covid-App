package com.malhar.mycovidtracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.malhar.mycovidtracker.adapter.StateAdapter;
import com.malhar.mycovidtracker.databinding.ActivityStatisticsBinding;
import com.malhar.mycovidtracker.dataclasses.StateResponse;
import com.malhar.mycovidtracker.interfaces.ApiService;
import com.readystatesoftware.chuck.ChuckInterceptor;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;
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

        setUpUi();

        binding.swipeLayout.setOnRefreshListener(this::setUpUi);
    }

    private void setUpUi(){

        binding.nestedView.setVisibility(View.INVISIBLE);
        /*
           1. Create OkHttp client
           2. Add Chuck Interceptor to it
           3. Chuck shows the data we have received in notification bar
         */
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(this))
                .build();

        /*
           Create Retrofit instance
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<StateResponse> call = apiService.getStates();

        call.enqueue(new Callback<StateResponse>() {
            @Override
            public void onResponse(@NotNull Call<StateResponse> call, @NotNull Response<StateResponse> response) {
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

                    /*
                       1. Hide loading screen if the data is fetched
                       2. Show ui of screen containing the information
                     */
                    binding.progressBarLayout.setVisibility(View.GONE);
                    binding.nestedView.setVisibility(View.VISIBLE);

                    binding.swipeLayout.setRefreshing(false);

                }
            }

            @Override
            public void onFailure(@NotNull Call<StateResponse> call, @NotNull Throwable t) {
                /*
                   If error has occurred then hide loading progress bar and show below error message
                 */
                binding.progressbar.setVisibility(View.GONE);
                binding.statusTV.setText("Error occured, Please retry");
            }
        });
    }
}