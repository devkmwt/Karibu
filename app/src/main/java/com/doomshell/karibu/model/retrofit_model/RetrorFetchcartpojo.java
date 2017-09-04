
package com.doomshell.karibu.model.retrofit_model;

import android.content.Intent;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrorFetchcartpojo {

    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("totalitems")
    @Expose
    private Integer totalitems;
    @SerializedName("cart_total")
    @Expose
    private Integer cartTotal;
    @SerializedName("product")
    @Expose
    private List<FetchProduct> fetchProduct = null;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getTotalitems() {
        return totalitems;
    }

    public void setTotalitems(Integer totalitems) {
        this.totalitems = totalitems;
    }

    public Integer getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(Integer cartTotal) {
        this.cartTotal = cartTotal;
    }

    public List<FetchProduct> getFetchProduct() {
        return fetchProduct;
    }

    public void setFetchProduct(List<FetchProduct> fetchProduct) {
        this.fetchProduct = fetchProduct;
    }

}
