package com.doomshell.karibu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class Search_Activity extends AppCompatActivity {


    Spinner search_kitchen_spinner;
    LinearLayout milano_item;

    ArrayList<String> kitchen_itenms=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        milano_item=(LinearLayout)findViewById(R.id.milano_item);

        kitchen_itenms.add("Select All");
        kitchen_itenms.add("Select All");
        kitchen_itenms.add("Select All");
        kitchen_itenms.add("Select All");
        ArrayAdapter<String> kitech_adapter=new ArrayAdapter<String>(Search_Activity.this,R.layout.support_simple_spinner_dropdown_item,kitchen_itenms);
        search_kitchen_spinner=(Spinner)findViewById(R.id.search_kitchen_spinner);
        search_kitchen_spinner.setAdapter(kitech_adapter);

        milano_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Search_Activity.this,Milano.class);
                startActivity(intent);
            }
        });

    }
}
