package com.malhar.mycovidtracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malhar.mycovidtracker.adapter.DistrictsAdapter;
import com.malhar.mycovidtracker.databinding.FragmentDistrictsBinding;
import com.malhar.mycovidtracker.dataclasses.DistrictResponse;
import com.malhar.mycovidtracker.interfaces.ApiService;
import com.readystatesoftware.chuck.ChuckInterceptor;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DistrictsFragment extends Fragment {

    FragmentDistrictsBinding binding;
    DistrictsAdapter adapter;
    String state;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDistrictsBinding.inflate(inflater, container, false);

        /*
           get strings passed by intent from StateAdapter and set it to corresponding TextViews
         */

        state = requireActivity().getIntent().getStringExtra("state");
        binding.stateTv.setText(state);
        binding.confirmedTv.setText(requireActivity().getIntent().getStringExtra("confirm"));
        binding.activeTv.setText(requireActivity().getIntent().getStringExtra("active"));
        binding.deathTv.setText(requireActivity().getIntent().getStringExtra("dead"));
        binding.recoveredTv.setText(requireActivity().getIntent().getStringExtra("cured"));

        setUpUi();

        binding.swipeLayout.setOnRefreshListener(this::setUpUi);

        return binding.getRoot();
    }

    private void setUpUi() {

        binding.nestedView.setVisibility(View.INVISIBLE);

        /*
           1. Create OkHttp client
           2. Add Chuck Interceptor to it
           3. Chuck shows the data we have received in notification bar
         */
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(requireContext()))
                .build();
        /*
           Retrofit instance
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<DistrictResponse>> call = apiService.getDistricts();

        call.enqueue(new Callback<List<DistrictResponse>>() {
            @Override
            public void onResponse(@NotNull Call<List<DistrictResponse>> call, @NotNull  Response<List<DistrictResponse>> response) {
                if (response.isSuccessful()) {
                    List<DistrictResponse> list = response.body();
                    /*
                       1. Above list contains data of all states
                       2. To get the data of required state iterate through above list till object
                       corresponding to req. state is found
                       3. Then set adapter with list of district data of req. state
                     */
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getState().equals(state)) {
                            adapter = new DistrictsAdapter(getContext(), list.get(i).getDistrictData());
                            binding.rv.setAdapter(adapter);
                            break;
                        }
                    }
                    binding.progressBarLayout.setVisibility(View.GONE);
                    binding.nestedView.setVisibility(View.VISIBLE);

                    binding.swipeLayout.setRefreshing(false);

                }
            }

            @Override
            public void onFailure(@NotNull Call<List<DistrictResponse>> call, @NotNull Throwable t) {
                binding.progressbar.setVisibility(View.GONE);
                binding.statusTV.setText("Error occured, Please retry");
            }
        });
    }

}