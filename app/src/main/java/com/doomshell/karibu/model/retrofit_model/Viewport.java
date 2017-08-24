
package com.doomshell.karibu.model.retrofit_model;

import com.doomshell.karibu.model.retrofit_model.for_zip.Northeast;
import com.doomshell.karibu.model.retrofit_model.for_zip.Southwest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Viewport {

    @SerializedName("northeast")
    @Expose
    private Northeast northeast;
    @SerializedName("southwest")
    @Expose
    private Southwest southwest;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Viewport() {
    }

    /**
     * 
     * @param southwest
     * @param northeast
     */
    public Viewport(Northeast northeast, Southwest southwest) {
        super();
        this.northeast = northeast;
        this.southwest = southwest;
    }

    public Northeast getNortheast() {
        return northeast;
    }

    public void setNortheast(Northeast northeast) {
        this.northeast = northeast;
    }

    public Southwest getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Southwest southwest) {
        this.southwest = southwest;
    }

}
