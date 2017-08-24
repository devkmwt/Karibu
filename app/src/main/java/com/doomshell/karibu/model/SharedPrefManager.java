package com.doomshell.karibu.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Anuj on 12/19/2016.
 */

public class SharedPrefManager {


    private static final String user_details= "userdetails";
    private static final String Login_status= "loginstatus";

    private static final String SHARED_PREF_NAME = "FCMSharedPref";
    private static final String TAG_TOKEN = "tagtoken";

    private static final String loginfrom = "loginfrom";


    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //this method will save the device token to shared preferences
    public boolean saveDeviceToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TOKEN, token);
        editor.apply();
        return true;
    }

    public boolean saveLoginFrom(String skey,String svalue){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(loginfrom, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(skey, svalue);
        editor.apply();
        return true;
    }

    public boolean saveUser_details(String skey,String svalue){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(user_details, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(skey, svalue);
        editor.apply();
        return true;
    }

    public boolean save_Login_status(String skey,boolean svalue){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Login_status, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(skey, svalue);
        editor.apply();
        return true;
    }


    public String getDeviceToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_TOKEN, null);
    }

    public String getLoginFrom(String skey){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(loginfrom, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(skey, null);
    }

    public String getUser_details(String skey){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(user_details, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(skey, null);
    }

    public boolean getLogin_status(String skey){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Login_status, Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean(skey, false);
    }



    public boolean getProperty_Login_status(String skey){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(Login_status, Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean(skey,false);
    }
}
