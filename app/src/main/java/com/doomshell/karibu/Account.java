package com.doomshell.karibu;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class Account extends AppCompatActivity {


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
