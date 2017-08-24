package com.doomshell.karibu;

import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class Tab_Menu extends Fragment  {


    Context context;
    View convertview;
   LinearLayout restro_item1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity().getApplicationContext();
        convertview= inflater.inflate(R.layout.activity_tab__menu, container, false);

        restro_item1=(LinearLayout)convertview.findViewById(R.id.restro_item1);

        restro_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Milano_product.class);
                startActivity(intent);
            }
        });

        return convertview;
    }


}
