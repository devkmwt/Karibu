
package com.doomshell.karibu.model.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Retrorreviewpojo {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("output")
    @Expose
    private List<Output_Review_Retorant> outputReviewRetorant = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Output_Review_Retorant> getOutputReviewRetorant() {
        return outputReviewRetorant;
    }

    public void setOutputReviewRetorant(List<Output_Review_Retorant> outputReviewRetorant) {
        this.outputReviewRetorant = outputReviewRetorant;
    }

}
