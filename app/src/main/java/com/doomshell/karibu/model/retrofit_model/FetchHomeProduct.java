
package com.doomshell.karibu.model.retrofit_model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchHomeProduct {

    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("output")
    @Expose
    private List<Output_Fetch_HomeProduct> outputFatchhomeproduct = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FetchHomeProduct() {
    }

    /**
     * 
     * @param outputFatchhomeproduct
     * @param success
     */
    public FetchHomeProduct(int success, List<Output_Fetch_HomeProduct> outputFatchhomeproduct) {
        super();
        this.success = success;
        this.outputFatchhomeproduct = outputFatchhomeproduct;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<Output_Fetch_HomeProduct> getOutputFatchhomeproduct() {
        return outputFatchhomeproduct;
    }

    public void setOutputFatchhomeproduct(List<Output_Fetch_HomeProduct> outputFatchhomeproduct) {
        this.outputFatchhomeproduct = outputFatchhomeproduct;
    }

}
