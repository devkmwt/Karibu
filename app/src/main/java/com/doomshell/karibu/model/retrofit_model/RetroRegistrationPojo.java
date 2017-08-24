
package com.doomshell.karibu.model.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetroRegistrationPojo {

    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("userid")
    @Expose
    private String userid;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RetroRegistrationPojo() {
    }

    /**
     * 
     * @param userid
     * @param success
     */
    public RetroRegistrationPojo(int success, String userid) {
        super();
        this.success = success;
        this.userid = userid;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
