package com.malhar.mycovidtracker.interfaces;

import com.malhar.mycovidtracker.dataclasses.Example;
import com.malhar.mycovidtracker.dataclasses.StateResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("data.json")
    Call<StateResponse> getStates();

    @GET("v2/state_district_wise.json")
    Call<List<Example>> getDistricts();

}
