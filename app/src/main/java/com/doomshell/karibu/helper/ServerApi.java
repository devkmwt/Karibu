package com.doomshell.karibu.helper;

import com.doomshell.karibu.model.retrofit_model.RetroEdiproPojo;
import com.doomshell.karibu.model.retrofit_model.for_zip.Result;
import com.doomshell.karibu.model.retrofit_model.RetroLoginPojo;
import com.doomshell.karibu.model.retrofit_model.RetroRegistrationPojo;
import com.doomshell.karibu.model.retrofit_model.for_zip.RetroZipcode;

import java.util.List;

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



    @GET("/maps/api/geocode/json")
    Call<RetroZipcode> get_zip_google(
            @Query("latlng") String latlng,
            @Query("language") String language);
}


