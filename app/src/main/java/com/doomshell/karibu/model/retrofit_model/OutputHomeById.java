
package com.doomshell.karibu.model.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutputHomeById {

    @SerializedName("Restorentid")
    @Expose
    private String restorentid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("deliver")
    @Expose
    private String deliver;
    @SerializedName("rating")
    @Expose
    private Float rating;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OutputHomeById() {
    }

    /**
     * 
     * @param deliver
     * @param name
     * @param image
     * @param rating
     * @param restorentid
     * @param type
     */
    public OutputHomeById(String restorentid, String name, String image, String type, String deliver, float rating) {
        super();
        this.restorentid = restorentid;
        this.name = name;
        this.image = image;
        this.type = type;
        this.deliver = deliver;
        this.rating = rating;
    }

    public String getRestorentid() {
        return restorentid;
    }

    public void setRestorentid(String restorentid) {
        this.restorentid = restorentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

}
