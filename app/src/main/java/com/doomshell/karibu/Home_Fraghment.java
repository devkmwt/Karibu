package com.doomshell.karibu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class Home_Fraghment extends Fragment implements View.OnClickListener {

View convertview;
    Context context;
    LinearLayout restro1,restro2,restro3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity().getApplicationContext();
        convertview= inflater.inflate(R.layout.fragment_home__fraghment, container, false);

        restro1=(LinearLayout)convertview.findViewById(R.id.restro1);
        restro2=(LinearLayout)convertview.findViewById(R.id.restro2);
        restro3=(LinearLayout)convertview.findViewById(R.id.restro3);

        restro1.setOnClickListener(this);
        restro2.setOnClickListener(this);
        restro3.setOnClickListener(this);

        return convertview;
    }

    @Override
    public void onClick(View v) {
        if(v==restro1)
        {
            Intent intent=new Intent(context,Milano.class);
            startActivity(intent);
        }

        if(v==restro2)
        {
            Intent intent=new Intent(context,Milano.class);
            startActivity(intent);
        }

        if(v==restro3)
        {
            Intent intent=new Intent(context,Milano.class);
            startActivity(intent);
        }
    }
}
