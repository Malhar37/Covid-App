package com.malhar.mycovidtracker.interfaces;

import com.malhar.mycovidtracker.dataclasses.DistrictResponse;
import com.malhar.mycovidtracker.dataclasses.StateResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    /*
       This function fetches state-wise data
     */
    @GET("data.json")
    Call<StateResponse> getStates();

    /*
       This function fetches district-wise data
     */
    @GET("v2/state_district_wise.json")
    Call<List<DistrictResponse>> getDistricts();

}
