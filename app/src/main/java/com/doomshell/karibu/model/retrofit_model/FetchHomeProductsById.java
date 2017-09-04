
package com.doomshell.karibu.model.retrofit_model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FetchHomeProductsById {

    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("output")
    @Expose
    private List<OutputHomeById> outputHomeById = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FetchHomeProductsById() {
    }

    /**
     * 
     * @param outputHomeById
     * @param success
     */
    public FetchHomeProductsById(int success, List<OutputHomeById> outputHomeById) {
        super();
        this.success = success;
        this.outputHomeById = outputHomeById;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<OutputHomeById> getOutputHomeById() {
        return outputHomeById;
    }

    public void setOutputHomeById(List<OutputHomeById> outputHomeById) {
        this.outputHomeById = outputHomeById;
    }

}
