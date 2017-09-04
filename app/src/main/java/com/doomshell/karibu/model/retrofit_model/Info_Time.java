
package com.doomshell.karibu.model.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info_Time {

    @SerializedName("opentime")
    @Expose
    private String opentime;
    @SerializedName("closetime")
    @Expose
    private String closetime;
    @SerializedName("day")
    @Expose
    private String day;

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getClosetime() {
        return closetime;
    }

    public void setClosetime(String closetime) {
        this.closetime = closetime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
