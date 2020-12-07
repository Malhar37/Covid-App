
package com.malhar.mycovidtracker.dataclasses;

//import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

//@Generated("net.hexar.json2pojo")
//@SuppressWarnings("unused")
public class CasesTimeSeries {

    @Expose
    private String dailyconfirmed;
    @Expose
    private String dailydeceased;
    @Expose
    private String dailyrecovered;
    @Expose
    private String date;
    @Expose
    private String dateymd;
    @Expose
    private String totalconfirmed;
    @Expose
    private String totaldeceased;
    @Expose
    private String totalrecovered;

    public String getDailyconfirmed() {
        return dailyconfirmed;
    }

    public void setDailyconfirmed(String dailyconfirmed) {
        this.dailyconfirmed = dailyconfirmed;
    }

    public String getDailydeceased() {
        return dailydeceased;
    }

    public void setDailydeceased(String dailydeceased) {
        this.dailydeceased = dailydeceased;
    }

    public String getDailyrecovered() {
        return dailyrecovered;
    }

    public void setDailyrecovered(String dailyrecovered) {
        this.dailyrecovered = dailyrecovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateymd() {
        return dateymd;
    }

    public void setDateymd(String dateymd) {
        this.dateymd = dateymd;
    }

    public String getTotalconfirmed() {
        return totalconfirmed;
    }

    public void setTotalconfirmed(String totalconfirmed) {
        this.totalconfirmed = totalconfirmed;
    }

    public String getTotaldeceased() {
        return totaldeceased;
    }

    public void setTotaldeceased(String totaldeceased) {
        this.totaldeceased = totaldeceased;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(String totalrecovered) {
        this.totalrecovered = totalrecovered;
    }

}
