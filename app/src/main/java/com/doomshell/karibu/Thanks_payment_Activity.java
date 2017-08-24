package com.doomshell.karibu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class Thanks_payment_Activity extends AppCompatActivity {

    TextView t_time,t_items;
    Button t_order_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_thanks_payment_);

        Intent intent=getIntent();


        t_time=(TextView)findViewById(R.id.t_time);
        t_items=(TextView)findViewById(R.id.t_items);
        t_order_detail=(Button) findViewById(R.id.t_order_detail);

        t_order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  CheckoutChecker.IsPaymentDone=true;
                Intent GoTonext = new Intent(Thanks_payment_Activity.this, Home.class);
                startActivity(GoTonext);
                finish();
            }
        });



        t_time.setText(intent.getStringExtra("t_time"));
        t_items.setText("for"+intent.getStringExtra("t_items")+" items");

        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                redirectview();
            }
        };
        handler.postDelayed(r, 7000);


    }

    public void redirectview() {

        //  SharedPreferences preferences=getSharedPreferences("userdetails",0);

            Intent GoTonext = new Intent(this, Home.class);
            startActivity(GoTonext);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();

    }
}
