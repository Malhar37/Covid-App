
package com.malhar.mycovidtracker.dataclasses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateResponse {

    @SerializedName("cases_time_series")
    private List<CasesTimeSeries> casesTimeSeries;
    @Expose
    private List<Statewise> statewise;
    @Expose
    private List<Tested> tested;

    public List<CasesTimeSeries> getCasesTimeSeries() {
        return casesTimeSeries;
    }

    public void setCasesTimeSeries(List<CasesTimeSeries> casesTimeSeries) {
        this.casesTimeSeries = casesTimeSeries;
    }

    public List<Statewise> getStatewise() {
        return statewise;
    }

    public void setStatewise(List<Statewise> statewise) {
        this.statewise = statewise;
    }

    public List<Tested> getTested() {
        return tested;
    }

    public void setTested(List<Tested> tested) {
        this.tested = tested;
    }

}
