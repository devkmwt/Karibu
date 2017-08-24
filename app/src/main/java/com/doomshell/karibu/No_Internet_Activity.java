package com.doomshell.karibu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class No_Internet_Activity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver=null;
    Button no_internet_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_internet_activity);
        no_internet_btn=(Button)findViewById(R.id.no_internet_btn);

        no_internet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                startActivity(intent);
            }
        });

        broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if(isNetworkAvailable())
                {

                 //   Toast.makeText(context, "network available", Toast.LENGTH_SHORT).show();
                    if(isOnline())
                    {
                       // Home_Checker.Is_comming_After_offline=true;
                    //    FirstHome_Item_setter.getInstance().isFirst_Homecreated=false;
                      //  Home_item_setter.getInstance().isHomecreated=false;
                        finish();
                   //     Toast.makeText(context, "online", Toast.LENGTH_SHORT).show();
                    }else {
                     //   Toast.makeText(context, "offline", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    //Toast.makeText(context, "not available", Toast.LENGTH_SHORT).show();
                }

            }

        };
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
    public Boolean isOnline() {
        try {
            Process p1 = Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal==0);
            return reachable;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }


    public void onStart()
    {
        super.onStart();

        String name= String.valueOf(ConnectivityManager.CONNECTIVITY_ACTION);
      //  Toast.makeText(this, ""+name, Toast.LENGTH_SHORT).show();
        registerReceiver(broadcastReceiver,new IntentFilter(name));
    }

    public void onStop()
    {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }
}
