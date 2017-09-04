package com.doomshell.karibu.helper;

import com.doomshell.karibu.model.retrofit_model.AddCart;
import com.doomshell.karibu.model.retrofit_model.FetchHomeProduct;
import com.doomshell.karibu.model.retrofit_model.FetchHomeProductsById;
import com.doomshell.karibu.model.retrofit_model.FetchKitchenTypes;
import com.doomshell.karibu.model.retrofit_model.FetchSearch;
import com.doomshell.karibu.model.retrofit_model.RetroEdiproPojo;
import com.doomshell.karibu.model.retrofit_model.RetromenuPojo;
import com.doomshell.karibu.model.retrofit_model.RetrorFetchcartpojo;
import com.doomshell.karibu.model.retrofit_model.RetrorInfopojo;
import com.doomshell.karibu.model.retrofit_model.Retrorreviewpojo;
import com.doomshell.karibu.model.retrofit_model.RetroLoginPojo;
import com.doomshell.karibu.model.retrofit_model.RetroRegistrationPojo;
import com.doomshell.karibu.model.retrofit_model.for_zip.RetroZipcode;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by Anuj on 7/12/2017.
 */

public interface ServerApi {

    @POST("/mobile/user_login")
    @FormUrlEncoded
    Call<RetroLoginPojo> savePost(
            @Field("email_id") String email,
            @Field("password") String pass);

    @POST("/mobile/edit_profile")
    @FormUrlEncoded
    Call<RetroEdiproPojo> editprofile(
            @Field("user_id") String user_id,
            @Field("first_name") String name,
            @Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("/mobile/user_registration")
    Call<RetroRegistrationPojo> user_registration(
            @Field("email_id") String email_id,
            @Field("name") String password,
            @Field("mobile") String mobile,
            @Field("password") String pass);

    @FormUrlEncoded
    @POST("/mobile/fatch_home_item")
    Call<FetchHomeProductsById> fatch_home_item(
            @Field("count") int count,
            @Field("type") int type,
            @Field("zipcode") String zipcode);

    @GET("/mobile/fatch_home_product")
    Call<FetchHomeProduct> fatch_home_product();

    @FormUrlEncoded
    @POST("/mobile/fetch_restorent_review")
    Call<Retrorreviewpojo> review(
            @Field("Restorentid") String Restorentid,
            @Field("count") int count);

    @FormUrlEncoded
    @POST("/mobile/fatch_store_product")
    Call<RetromenuPojo> menu(
            @Field("restoid") String restoid,
            @Field("count") int count);

    @FormUrlEncoded
    @POST("/mobile/fatch_store_info")
    Call<RetrorInfopojo> info(
            @Field("restoid") String restoid);

    @GET("/mobile/kitchen_types")
    Call<FetchKitchenTypes> kitchen_types();

    @FormUrlEncoded
    @POST("/mobile/search")
    Call<FetchSearch> search(
            @Field("type") String type,
            @Field("rate") float rate,
            @Field("cate") String cate,
            @Field("zipcode") String zipcode,
            @Field("title") String title
            );


    @FormUrlEncoded
    @POST("/mobile/add_cart")
    Call<AddCart> add_cart(
            @Field("user_id") String restoid,
            @Field("device_id") int device_id,
            @Field("p_id") String p_id,
            @Field("tot_price") int tot_price,
            @Field("qty") int qty);

    @FormUrlEncoded
    @POST("/mobile/fatch_cart")
    Call<RetrorFetchcartpojo> fetchcart(
            @Field("user_id") String user_id);


    @GET("/maps/api/geocode/json")
    Call<RetroZipcode> get_zip_google(
            @Query("latlng") String latlng,
            @Query("language") String language);
}


