package com.doomshell.karibu.helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Neha on 12/19/2016.
 */

public class SharedPrefManager {


    private static final String user_details= "user_details";
    private static final String login= "login";



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

    public boolean saveuser_details(String skey, String svalue){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(user_details, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(skey, svalue);
        editor.apply();
        return true;
    }

    public String getuser_details(String skey){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(user_details, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(skey, null);
    }

    public boolean saveuser_login(String skey, boolean svalue){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(login, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(skey, svalue);
        editor.apply();
        return true;
    }

    public boolean getuser_login(String skey){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(login, Context.MODE_PRIVATE);
        return  sharedPreferences.getBoolean(skey, false);
    }



}
