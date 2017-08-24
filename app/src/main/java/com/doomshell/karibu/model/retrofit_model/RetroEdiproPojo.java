
package com.doomshell.karibu.model.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetroEdiproPojo {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("success")
    @Expose
    private Integer success;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}