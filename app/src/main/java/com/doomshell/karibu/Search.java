package com.doomshell.karibu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;


public class Search extends AppCompatActivity implements View.OnClickListener{

    RadioButton radio_first;
    LinearLayout search1,search2,search3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search);

        radio_first=(RadioButton)findViewById(R.id.radio_first);
        radio_first.setChecked(true);
        radio_first.setSelected(true);

        search1=(LinearLayout)findViewById(R.id.search1);
        search2=(LinearLayout)findViewById(R.id.search2);
        search3=(LinearLayout)findViewById(R.id.search3);

        search1.setOnClickListener(this);
        search2.setOnClickListener(this);
        search3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v==search1)
        {
            Intent intent=new Intent(Search.this,Milano.class);
            startActivity(intent);
        }

        if(v==search2)
        {
            Intent intent=new Intent(Search.this,Milano.class);
            startActivity(intent);
        }

        if(v==search3)
        {
            Intent intent=new Intent(Search.this,Milano.class);
            startActivity(intent);
        }
    }
}
