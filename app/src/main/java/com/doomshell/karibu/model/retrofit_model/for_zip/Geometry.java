
package com.doomshell.karibu.model.retrofit_model.for_zip;

import com.doomshell.karibu.model.retrofit_model.Viewport;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("location_type")
    @Expose
    private String locationType;
    @SerializedName("viewport")
    @Expose
    private Viewport viewport;
    @SerializedName("bounds")
    @Expose
    private Bounds bounds;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Geometry() {
    }

    /**
     * 
     * @param bounds
     * @param viewport
     * @param location
     * @param locationType
     */
    public Geometry(Location location, String locationType, Viewport viewport, Bounds bounds) {
        super();
        this.location = location;
        this.locationType = locationType;
        this.viewport = viewport;
        this.bounds = bounds;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

}
