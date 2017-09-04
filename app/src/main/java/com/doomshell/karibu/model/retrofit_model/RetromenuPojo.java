
package com.doomshell.karibu.model.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetromenuPojo {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("output")
    @Expose
    private List<Output_menu> output = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<Output_menu> getOutput() {
        return output;
    }

    public void setOutput(List<Output_menu> output) {
        this.output = output;
    }

}
