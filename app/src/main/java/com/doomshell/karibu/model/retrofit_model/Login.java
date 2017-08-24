
package com.doomshell.karibu.model.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("email_id")
    @Expose
    private String emailId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("mobile")
    @Expose
    private String mobile;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Login() {
    }

    /**
     * 
     * @param lastName
     * @param emailId
     * @param userId
     * @param firstName
     * @param success
     * @param mobile
     */
    public Login(int success, String userId, String emailId, String firstName, String lastName, String mobile) {
        super();
        this.success = success;
        this.userId = userId;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
