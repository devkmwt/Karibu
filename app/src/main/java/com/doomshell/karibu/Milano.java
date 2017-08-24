package com.doomshell.karibu;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doomshell.karibu.adapter.ViewPager_adapter;

import java.util.Timer;
import java.util.TimerTask;

public class Milano extends FragmentActivity implements
        ActionBar.TabListener {


    TabLayout tabLayout;
   // ImageView imageView;
    Timer timer;
    int page =0;
    private ViewPager viewPager;
    private ViewPager_adapter pageadapter;

    // Tab titles
    private String[] tabs = { "Menu", "Review", "INFO" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_milano);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //imageView=(ImageView)findViewById(R.id.cart_img);
        viewPager=(ViewPager)findViewById(R.id.myviewpager);
        pageadapter=new ViewPager_adapter(getSupportFragmentManager());

        pageadapter.addFragment(new Tab_Menu(),"Menu");
        pageadapter.addFragment(new TabReview(),"Review");
        pageadapter.addFragment(new TabInfo(),"Info");

        viewPager.setAdapter(pageadapter);

        tabLayout.setupWithViewPager(viewPager);

      /*  imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(Milano.this);
                LayoutInflater inflater= getLayoutInflater();
                View alerview=inflater.inflate(R.layout.activity_checkout,null);
                builder.setView(alerview);
                AlertDialog alertDialog=builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                alertDialog.show();
            }
        });*/
   }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

}
