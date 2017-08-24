package com.doomshell.karibu;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;

public class Account extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ZipcodeFragment zipcodeFragment = new ZipcodeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.first_container, zipcodeFragment);
        //fragmentTransaction.addToBackStack(zipcodeFragment.getClass().getName());
        fragmentTransaction.commit();
    }
}
