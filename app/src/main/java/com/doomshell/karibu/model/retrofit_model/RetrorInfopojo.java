
package com.doomshell.karibu.model.retrofit_model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrorInfopojo {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("time")
    @Expose
    private List<Info_Time> time = null;
    @SerializedName("paymentmethods")
    @Expose
    private List<Info_Paymentmethod> paymentmethods = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Info_Time> getTime() {
        return time;
    }

    public void setTime(List<Info_Time> time) {
        this.time = time;
    }

    public List<Info_Paymentmethod> getPaymentmethods() {
        return paymentmethods;
    }

    public void setPaymentmethods(List<Info_Paymentmethod> paymentmethods) {
        this.paymentmethods = paymentmethods;
    }

}
