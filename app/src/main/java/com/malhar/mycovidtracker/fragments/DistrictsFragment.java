package com.malhar.mycovidtracker.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malhar.mycovidtracker.R;
import com.malhar.mycovidtracker.adapter.DistrictsAdapter;
import com.malhar.mycovidtracker.databinding.FragmentDistrictsBinding;
import com.malhar.mycovidtracker.dataclasses.Example;
import com.malhar.mycovidtracker.interfaces.ApiService;

import java.util.List;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDistrictsBinding.inflate(inflater, container, false);

        //set data to textviews

        state = requireActivity().getIntent().getStringExtra("state");
        binding.stateTv.setText(state);
        binding.confirmedTv.setText(requireActivity().getIntent().getStringExtra("confirm"));
        binding.activeTv.setText(requireActivity().getIntent().getStringExtra("active"));
        binding.deathTv.setText(requireActivity().getIntent().getStringExtra("dead"));
        binding.recoveredTv.setText(requireActivity().getIntent().getStringExtra("cured"));

        setUpUi();
        return binding.getRoot();
    }

    private void setUpUi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19india.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<Example>> call = apiService.getDistricts();

        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                if (response.isSuccessful()) {
                    List<Example> list = response.body();

                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getState().equals(state)) {
                            adapter = new DistrictsAdapter(getContext(), list.get(i).getDistrictData());
                            binding.rv.setAdapter(adapter);
                            break;
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {

            }
        });
    }

}