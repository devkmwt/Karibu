
package com.doomshell.karibu.model.retrofit_model.for_zip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Northeast_ {

    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Northeast_() {
    }

    /**
     * 
     * @param lng
     * @param lat
     */
    public Northeast_(double lat, double lng) {
        super();
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

}
