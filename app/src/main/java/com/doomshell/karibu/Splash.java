package com.doomshell.karibu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doomshell.karibu.model.SharedPrefManager;

public class Splash extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    Animation animationmove,animation_Fade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask

        setContentView(R.layout.activity_splash);

        imageView=(ImageView)findViewById(R.id.splash_imag);
        textView=(TextView)findViewById(R.id.splash_txt);



        animationmove= AnimationUtils.loadAnimation(Splash.this,R.anim.zoom_in);
        animation_Fade= AnimationUtils.loadAnimation(Splash.this,R.anim.fade_in);
        imageView.startAnimation(animationmove);
        textView.startAnimation(animation_Fade);
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                redirectview();
            }
        };
        handler.postDelayed(r, 3000);
    }

    private void redirectview() {

        boolean islogin=SharedPrefManager.getInstance(Splash.this).getLogin_status("islogin");

        if(islogin)
        {
            Intent intent = new Intent(Splash.this, Home.class);
            startActivity(intent);
            finish();

        }else {

            Intent intent=new Intent(Splash.this,Account.class);
            startActivity(intent);
           /* ZipcodeFragment zipcodeFragment = new ZipcodeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.first_container, zipcodeFragment);
            fragmentTransaction.addToBackStack(zipcodeFragment.getClass().getName());
            fragmentTransaction.commit();*/

        }
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();

    }
}
