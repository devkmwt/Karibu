
package com.doomshell.karibu.model.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Output_menu {

    @SerializedName("Restorentid")
    @Expose
    private String restorentid;
    @SerializedName("pname")
    @Expose
    private String pname;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("s_price")
    @Expose
    private String sPrice;
    @SerializedName("pid")
    @Expose
    private String pid;

    public String getRestorentid() {
        return restorentid;
    }

    public void setRestorentid(String restorentid) {
        this.restorentid = restorentid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSPrice() {
        return sPrice;
    }

    public void setSPrice(String sPrice) {
        this.sPrice = sPrice;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

}
