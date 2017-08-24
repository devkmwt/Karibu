package com.doomshell.karibu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Milano_search extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //code that displays the content in full screen mode
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask


        setContentView(R.layout.activity_milano_search);

        LinearLayout milano_item=(LinearLayout)findViewById(R.id.milano_item);
        LinearLayout millano2=(LinearLayout)findViewById(R.id.millano2);

        milano_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Milano_search.this,Milano_product.class);
                startActivity(intent);
            }
        });


    }
}
