package com.doomshell.karibu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.doomshell.karibu.Home;
import com.doomshell.karibu.Milano;
import com.doomshell.karibu.Milano_product;
import com.doomshell.karibu.R;
import com.doomshell.karibu.helper.CustomLogin_Dialog;
import com.doomshell.karibu.helper.ServerApi;
import com.doomshell.karibu.model.SharedPrefManager;
import com.doomshell.karibu.model.retrofit_model.AddCart;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by neha on 8/25/2017.
 */

public class Menu_adaptor extends RecyclerView.Adapter<Menu_adaptor.MyViewHolder> {

    Context context;
    Activity activity;

    ArrayList<String> restro_id=new ArrayList<>();
    ArrayList<String> product_title=new ArrayList<>();
    ArrayList<String> product_image=new ArrayList<>();
    ArrayList<String> description=new ArrayList<>();
    ArrayList<String> store_price=new ArrayList<>();
    ArrayList<String> p_id=new ArrayList<>();

    ServerApi serverApi;
    FloatingActionButton floatingActionButton;

    public Menu_adaptor(Context context, ArrayList<String> restro_id, ArrayList<String> product_name,
                        ArrayList<String> product_image, ArrayList<String> description,
                        ArrayList<String> store_price, ArrayList<String> p_id, Activity activity,
                        FloatingActionButton floatingActionButton) {

        this.context=context;
        this.restro_id=restro_id;
        this.product_title=product_name;
        this.product_image=product_image;
        this.description=description;
        this.store_price=store_price;
        this.p_id=p_id;
        this.activity=activity;
        this.floatingActionButton=floatingActionButton;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(activity.getResources().getString(R.string.Base_Url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        serverApi =retrofit.create(ServerApi.class);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView menu_titl;
        TextView menu_description;
        TextView menu_price;
        ImageView menu_img;
        LinearLayout restro_item,add_item_to_cart;

        public MyViewHolder(View itemView) {
            super(itemView);

            menu_titl=(TextView)itemView.findViewById(R.id.menu_title);
            menu_description=(TextView)itemView.findViewById(R.id.menu_description);
            menu_price=(TextView)itemView.findViewById(R.id.menu_price);
            menu_img=(ImageView) itemView.findViewById(R.id.menu_image);
            restro_item=(LinearLayout) itemView.findViewById(R.id.restro_item);
            add_item_to_cart=(LinearLayout) itemView.findViewById(R.id.add_item_to_cart);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_menu_adaptor,parent,false);
      //Toast.makeText(context, ""+restro_id, Toast.LENGTH_SHORT).show();

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.menu_titl.setText(""+product_title.get(position));
        holder.menu_description.setText(""+description.get(position));
        holder.menu_price.setText(" \uFF04"+store_price.get(position));

        Glide.with(context).load(product_image.get(position)).asBitmap().centerCrop().into(new SimpleTarget<Bitmap>(200,200) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.menu_img.setImageDrawable(circularBitmapDrawable);
            }
        });


       /* Glide.with(context).load(product_image.get(position)).asBitmap().into(new BitmapImageViewTarget(holder.menu_img) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(activity.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.menu_img.setImageDrawable(circularBitmapDrawable);
            }});*/

        holder.restro_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Toast.makeText(context, "product :"+p_id.get(position), Toast.LENGTH_SHORT).show();
                /*Intent intent=new Intent(activity, Milano_product.class);
                intent.putExtra("title",product_title.get(position));
                intent.putExtra("description",description.get(position));
                intent.putExtra("price",store_price.get(position));
                activity.startActivity(intent);*/
            }
        });

        holder.add_item_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SharedPrefManager.getInstance(context).getLogin_status("islogin"))
                {
                    Toast.makeText(context, "product id :"+p_id.get(position)+"\n"+store_price.get(position), Toast.LENGTH_SHORT).show();

                    load_Add_Cart(SharedPrefManager.getInstance(context).getUser_details("userid"),0,p_id.get(position), Integer.parseInt(store_price.get(position)),1);

                } else {

                    CustomLogin_Dialog customLogin_dialog=new CustomLogin_Dialog(activity,R.style.My_login_DialogTheme,activity);
                    customLogin_dialog.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return restro_id.size();
    }

    void load_Add_Cart(String userid,int deviceid,String product_id,int price,int qty)
    {
        floatingActionButton.hide();
        serverApi.add_cart(userid,deviceid,product_id,price,qty).enqueue(new Callback<AddCart>() {
            @Override
            public void onResponse(Call<AddCart> call, Response<AddCart> response) {
           //     Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();
                if(response!=null)
                {
                    if (response.message().equalsIgnoreCase("ok"))
                    {
                        if (response.body().getSuccess().equals("1"))
                        {
                            Toast.makeText(context, ""+response.body().getStatus(), Toast.LENGTH_SHORT).show();
                            floatingActionButton.show();
                        }else {
                            Toast.makeText(context, "unable to add item in cart", Toast.LENGTH_SHORT).show();
                            floatingActionButton.show();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<AddCart> call, Throwable t) {

            }
        });

    }
}
