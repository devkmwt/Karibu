package com.doomshell.karibu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doomshell.karibu.adapter.Fetch_cart_adaptor;
import com.doomshell.karibu.adapter.Review_Adaptor;
import com.doomshell.karibu.adapter.ViewPager_adapter;
import com.doomshell.karibu.helper.CustomLogin_Dialog;
import com.doomshell.karibu.helper.CustomProgressDialog;
import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.SharedPrefManager;
import com.doomshell.karibu.model.retrofit_model.FetchProduct;
import com.doomshell.karibu.model.retrofit_model.Output_Review_Retorant;
import com.doomshell.karibu.model.retrofit_model.RetrorFetchcartpojo;
import com.doomshell.karibu.model.retrofit_model.Retrorreviewpojo;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Milano extends AppCompatActivity implements
        ActionBar.TabListener,View.OnClickListener {


    TabLayout tabLayout;
   // ImageView imageView;
    Timer timer;
    int page =0;
    private ViewPager viewPager;
    private ViewPager_adapter pageadapter;
    String Restro_name,Restro_Image,Type,Deliver;
    Float Rating;
    RecyclerView cart_recycle;
    int Restro_ID;
    TextView restro_name_text,restro_type,restro_deliver;
    ImageView restro_image;
    RatingBar restro_rating;
    CoordinatorLayout millano_cordination;
    FloatingActionButton restro_cart;
    int cartitam,cartprice;
    ServerApi serverApi;
    Activity activity;
    ArrayList<String> itemId=new ArrayList<>();
    ArrayList<String> imageUrl=new ArrayList<>();
    ArrayList<String> title=new ArrayList<>();
    ArrayList<String> description=new ArrayList<>();
    ArrayList<String> qty=new ArrayList<>();
    ArrayList<String> price=new ArrayList<>();


    DisplayMetrics displayMetrics;
    double screenHeight;
    double screenWidth;
    int lwi, lhi;
    int layout_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_milano);


        displayMetrics = getResources().getDisplayMetrics();
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
        lhi = (int) (screenHeight * 0.40);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://karibu.kbctindia.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serverApi =retrofit.create(ServerApi.class);

        Restro_name=getIntent().getStringExtra("name");
        Restro_Image=getIntent().getStringExtra("image");
        Restro_ID=getIntent().getIntExtra("cat_id",0);
        Type=getIntent().getStringExtra("type");
        Deliver=getIntent().getStringExtra("deliver");
        Rating=getIntent().getFloatExtra("rating",0.0f);

        restro_image = (ImageView) findViewById(R.id.restro_image);
        restro_cart = (FloatingActionButton) findViewById(R.id.restro_cart);
        restro_name_text = (TextView) findViewById(R.id.restro_name_text);
        restro_type = (TextView) findViewById(R.id.restro_type);
        restro_deliver = (TextView) findViewById(R.id.restro_deliver);
        restro_rating = (RatingBar) findViewById(R.id.restro_rating);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.myviewpager);
        millano_cordination=(CoordinatorLayout) findViewById(R.id.millano_cordination);

        pageadapter=new ViewPager_adapter(getSupportFragmentManager());

        Bundle tab_bundle=new Bundle();
        tab_bundle.putInt("Restro_ID",Restro_ID);

        Tab_Menu tabMenu=new Tab_Menu();
        tabMenu.setArguments(tab_bundle);


        TabReview tabReview=new TabReview();
        tabReview.setArguments(tab_bundle);

        TabInfo tabInfgo=new TabInfo();
        tabInfgo.setArguments(tab_bundle);

        pageadapter.addFragment(tabMenu,"Menu");
        pageadapter.addFragment(tabReview,"Review");
        pageadapter.addFragment(tabInfgo,"Info");
        viewPager.setAdapter(pageadapter);

        tabLayout.setupWithViewPager(viewPager);

        restro_name_text.setText(Restro_name);
        restro_type.setText(Type);
        restro_deliver.setText(Deliver);

        LayerDrawable stars = (LayerDrawable)restro_rating.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        restro_rating.setRating(Rating);

        Log.e("img",Restro_Image);
        Glide.with(Milano.this).load(Restro_Image).into(restro_image);

        restro_cart.setOnClickListener(this);
        if(SharedPrefManager.getInstance(Milano.this).getLogin_status("islogin")) {
            load_cart(SharedPrefManager.getInstance(Milano.this).getUser_details("userid"));
        }
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

    @Override
    public void onClick(View v) {
        if(v==restro_cart)
        {
            if(SharedPrefManager.getInstance(Milano.this).getLogin_status("islogin")) {

                restro_cart.hide();
                AlertDialog.Builder builder=new AlertDialog.Builder(Milano.this);
                LayoutInflater inflater= getLayoutInflater();
                View alerview=inflater.inflate(R.layout.cart,null);

                TextView cart_total_item=(TextView)alerview.findViewById(R.id.cart_total_item);
                cart_total_item.setText(""+cartitam);
                TextView cart_total_price=(TextView)alerview.findViewById(R.id.cart_total_price);
                cart_total_price.setText(""+cartprice);
                cart_recycle=(RecyclerView)alerview.findViewById(R.id.recycle_cart);
                cart_recycle.setLayoutManager(new LinearLayoutManager(Milano.this,LinearLayoutManager.VERTICAL,false));
                cart_recycle.getLayoutParams().height=lhi;
                cart_recycle.requestLayout();

                builder.setView(alerview);
                AlertDialog alertDialog=builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cart_recycle.setAdapter(new Fetch_cart_adaptor(Milano.this, itemId, imageUrl, title, description, qty, price, activity));

                alertDialog.show();
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        restro_cart.show();
                    }
                });
            }
            else {

                Toast.makeText(Milano.this, "Please login first", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void load_cart(String userid) {

       final CustomProgressDialog customProgressDialog=new CustomProgressDialog(Milano.this);

        serverApi.fetchcart(userid).enqueue(new Callback<RetrorFetchcartpojo>() {
            @Override
            public void onResponse(Call<RetrorFetchcartpojo> call, Response<RetrorFetchcartpojo> response) {

             //   Toast.makeText(Milano.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                if(response.message().equalsIgnoreCase("ok")) {

                 //   Toast.makeText(Milano.this, ""+response.body().getSuccess(), Toast.LENGTH_SHORT).show();

                    if (response.body().getSuccess() == 1) {
                        cartitam=response.body().getTotalitems();
                        cartprice=response.body().getCartTotal();
                        RetrorFetchcartpojo retrorFetchcartpojo = response.body();
                        ArrayList<FetchProduct> fetchProducts = (ArrayList<FetchProduct>)
                                retrorFetchcartpojo.getFetchProduct();

                        for (FetchProduct ot : fetchProducts) {
                            itemId.add(ot.getItemId());
                            imageUrl.add(ot.getImageUrl());
                            title.add(ot.getTitle());
                            description.add(ot.getDescription());
                            qty.add(ot.getQty());
                            price.add(ot.getPrice());
                        }
                        customProgressDialog.hide();
                    }
                        else{
                        customProgressDialog.hide();
                        Toast.makeText(Milano.this, "No Item founf in your Cart", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RetrorFetchcartpojo> call, Throwable t) {
                customProgressDialog.hide();
                Toast.makeText(activity, "fetch cart :"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
